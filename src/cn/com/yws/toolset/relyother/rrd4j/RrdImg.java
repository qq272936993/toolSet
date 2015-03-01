package cn.com.yws.toolset.relyother.rrd4j;

import java.io.IOException;
import java.io.InputStream;

import org.rrd4j.core.Util;
import org.rrd4j.graph.RrdGraphDef;

import cn.com.yws.toolset.base.common.date.DateTimeConstants;


/**
 * 文件名称: RrdImg.java
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
public abstract class RrdImg {

	private Integer width = 600;	//默认宽度
	private Integer height = 400;	//默认高度
	private String imageFormat ="png";
	private String title ="监控";
	private String verticalLabel;
	private Long nowTime = Util.getTimestamp();
	private Long startTime = nowTime - DateTimeConstants.ONE_DAY_SECONDS;
	private Long endTime = nowTime;
	protected String graphFilepath;
	protected RrdGraphDef gDef;
	
	public RrdImg(){}
	
	public RrdImg(Integer width,Integer height,String imageFormat,String title,String verticalLabel,
			Long startTime,Long endTime,String graphFilepath) throws IOException{
		if(width != null) this.width = width;
		if(height != null) this.height = height;
		if(imageFormat != null) this.imageFormat = imageFormat;
		if(title != null) this.title = title;
		if(verticalLabel != null) this.verticalLabel = verticalLabel;
		if(startTime != null) this.startTime = startTime;
		if(endTime != null) this.endTime = endTime;
		this.graphFilepath = graphFilepath == null ? (Util.getCanonicalPath(String.valueOf(endTime))+".png") : graphFilepath;
	}
	
	
	protected void setDef() throws IOException{
		gDef = new RrdGraphDef();
		gDef.setFilename(graphFilepath);
		gDef.setWidth(width);
		gDef.setHeight(height);
		gDef.setImageFormat(imageFormat);
		gDef.setTimeSpan(startTime, endTime);// 时间范围，最近一天;生成最近一天的图形
		gDef.setTitle(title);
		gDef.setVerticalLabel(verticalLabel);
	}
	
	/**
	 * 生成图片并读取图片生成二进制数据,最终将文件删除
	 * */
	public abstract byte[] graph2Bytes()throws Exception;
	
	/**
	 * 生成图片并返回输入流
	 * @return {@link InputStream} 输入流
	 * */
	public abstract InputStream graph2Stream()throws Exception;
	
	/**
	 * 生成图片并返回图片路径
	 * @return String 图片路径
	 * */
	public abstract String graphImgPath()throws Exception;
	
	
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getImageFormat() {
		return imageFormat;
	}

	public void setImageFormat(String imageFormat) {
		this.imageFormat = imageFormat;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVerticalLabel() {
		return verticalLabel;
	}

	public void setVerticalLabel(String verticalLabel) {
		this.verticalLabel = verticalLabel;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public String getGraphFilepath() {
		return graphFilepath;
	}

	public void setGraphFilepath(String graphFilepath) {
		this.graphFilepath = graphFilepath;
	}
	
}
