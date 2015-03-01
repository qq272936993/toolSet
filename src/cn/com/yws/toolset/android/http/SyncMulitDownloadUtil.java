package cn.com.yws.toolset.android.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


/**
 * 多线程下载文件
 * */
public class SyncMulitDownloadUtil {
	
	private final static int HTTP_ALL_SUCCESS = 200;
	public final static int HTTP_PART_SUCCESS = 206;
	private final static int DEFAULT_THREAD_COUNT = 3;
	
	//定以一个内部类用于相关数据的封装
	public class ResouceInfo{
		
		private String fileName;
		private int fileTotalLength;
		private String path;
		private String target;
		private int threadCount;
		
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public int getFileTotalLength() {
			return fileTotalLength;
		}
		public void setFileTotalLength(int fileTotalLength) {
			this.fileTotalLength = fileTotalLength;
		}
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		public String getTarget() {
			return target;
		}
		public void setTarget(String target) {
			this.target = target;
		}
		public int getThreadCount() {
			return threadCount;
		}
		public void setThreadCount(int threadCount) {
			this.threadCount = threadCount;
		}
		public ResouceInfo(String path, String target,int threadCount) {
			super();
			this.path = path;
			this.target = target;
			this.threadCount = threadCount;
		}
		public ResouceInfo() {
			super();
		}
	}
	
	/**
	 * 通过HTTP GET的请求下载数据
	 * @param path 下载文件的路径
	 * @param target 存放的路径文件
	 * */
	public void get(String path,String target){
		ResouceInfo resouceInfo = new ResouceInfo(path,target,DEFAULT_THREAD_COUNT);	
		try {
			URL url = new URL(path);
			resouceInfo.setFileName(getFileName(path));
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			int code = conn.getResponseCode();
			//如果返回成功,则继续以后的操作
			if(code == HTTP_ALL_SUCCESS){
				resouceInfo.setFileTotalLength(conn.getContentLength());
				allotThread(resouceInfo);
			}else{
				System.out.println("操作错误:错误码"+code);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.out.println("访问的路径:"+path+"\t不是合法格式的url");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("连接不到服务器..");
		}
	}
	
	/**
	 * 分配线程工作,并启动线程工作
	 * */
	private void allotThread(ResouceInfo resourceInfo){
		int fileTotalLength = resourceInfo.getFileTotalLength();
		if( fileTotalLength> 0){
			int singleDataLength = fileTotalLength/DEFAULT_THREAD_COUNT;
			for(int i = 0; i < DEFAULT_THREAD_COUNT; i++){
				int startIndex = i * singleDataLength;	//每个线程开始下载的位置
				int endIndex = (i+1) * singleDataLength-1;	//每个线程结束下载的位置
				if(i == DEFAULT_THREAD_COUNT -1)
					endIndex = fileTotalLength;		//最后一个线程下载预留部分的数据
				
				new DownloadThread(i,startIndex, endIndex, resourceInfo)
							.start();
			}
			
		}else{
			System.out.println("文件没有数据");
		}
	}
	
	
	public class DownloadThread extends Thread{
		
		private int threadId;
		private int startIndex;
		private int endIndex;
		private ResouceInfo resourceInfo;
		
		
		public DownloadThread(int threadId,int startIndex, int endIndex,
				ResouceInfo resourceInfo) {
			this.threadId = threadId;
			this.startIndex = startIndex;
			this.endIndex = endIndex;
			this.resourceInfo = resourceInfo;
		}


		@Override
		public void run() {
			String target = resourceInfo.getTarget();	//获取目标路径
			String tmpPath = getTmpFileName(target, threadId);	//获取临时文件名称
			RandomAccessFile raf = null;
			int totalLength = resourceInfo.getFileTotalLength();	//该文件的总长度
			try {
				URL url = new URL(resourceInfo.getPath());
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				Integer lastReadIndex = readTmpFileContent(tmpPath);	//上一次读取最后的位置
				conn.setRequestProperty("Range", "bytes="+(startIndex+lastReadIndex)+"-"+endIndex);
				int code = conn.getResponseCode();
				//获取部分数据成功
				if(code == HTTP_PART_SUCCESS){
					
					raf = new RandomAccessFile(target, "rwd");
					raf.seek(startIndex+lastReadIndex);						//设置开始写入的位置
					
					InputStream is = conn.getInputStream();
					byte[] buf = new byte[1024 * 5];
					int len = -1;
					int readLength = endIndex - startIndex;
					int total = 0;
					while((len = is.read(buf)) != -1){
						raf.write(buf, 0, len);
						total +=  len;
						//生成进度信息
						System.out.println("线程："+threadId + "读取的资源占该线程的:"+( (total+lastReadIndex)*100/readLength )+"%" +"\t占全部资源的:"+((total+lastReadIndex)*100/totalLength)+"%" );
						//生成临时记录文件
						RandomAccessFile recordFile = new RandomAccessFile(tmpPath, "rwd");
						recordFile.write(String.valueOf(total+lastReadIndex).getBytes());	//这里暂时还没有考虑断点下载的数据
						recordFile.close();
					}
					is.close();
				}
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(raf != null){
					try {
						raf.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					raf = null;
				}
			}
			
			//锁resourceInfo对象,是因为当该Util工具类被new创建出来后,get方法可能会调用多次
			//	用于下载多个文件.每当调用一次get方法,则会开启三个线程下载文件,并且每调用一次get方法,
			//	就会初始化唯一了一个ResourceInfo对象,所以选择该对象进行加锁
			synchronized (resourceInfo) {
				int threadCount = resourceInfo.getThreadCount()-1;
				resourceInfo.setThreadCount(threadCount);
				if(threadCount == 0){
					System.out.println("文件下载完成...");
				}		
				//下载好文件以后,则删除保存的记录文件
				File tmpFile = new File(tmpPath);
				if(tmpFile.exists()){
					tmpFile.delete();
				}
			}
		}
		
	}
	
	
	
	
	private String getFileName(String path){
		return path.substring(path.lastIndexOf("/")+1);
	}
	
	private String getTmpFileName(String target , int threadId){
		File file = new File(target);		
		String targeFileName = file.getName();		//获取目标文件文件名
		String parentPath = file.getParent();		//获取上一级目录,用于拼接临时文件
		String tmpPath = parentPath+targeFileName+"_"+threadId+".txt";
		return tmpPath;
	}
	
	private Integer readTmpFileContent(String tmpPath){
		File tmpFile = new File(tmpPath);
		if(tmpFile.exists()){
			BufferedReader br = null;
			try {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(tmpFile)));
				String line = br.readLine();
				return Integer.valueOf(line);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(br != null){
					try {
						br.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					br = null;
				}
			}
			
		}
		
		return 0;
	}
	
	
	public static void main(String[] args) {
		new SyncMulitDownloadUtil().get("http://192.168.1.61:8089/aa.exe", "D://test.exe");
	}
	
}
