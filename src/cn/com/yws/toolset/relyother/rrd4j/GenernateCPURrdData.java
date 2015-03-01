package cn.com.yws.toolset.relyother.rrd4j;

import org.hyperic.sigar.Cpu;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.rrd4j.core.Sample;

/**
 * 文件名称: GenernateCPURrdData.java
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
public class GenernateCPURrdData extends GenerateRrdData{

	public static String[] sourceNames = {"idle","sysCpu","useCpu","totalCpu","irq",
			"nice","siftIrq","stolen"};

	/**
	 * Title:
	 * Description:
	 * @param rrdFilePath
	 * @param sourceNames
	 */
	public GenernateCPURrdData(String rrdFilePath) {
		super(rrdFilePath, sourceNames);
	}

	@Override
	public void setSampleValue(Sample sample) {
		try {
			Sigar sigar = new Sigar();
			Cpu cpu = sigar.getCpu();
			long idle = cpu.getIdle();  //获取的总系统CPU空闲时间
			long sysCpu = cpu.getSys();	//获取的总系统CPU内核时间
			long useCpu = cpu.getUser();	// 获取的总系统CPU用户时间
			long totalCpu = cpu.getTotal();	// 获取系统的总CPU时间
			long irq = cpu.getIrq();		// 获取系统的总cpu时间服务中断
			long nice = cpu.getNice();		//获取的总系统CPU美好的时光。
			long siftIrq = cpu.getSoftIrq();	//获取系统的总cpu时间服务软中断
			long stolen = cpu.getStolen();	//获取的总系统CPU不由自主的等待时间。
			sample.setValue("idle", idle);
			sample.setValue("sysCpu", sysCpu);
			sample.setValue("useCpu", useCpu);
			sample.setValue("totalCpu", totalCpu);
			sample.setValue("irq", irq);
			sample.setValue("nice", nice);
			sample.setValue("siftIrq", siftIrq);
			sample.setValue("stolen", stolen);
		} catch (SigarException e) {
			e.printStackTrace();
		}
	}

}
