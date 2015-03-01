package cn.com.yws.toolset.jee.fusioncharts;

public class Chart {
	
	private String caption;		//显示的标题
	private String subcaption;	//小标题,一般显示在标题下面
	private String xaxisname;	//X轴显示的说明Label
	private String yaxisname;	//Y轴显示的说明Label
	
	private String numberprefix;	//数字格式化___前缀

	
	
	
	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getSubcaption() {
		return subcaption;
	}

	public void setSubcaption(String subcaption) {
		this.subcaption = subcaption;
	}

	public String getXaxisname() {
		return xaxisname;
	}

	public void setXaxisname(String xaxisname) {
		this.xaxisname = xaxisname;
	}

	public String getYaxisname() {
		return yaxisname;
	}

	public void setYaxisname(String yaxisname) {
		this.yaxisname = yaxisname;
	}

	public String getNumberprefix() {
		return numberprefix;
	}

	public void setNumberprefix(String numberprefix) {
		this.numberprefix = numberprefix;
	}
	
	
}
