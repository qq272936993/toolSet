package cn.com.yws.toolset.base.common.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashSet;
import java.util.Set;


public class FileHelper {
	/**
	 * 系统路径分隔符
	 */
	static final char FILE_SPT = File.separatorChar;

	private static final int BUFFER_SIZE = 16 * 1024;

	private static final long VIRTUAL_MEMORY_SIZE = 512 * 1024 * 1024;

	private static final String CHARTSET = "UTF-8";

	public static String toLocalDir(String dir) {
		String osName = System.getProperty("os.name").toLowerCase();
		return toLocalDir(osName, dir);
	}

	public static String toLocalDir(String osName, String dir) {
		osName = osName.toLowerCase();
		if (osName.indexOf("windows") != -1) {
			return dir.replace('/', '\\');
		}

		return dir.replace('\\', '/');
	}

	public static String addPath(String headPath, String behindPath) {
		if (null == headPath || headPath.length() <= 0) {
			throw new IllegalArgumentException(
					"headPath must input and length > 0");
		}

		headPath = FileHelper.toLocalDir(headPath);
		behindPath = FileHelper.toLocalDir(behindPath);
		StringBuffer path = new StringBuffer(headPath);
		if (path.lastIndexOf(File.separator) == path.length() - 1) {
			path.deleteCharAt(path.length() - 1);
		}

		if (behindPath.indexOf(File.separator) != 0) {
			path.append(File.separator);
		}
		path.append(behindPath);
		return path.toString();
	}

	public static boolean createDir(String dir) {
		boolean result = false;
		File f = new File(toLocalDir(dir));
		if (f.exists()) {
			return true;
		}
		result = f.mkdir();
		if (!result) {
			result = f.mkdirs();
		}
		return result;
	}

	public static boolean deleteDir(String dir) {
		File f = new File(toLocalDir(dir));
		if (f.exists()) {
			delete(f);
		}
		return true;
	}

	public static boolean createFile(String filePath) {
		File f = new File(toLocalDir(filePath));
		if (f.exists()) {
			return true;
		}
		try {
			return f.createNewFile();
		} catch (IOException e) {

			return false;
		}

	}

	public static boolean deleteFile(String file) {
		File f = new File(toLocalDir(file));
		if (f.exists()) {
			return f.delete();
		}
		return false;
	}

	private static void delete(File file) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					delete(files[i]);
				} else {
					files[i].delete();
				}
			}
			file.delete();
		} else {
			file.delete();
		}
	}



	/**
	 * Description : 保存图片到物理路径下
	 * 
	 * @param src
	 * @param dst
	 * @throws Exception
	 */
	public static void copy(File src, File dst) throws Exception {
		try {
			long srctime = src.lastModified();
			if (dst.exists()) {
				long srcfilesize = src.length();
				long dstfilesize = dst.length();
				long dsttime = dst.lastModified();
				if (srcfilesize == dstfilesize && srctime == dsttime) {
					return;
				}
			}
			InputStream srcIo = new FileInputStream(src);
			copyStream(srcIo, dst, src.length());
			dst.setLastModified(srctime);
		} catch (FileNotFoundException e) {
			StringBuilder msg = new StringBuilder(src.getAbsolutePath());
			msg.append("文件不存在!");
			throw e;
		} catch (IOException e) {
			StringBuilder msg = new StringBuilder(src.getAbsolutePath());
			msg.append("复制到");
			msg.append(dst.getAbsolutePath());
			msg.append("文件失败!");
			throw e;
		}
	}

	/**
	 * Description : 保存图片到物理路径下
	 * 
	 * @param src
	 * @param dst
	 * @throws Exception
	 */
	public static void copyStream(InputStream src, File dst) throws Exception {
		copyStream(src, dst, -1);
	}

	@SuppressWarnings("resource")
	private static void copyStream(InputStream src, File dst,
			final long byteCount) throws Exception {
		try {
			if (!dst.exists()) {
				dst.createNewFile();
			}
			if (src instanceof FileInputStream && byteCount > -1) {
				FileChannel srcChannel = ((FileInputStream) src).getChannel();
				FileChannel dstChannel = new FileOutputStream(dst).getChannel();
				if (VIRTUAL_MEMORY_SIZE > byteCount) {
					srcChannel.transferTo(0, srcChannel.size(), dstChannel);
				} else {
					long postion = 0;
					while (byteCount > postion) {
						long needCopyByte = byteCount - postion;
						if (needCopyByte > VIRTUAL_MEMORY_SIZE) {
							needCopyByte = VIRTUAL_MEMORY_SIZE;
						}
						postion += srcChannel.transferTo(postion, needCopyByte,
								dstChannel);
					}
				}
				srcChannel.close();
				dstChannel.close();
			} else {
				BufferedInputStream in = new BufferedInputStream(src,
						BUFFER_SIZE);
				OutputStream out = new BufferedOutputStream(
						new FileOutputStream(dst), BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				int len = 0;
				while ((len = in.read(buffer)) > 0) {
					out.write(buffer, 0, len);
				}
				in.close();
				out.close();
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @Description: 从路径中得到文件名
	 * @param fileFullPath
	 *            文件的全路径
	 * @param spt
	 *            路径分隔符
	 * @return String 文件名
	 * @date Jan 27, 2010 1:20:00 AM
	 */
	public static String getFileName(String fileFullPath, String spt) {
		String fileName = "";
		if (null != fileFullPath) {
			String fileFullPathTemp = fileFullPath.trim();
			if (!"".equals(fileFullPathTemp)) {
				int sptLocation = fileFullPathTemp.lastIndexOf(spt);
				if (sptLocation > -1
						&& sptLocation != fileFullPathTemp.length() - 1) {
					fileName = fileFullPathTemp.substring(sptLocation + 1);
				}
			}
		}
		return fileName;
	}

	/**
	 * 
	 * Description : 目录复制
	 * 
	 * @param srcPath
	 *            源目录
	 * @param dstPath
	 *            目标目录
	 * @throws Exception
	 * 
	 */
	public static void copyDirectory(String srcPath, String dstPath)
			throws Exception {
		copyDirectory(new File(srcPath), new File(dstPath));
	}

	/**
	 * 目录复制
	 * 
	 * @param srcPath
	 *            源目录
	 * @param dstPath
	 *            目标目录
	 * @throws IOException
	 */
	public static void copyDirectory(File srcPath, File dstPath)
			throws Exception {

		if (srcPath.isDirectory()) {

			if (!dstPath.exists()) {
				dstPath.mkdirs();
			}

			String files[] = srcPath.list();

			for (int i = 0; i < files.length; i++) {
				copyDirectory(new File(srcPath, files[i]), new File(dstPath,
						files[i]));

			}

		}

		else {
			if (!srcPath.exists()) {
				System.out.println("File or directory " + srcPath
						+ "does not exist.");

			} else {
				copy(srcPath, dstPath);
			}
		}
	}

	/**
	 * @Description: 复制文件
	 * @param tarPath
	 *            目标文件夹
	 * @param fileName
	 *            文件名
	 * @param srcPath
	 *            来源文件夹
	 * @return void
	 * @throws Exception
	 * @date Jan 27, 2010 1:38:14 AM
	 */
	public static void copyFile(String tarPath, String fileName,String srcPath) throws Exception {
		if (null == tarPath || tarPath.length() <= 0) {
			throw new IllegalArgumentException(
					"tarPath must input and length > 0");
		}
		if (null == srcPath || srcPath.length() <= 0) {
			throw new IllegalArgumentException(
					"srcPath must input and length > 0");
		}
		if (null == fileName || fileName.length() <= 0) {
			throw new IllegalArgumentException(
					"fileName must input and length > 0");
		}
		tarPath = FileHelper.toLocalDir(tarPath);
		srcPath = FileHelper.toLocalDir(srcPath);
		File tarFile = new File(tarPath);

		if (tarFile.exists() && null != fileName && !"".equals(fileName)) {
			File srcBigphotoFImg = null;
			if (srcPath.lastIndexOf(File.separator) == srcPath.length() - 1) {
				srcBigphotoFImg = new File(srcPath + fileName);
			} else {
				srcBigphotoFImg = new File(srcPath + FILE_SPT + fileName);
			}
			if (null == srcBigphotoFImg || !srcBigphotoFImg.isFile()
					|| !srcBigphotoFImg.exists()) {
				throw new Exception("copy.src.file.nofind");
			}
			File dstBigphotoFImg = null;
			if (tarPath.lastIndexOf(File.separator) == tarPath.length() - 1) {
				dstBigphotoFImg = new File(tarPath + fileName);
			} else {
				dstBigphotoFImg = new File(tarPath + FILE_SPT + fileName);
			}
			if (!dstBigphotoFImg.exists()) {
				try {
					dstBigphotoFImg.createNewFile();
				} catch (IOException e) {
					throw new Exception("create.file.error");
				}
			}
		}
	}

	public static Set<String> listFile(String path) {
		Set<String> result = new HashSet<String>();
		if (null == path || path.length() <= 0) {
			throw new IllegalArgumentException("path must input and length > 0");
		}
		path = FileHelper.toLocalDir(path);
		File dir = new File(path);
		if (null != dir && dir.exists() && dir.isDirectory()) {
			File[] files = dir.listFiles();
			if (null != files) {
				int fileslen = files.length;
				for (int i = 0; i < fileslen; i++) {
					File file = files[i];
					if (file.isFile()) {
						result.add(file.getName());
					}
				}
			}
		}
		return result;
	}

	/**
	 * 
	 * Description : 获取文件或文件夹总大小，单位byte
	 * 
	 * @param f
	 * @return
	 * 
	 */
	public static long getFileSize(File f) {
		long size = 0;
		if (f.exists()) {
			if (f.isDirectory()) {
				File flist[] = f.listFiles();
				for (int i = 0; i < flist.length; i++) {
					size += getFileSize(flist[i]);
				}
			} else
				size += f.length();
		}
		return size;
	}

	/**
	 * @Description: 移动文件或者文件夹
	 * @param srcFilePath
	 *            来源文件或者文件夹
	 * @param destFilePath
	 *            目标文件或者文件夹
	 * @return void
	 * @throws Exception
	 */
	public static void move(String srcFilePath, String destFilePath)
			throws Exception {
		File srcFile = new File(srcFilePath);
		if (!srcFile.exists()) {
			return;
		}
		File destFile = new File(destFilePath);
		if (destFile.exists()) {
			destFile.delete();
		}
		String srcParent = srcFile.getParent();
		String destParent = destFile.getParent();
		boolean renameflag = false;
		if (null != srcParent && srcParent.equals(destParent)) {
			renameflag = srcFile.renameTo(destFile);
		}
		if (!renameflag) {
			// 将临时文件目录重命名为数据目录
			copyDirectory(srcFilePath, destFilePath);
			deleteDir(srcFilePath);
		}
	}

	/**
	 * Description : 镜像
	 * 
	 * @param srcPath
	 *            来源文件
	 * @param dstPath
	 *            目标文件
	 * @throws Exception
	 * 
	 */
	public static void mirror(File srcPath, File dstPath) throws Exception {
		mirrordelFile(srcPath, dstPath);
		copyDirectory(srcPath, dstPath);
	}

	// 镜像 根据目标 删除 来源的文件
	private static void mirrordelFile(File src, File dest) {
		if (!dest.exists()) {
			return;
		}
		if (!src.exists()) {
			if (dest.isDirectory()) {
				delete(dest);
			} else {
				dest.delete();
			}
			return;
		}

		if (dest.isDirectory()) {
			if (src.isFile()) {
				dest.delete();
			} else {
				File[] chilefiles = dest.listFiles();
				if (null != chilefiles && chilefiles.length > 0) {
					for (int i = 0; i < chilefiles.length; i++) {
						File dest2 = chilefiles[i];
						String filename = dest2.getName();
						String srcPath = src.getPath();
						File src2 = new File(srcPath + File.separator
								+ filename);
						mirrordelFile(src2, dest2);
					}
				}
			}
		} else {
			if (src.isDirectory()) {
				dest.delete();
			}
		}
	}

	/**
	 * Description : 使用UTF-8字符集 把字符串写入文件中
	 * 
	 * @param text
	 * @throws IOException
	 * 
	 */
	public static void writeFile(File file, String text) throws IOException {
		writeFile(file, text, CHARTSET);
	}

	/**
	 * Description : 根据字符集 把字符串写入文件中
	 * 
	 * @param text
	 * @param chartset
	 * @throws IOException
	 * 
	 */
	public static void writeFile(File file, String text, String chartset)
			throws IOException {
		if (!file.exists()) {
			file.createNewFile();
		}
		OutputStream os = new BufferedOutputStream(new FileOutputStream(file));
		byte[] b = text.getBytes(chartset);
		os.write(b);
		os.close();
	}

	public static void writeObject(String filePath, Object object) throws IOException {
		File file = new File(filePath);
		System.out.println("模型文件生成路径:"+ file.getAbsolutePath());
		if (!file.exists()) {
			file.createNewFile();
		}
		ObjectOutputStream out = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(file)));
		out.writeObject(object);
		out.close();

	}

	/**
	 * 从指定的文件中读取对象
	 * 
	 * @throws ClassNotFoundException
	 * **/
	public static Object readObject(String filePath) throws IOException,
			ClassNotFoundException {
		File file = new File(filePath);
		if (!file.exists()) {
			return null;
		}
		ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(
				new FileInputStream(file)));
		Object o = in.readObject();
		in.close();
		return o;
	}
	
	@SuppressWarnings("resource")
	public static byte[] readFile(String filePath ) throws IOException {
		File file = new File(filePath);
		if (!file.exists()) {
			return null;
		}
		FileChannel channel = new RandomAccessFile(file, "r").getChannel();
		int size = (int) channel.size();
		ByteBuffer bf = ByteBuffer.allocate(size);
		channel.read(bf);
		bf.flip();
		channel.close();
		return bf.array();
	}
	
	@SuppressWarnings("resource")
	public static void writeFile(String filePath, byte[] content ) throws IOException {
		File file = new File(filePath);
		
		FileChannel channel = new RandomAccessFile(file, "rw").getChannel();
		int size = content.length;
		ByteBuffer bf = ByteBuffer.allocate(size);
		bf.put(content);
		bf.flip();
		channel.write(bf);
		channel.close();
	}
	
	public static String getClassRootPath() {
		try {
			ClassLoader classLoader = FileHelper.class.getClassLoader();
			URL url = classLoader.getResource(File.separator);

			if (url == null)
			{
				url = System.class.getResource(File.separator);
			}
			
			String path = null;
			if (url != null)
			{
			    path = url.getPath();
			}
			else
			{
			    path = System.getProperty("user.dir");
			}
            return URLDecoder.decode(path, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	
	@SuppressWarnings("resource")
	public static void writerSerializObj(Object obj,String filePath) throws IOException{
		FileOutputStream fos = new FileOutputStream(filePath);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(obj);
	}
	
	@SuppressWarnings("resource")
	public static Object readSerializObj(String filePath) throws IOException, ClassNotFoundException{
		FileInputStream fis = new FileInputStream(filePath);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		return ois.readObject();
	}
	
	
	
	
	
}