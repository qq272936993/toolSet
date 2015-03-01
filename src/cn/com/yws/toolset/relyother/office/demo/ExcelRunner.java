package cn.com.yws.toolset.relyother.office.demo;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import cn.com.yws.toolset.relyother.office.TableHeaderProp;
import cn.com.yws.toolset.relyother.office.excel.ExcelBaseOper;
import cn.com.yws.toolset.relyother.office.excel.ExcelColumnProp;
import cn.com.yws.toolset.relyother.office.excel.ExcelGenerator;
import cn.com.yws.toolset.relyother.office.excel.ExcelSheetProp;


/**
 * <pre>
 * 文件名称: ExcelRunner.java
 * 包路径: cn.com.frm.base.office.rundemo
 * 描述:
 * 内容摘要 
 *    作者: 杨文松
 *    版本: 1.0
 *    时间: 2014年9月11日
 *    邮箱: 272936993@qq.com
 * 修改历史:  
 * 修改日期           修改人员        版本	       修改内容  		说明
 * ---------------------------------------------- 
 * 
 * </pre>
 */
public class ExcelRunner {

	
	public static void main(String[] args) {
		exportExcel2003();
	}
	
	
	public static void exportExcel2003(){
		try{
			//String headerconfig="[{name:'col1',length:1,collength:1,colheigth:2},{name:'col2',length:1,collength:3,colheigth:1}]";
			//String headerconfig="[{name:'col1',length:1,collength:1,colheigth:2},{name:'col2',length:1,collength:3,colheigth:1,subRegions:[{name:'col3',length:1,collength:1,colheigth:1},{name:'col4',length:1,collength:1,colheigth:1},{name:'col5',length:1,collength:1,colheigth:1}]}]";
			String headerconfig="[{name:'col1',length:1,collength:1,colheigth:3},{name:'col2',length:1,collength:6,colheigth:1,subRegions:[{name:'col3',length:1,collength:2,colheigth:1,subRegions:[{name:'col7',length:1,collength:1,colheigth:1},{name:'col8',length:1,collength:1,colheigth:1}]},{name:'col4',length:1,collength:3,colheigth:1,subRegions:[{name:'col9',length:1,collength:1,colheigth:1},{name:'col10',length:1,collength:1,colheigth:1},{name:'col11',length:1,collength:1,colheigth:1}]},{name:'col5',length:1,collength:1,colheigth:2}]}]";
		
			TableHeaderProp header=ExcelBaseOper.getHeaderPropFromJson(headerconfig);
			header.setContainRow(3);
			ExcelSheetProp prop=new ExcelSheetProp();
			prop.setFileext("xls");
			prop.setStartCol(1);
			prop.setStartRow(1);
			prop.setSheetName("sheet1");
			ExcelRunner test=new ExcelRunner();
			test.mockObj(prop);
		
			Workbook wb=ExcelGenerator.GenerateExcelFile(prop, header);
		    
			OutputStream out=new FileOutputStream("d:\\test.xls");
			wb.write(out);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void mockObj(ExcelSheetProp prop){
		String columnJson="[{columnCode:'column1',columnType:'01',needMerge:'false'},{columnCode:'column2',columnType:'01',needMerge:'true'}]";
		JsonConfig config=new JsonConfig();
		config.setRootClass(ExcelColumnProp.class);
		JSONArray array=JSONArray.fromObject(columnJson);
		Collection<ExcelColumnProp> col=JSONArray.toCollection(array, config);
		prop.setColumnPropList((List<ExcelColumnProp>)col);
		JsonConfig config1=new JsonConfig();
		String resultJson="[{column1:'AAA',column2:'1'},{column1:'BBB',column2:'1'},{column1:'CCC',column2:'1'},{column1:'DDD',column2:'2'},{column1:'EEE',column2:'2'}]";
		config1.setRootClass(Map.class);
		JSONArray array1=JSONArray.fromObject(resultJson);
		List<Map<String,String>> mapcol=(List<Map<String, String>>) JSONArray.toCollection(array1,config1);
		prop.setColumnList(mapcol);
	}
	
	
}
