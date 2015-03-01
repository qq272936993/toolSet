package cn.com.yws.toolset.relyother.rrd4j;

import java.io.File;
import java.io.IOException;

import org.rrd4j.ConsolFun;
import org.rrd4j.DsType;
import org.rrd4j.core.RrdDb;
import org.rrd4j.core.RrdDbPool;
import org.rrd4j.core.RrdDef;
import org.rrd4j.core.Sample;

import cn.com.yws.toolset.base.common.date.DateTimeConstants;


/**
 * 文件名称: GenerateRrdData.java
 * 包路径: cn.com.frm.rrd4j
 * 描述: 生成rrd4j文件,并添加数据
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
public abstract class GenerateRrdData {

	protected String rrdFilePath;
	protected String[] sourceNames;

	private final int gatheringRange = 300;
	private DsType dsType = DsType.GAUGE;
	private long oneday_seconds = DateTimeConstants.ONE_DAY_SECONDS;
	
	public GenerateRrdData(String rrdFilePath,String[] sourceNames){
		this.rrdFilePath = rrdFilePath;
		this.sourceNames = sourceNames;
	}
	
	public void sample(){
		Sample sample = null;
		RrdDb rrdDb = null;
		try {
			File file=new File(rrdFilePath);
			if(!file.exists()) createRRD();
			
			RrdDbPool pool = RrdDbPool.getInstance();  
			rrdDb  = pool.requestRrdDb(rrdFilePath);
			
//			rrdDb = new RrdDb(file.getPath());
//			sample = rrdDb.createSample();
			sample = rrdDb.createSample();
			setSampleValue(sample);
			sample.update();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 如何赋值由子类书写
	 * @param Sample
	 * */
	public abstract void setSampleValue(Sample sample);
	
	/**
	 * 新建rrd文件
	 * */
	private void createRRD() throws IOException{
		RrdDef rrdDef = new RrdDef(rrdFilePath, gatheringRange);
		for(String name : sourceNames){
			rrdDef.addDatasource(name, dsType , 2*gatheringRange, 0, Double.NaN); //定义数据源，可以定义多个
		}
		rrdDef.addArchive(ConsolFun.AVERAGE, 0.5, 1, (int)(oneday_seconds/gatheringRange)); //输入给数据源的数据每一个都保存下来，保存288笔数据，即保存最近一天的数据
		rrdDef.addArchive(ConsolFun.AVERAGE, 0.5, 7, (int)(oneday_seconds/gatheringRange)); //每7笔数据，取平均值，然后保存，保存288笔数据，即保存最近一周的数据
		rrdDef.addArchive(ConsolFun.AVERAGE, 0.5, 30, (int)(oneday_seconds/gatheringRange)); //每30笔数据，取平均值，然后保存，保存288笔数据，即保存最近一个月的数据

		rrdDef.addArchive(ConsolFun.MAX, 0.5, 1, (int)(oneday_seconds/gatheringRange)); 
		rrdDef.addArchive(ConsolFun.MAX, 0.5, 7, (int)(oneday_seconds/gatheringRange)); 
		rrdDef.addArchive(ConsolFun.MAX, 0.5, 30, (int)(oneday_seconds/gatheringRange)); 

		rrdDef.addArchive(ConsolFun.MIN, 0.5, 1, (int)(oneday_seconds/gatheringRange)); 
		rrdDef.addArchive(ConsolFun.MIN, 0.5, 7, (int)(oneday_seconds/gatheringRange)); 
		rrdDef.addArchive(ConsolFun.MIN, 0.5, 30, (int)(oneday_seconds/gatheringRange));

		RrdDb rrdDb = new RrdDb(rrdDef);//生成RRD文件
		rrdDb.close();
	}
	
	
}
