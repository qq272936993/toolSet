package cn.com.yws.toolset.relyother.rrd4j;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.rrd4j.ConsolFun;
import org.rrd4j.graph.RrdGraph;

import cn.com.yws.toolset.base.common.file.FileHelper;


/**
 * 文件名称: GenerateRrdImg.java
 * 包路径: cn.com.frm.rrd4j
 * 描述:
 * 内容摘要 
 *    作者: 杨文松
 *    版本: 1.0
 *    时间: 2014年1月3日
 *    邮箱: yangwensong@talkweb.com.cn
 * 修改历史:  
 * 修改日期           修改人员        版本	       修改内容  		说明
 * ---------------------------------------------- 
 * 
 */
public class GenerateCPURrdImg extends RrdImg{
	
	private String rrdFilePath;
	private String[] sourceNames;
	private Color[] colors = {Color.RED,Color.BLUE,Color.YELLOW,Color.GREEN,Color.ORANGE,Color.GRAY,Color.CYAN,Color.DARK_GRAY};
	
	
	/**
	 * Title:
	 * Description:
	 * @param width 图片宽度
	 * @param height 图片高度
	 * @param imageFormat 图片格式
	 * @param title	图片标题
	 * @param verticalLabel	图片单位
	 * @param startTime	开始时间
	 * @param endTime	结束时间
	 * @throws IOException
	 */
	public GenerateCPURrdImg(Integer width, Integer height, String imageFormat,String graphFilepath,
			String title, String verticalLabel, Long startTime, Long endTime,
			String rrdFilePath,String[] sourceNames)
			throws IOException {
		super(width, height, imageFormat, title, verticalLabel, startTime, endTime,graphFilepath);
		this.rrdFilePath = rrdFilePath;
		this.sourceNames = sourceNames;
	}
	
	public GenerateCPURrdImg(Integer width, Integer height,String verticalLabel,Long startTime, Long endTime,
			String rrdFilePath,String[] sourceNames,String graphFilepath) throws IOException{
		super(width, height, null, null, verticalLabel, startTime, endTime,graphFilepath);
		this.rrdFilePath = rrdFilePath;
		this.sourceNames = sourceNames;
	}
	
	public GenerateCPURrdImg(Integer width, Integer height,String verticalLabel,String rrdFilePath,String[] sourceNames,
			String graphFilepath) throws IOException{
		super(width, height, null, null, verticalLabel, null, null,graphFilepath);
		this.rrdFilePath = rrdFilePath;
		this.sourceNames = sourceNames;
	}
	
	public GenerateCPURrdImg(Integer width, Integer height,String title, String verticalLabel, Long startTime, Long endTime,
			String rrdFilePath,String[] sourceNames,String graphFilepath) throws IOException{
		super(width, height, null, title, verticalLabel, startTime, endTime,graphFilepath);
		this.rrdFilePath = rrdFilePath;
		this.sourceNames = sourceNames;
	}
	
	public void createImg() throws IOException{
		setDef();
		for(int i = 0; i< sourceNames.length; i++){
			gDef.datasource(sourceNames[i], rrdFilePath, sourceNames[i], ConsolFun.AVERAGE);
		}
		
		for(int i = 0; i< sourceNames.length; i++){
			gDef.line(sourceNames[i], colors[i], sourceNames[i]);
		}
		gDef.comment("\\c");
		gDef.comment("\\r");

		for(int i = 0; i< sourceNames.length; i++){
			String name = sourceNames[i];
			gDef.gprint(name, ConsolFun.AVERAGE, name+"_AVG=%-5.1lf%s ");
			gDef.gprint(name, ConsolFun.MAX, name+"_MAX=%-5.1lf%s ");
			gDef.gprint(name, ConsolFun.MIN, name+"_MIN=%-5.1lf%s ");
			
			gDef.comment("\\c");
		}
		
		gDef.setSmallFont(new Font("Monospaced", Font.PLAIN, 11));
		gDef.setLargeFont(new Font("SansSerif", Font.BOLD, 14));
		try {
			new RrdGraph(gDef);// 生成图形
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public byte[] graph2Bytes() throws IOException {
		 createImg();
		 return FileHelper.readFile(graphFilepath);
	}
	
	
	@Override
	public InputStream graph2Stream() throws IOException {
		createImg();
		return new FileInputStream(new File(graphFilepath));
	}
	
	
	
	public String getRrdFilePath() {
		return rrdFilePath;
	}

	public void setRrdFilePath(String rrdFilePath) {
		this.rrdFilePath = rrdFilePath;
	}

	/** 
	* 描述: TODO
	* @return
	* @throws Exception    
	*/
	@Override
	public String graphImgPath() throws Exception {
		createImg();
		return graphFilepath;
	}
	
}
