package cn.com.yws.toolset.relyother.office.excel;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import cn.com.yws.toolset.base.common.store.Constants;
import cn.com.yws.toolset.relyother.office.TableHeaderProp;
import cn.com.yws.toolset.relyother.office.TableMergeRegion;


/**
 * <pre>
 * 文件名称: ExcelBaseOper.java
 * 包路径: cn.com.frm.base.office.excel
 * 描述:	 
 * 
 * 		依赖jar包:
 * 				json-lib.jar包
 * 
 * 内容摘要 
 *    作者: 杨文松
 *    版本: 1.0
 *    时间: 2014年9月10日
 *    邮箱: 272936993@qq.com
 * 修改历史:  
 * 修改日期           修改人员        版本	       修改内容  		说明
 * ---------------------------------------------- 
 * 
 * </pre>
 */
public class ExcelBaseOper {

	public static String TYPE_EXCEL2003 = "xls";
	public static String TYPE_EXCEL2007 = "xlsx";
	private static String defaultFontName = "Microsoft YaHei";
	
	
	/**
	 * @param paramWorkbook Workbook对象
	 * @param paramString sheet名称
	 * */
	public static Sheet createSheet(Workbook paramWorkbook,String paramString){
	
		Sheet localSheet = paramWorkbook.createSheet(paramString);
		return localSheet;
	}
	
	public static Workbook createWorkBook(ExcelSheetProp paramExcelSheetProp){
		Workbook localObject = null;
		String str = paramExcelSheetProp.getFileext();
		if(TYPE_EXCEL2003.equalsIgnoreCase(str))
			localObject = new HSSFWorkbook();
		else if(TYPE_EXCEL2007.equalsIgnoreCase(str))
			localObject = new XSSFWorkbook();
		
		return localObject;
	}
	
	public static Workbook createWorkBook(ExcelSheetProp paramExcelSheetProp,
			InputStream is) throws IOException{
		Workbook localObject = null;
		String str = paramExcelSheetProp.getFileext();
		if (TYPE_EXCEL2003.equalsIgnoreCase(str))
	      localObject = new HSSFWorkbook(is);
	    else if (TYPE_EXCEL2007.equalsIgnoreCase(str))
	      localObject = new XSSFWorkbook(is);
		
		return localObject;
	}
	
	public static CellStyle getStyle(Workbook paramWorkbook, int paramInt1,
			int paramInt2, TableMergeRegion paramTableMergeRegion, TableHeaderProp paramTableHeaderProp){
		
		CellStyle localCellStyle = paramWorkbook.createCellStyle();
		if(paramInt1 > 1)
			localCellStyle.setVerticalAlignment((short)1);
		
		localCellStyle.setAlignment((short)paramInt2);
	    localCellStyle.setBorderLeft((short)1);
	    localCellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	    localCellStyle.setBorderRight((short)1);
	    localCellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
	    localCellStyle.setBorderTop((short)1);
	    localCellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
	    localCellStyle.setBorderBottom((short)1);
	    localCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		if(paramTableHeaderProp != null){
			Font font = paramWorkbook.createFont();
			if ((paramTableHeaderProp.getFontName() != null) && (paramTableHeaderProp.getFontName().equals("")))
				font.setFontName(paramTableHeaderProp.getFontName());
			else
				font.setFontName(defaultFontName);
			
			if(paramTableHeaderProp.isBold()) font.setBoldweight((short)2);
			if(paramTableHeaderProp.isItalic()) font.setItalic(true);
			localCellStyle.setFont(font);
		}
		
		String groundColor = paramTableMergeRegion.getForeGroundColor();
		if(groundColor != null && !"".equals(groundColor)){
			int[] arrayOfInt = usingColor(groundColor);
			//localCellStyle.setFillBackgroundColor(arg0);
		     //localCellStyle.setFillForegroundColor(new XSSFColor(new Color(arrayOfInt[0], arrayOfInt[1], arrayOfInt[2])).getIndexed());
		}else
	    {
		      localCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
	    }
	    localCellStyle.setFillPattern((short)1);
		
		return localCellStyle;
	}
	
	public static CellStyle getStyle(Workbook workbook,int paramInt1, int paramInt2,
			TableHeaderProp paramTableHeaderProp){
		CellStyle localCellStyle = workbook.createCellStyle();
		if(paramInt1 > 1)
			localCellStyle.setVerticalAlignment((short) 1);
		localCellStyle.setAlignment(int2Short(paramInt2) );
		localCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	    localCellStyle.setBorderLeft((short)1);
	    localCellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	    localCellStyle.setBorderRight((short)1);
	    localCellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
	    localCellStyle.setBorderTop((short)1);
	    localCellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
	    localCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
	    if (paramTableHeaderProp != null)
	    {
	      Font localFont = workbook.createFont();
	      if ((paramTableHeaderProp.getFontName() != null) && (paramTableHeaderProp.getFontName().equals("")))
	        localFont.setFontName(paramTableHeaderProp.getFontName());
	      else
	        localFont.setFontName(defaultFontName);
	      if (paramTableHeaderProp.isBold())
	        localFont.setBoldweight((short)2);
	      if (paramTableHeaderProp.isItalic())
	        localFont.setItalic(true);
	      localCellStyle.setFont(localFont);
	    }
	    
	    localCellStyle.setFillPattern((short)1);
	    localCellStyle.setWrapText(true);
		
		return localCellStyle;
	}
	
	public  static Cell merged(Sheet sheet, Row row, String str1 , 
				int param1, int param2,int param3,int param4, 
				CellStyle cellStyle,String str2, CreationHelper creationHelper){
		Cell localCell = null;
	    CellRangeAddress localCellRangeAddress = new CellRangeAddress(param1, param3, (short)param2, (short)param4);
	    sheet.addMergedRegion(localCellRangeAddress);
	    mergeCellRegion(sheet, localCellRangeAddress, cellStyle);
	    localCell = createCell(row, param2, str2, str1, cellStyle, creationHelper);
  
	    return localCell;
	}
	
	private static void mergeCellRegion(Sheet paramSheet,CellRangeAddress cellRangeAddress,
			CellStyle cellStyle){
		for(int i = cellRangeAddress.getFirstRow(); i <= cellRangeAddress.getLastRow(); i++){
			Row localRow = getRow(paramSheet, i);
			for(int j = cellRangeAddress.getFirstColumn(); j < cellRangeAddress.getLastColumn(); j++){
				Cell localCell = CellUtil.getCell(localRow, j);
				localCell.setCellStyle(cellStyle);
			}
		}
	}
	
	public static void caculateHeaderParam(ExcelSheetProp excelSheetProp , ExcelHeaderProp excelHeaderProp){
		
		if(excelHeaderProp.getHeaderColumnList().size() != 0){
			int i = excelHeaderProp.getHeaderColumnList().size();
			int j = excelSheetProp.getStartRow() -1;
			int k = excelSheetProp.getStartCol() -1;
			for(int m = 0; m < i; m++ ){
				List<ExcelHeaderColumn> localList = excelHeaderProp.getHeaderColumnList().get(m);
				Iterator<ExcelHeaderColumn> localIterator = localList.iterator();
				while(localIterator.hasNext()){
					ExcelHeaderColumn localExcelHeaderColumn = localIterator.next();
					localExcelHeaderColumn.setStartRow(j + m);
				}
			}
		}
	}
	
	public static int[][] caculateHeaderRowStartcol(ExcelHeaderProp excelHeaderProp){
		int i = excelHeaderProp.getHeaderColumnList().size();
		int[][] arrayOfInt = new int[i][excelHeaderProp.getTotalCol()];
		for(int j = 0; j < arrayOfInt.length; j++)
			arrayOfInt[j] = new int[excelHeaderProp.getTotalCol()];
		for(int j = 0; j < i; j++){
			int k = excelHeaderProp.getHeaderColumnList().get(j).size();
			for(int m = 0; m < k; m++ )
				setValue(j, m, excelHeaderProp, arrayOfInt);
		} 
		
		return arrayOfInt;
	}
	
	private static void setValue(int param1 , int param2, ExcelHeaderProp excelHeaderProp, int[][] paramArrayOfInt){
		if(param1 == 0){
			if(param2 ==0)
				paramArrayOfInt[0][0] = 0;
			else
				paramArrayOfInt[0][param2] = ( paramArrayOfInt[0][(param2-1)] +
						((ExcelHeaderColumn)((List)excelHeaderProp.getHeaderColumnList().get(0)).get(param2 - 1)).getColSpan());
		}else{
			int i = excelHeaderProp.getHeaderColumnList().size();
			int m , n , i1 , i3;
			Object localObject;
			
			if(param2 == 0){
				int j = 0;
				int k = 0;
				m = param2 + 1; 
				for(n = 0; n < param1; n++){
					localObject = excelHeaderProp.getHeaderColumnList().get(n);
					for(i1 = 0; i1 < ((List)localObject).size(); i1++){
						 ExcelHeaderColumn localExcelHeaderColumn1 = (ExcelHeaderColumn)((List)localObject).get(i1);
						 i3 = localExcelHeaderColumn1.getRowSpan();
				            int i4 = localExcelHeaderColumn1.getColSpan();
				            if (i3 + n != i)
				              k++;
				            if (k == m)
				            {
				              paramArrayOfInt[param1][param2] = paramArrayOfInt[n][i1];
				              return;
				            }
						
					}
				}
			}else{
				List<ExcelHeaderColumn> localList1 = excelHeaderProp.getHeaderColumnList().get(param1 - 1);
				List<ExcelHeaderColumn> localList2 = excelHeaderProp.getHeaderColumnList().get(param1);
		        m = 0;
		        n = 0;
		        int[] columns = new int[param2];
		        for (i1 = 0; i1 < param2; i1++)
		        {
		          n += localList2.get(i1).getColSpan();
		          columns[i1] = n;
		        }
		        i1 = 0;
		        for (int i2 = 0; i2 < localList1.size(); i2++)
		        {
		          i3 = 0;
		          ExcelHeaderColumn localExcelHeaderColumn2 = (ExcelHeaderColumn)localList1.get(i2);
		          int i5 = localExcelHeaderColumn2.getRowSpan();
		          int i6 = localExcelHeaderColumn2.getColSpan();
		          if (i5 + param1 - 1 != i)
		          {
		            m += i6;
		            i1++;
		          }
		          for (int i7 = 0; i7 < columns.length; i7++)
		            if (columns[i7] >= m)
		              i3 = 0;
		            else
		              i3++;
		          if (m > n)
		          {
		            if (sameSpan(columns, localList1, i2, param1, i))
		            {
		              paramArrayOfInt[param1][param2] = paramArrayOfInt[(param1 - 1)][i2];
		              break;
		            }
		            paramArrayOfInt[param1][param2] = (paramArrayOfInt[param1][(param2 - 1)] + ((ExcelHeaderColumn)((List)excelHeaderProp.getHeaderColumnList().get(param1)).get(param2 - 1)).getColSpan());
		            break;
		          }
		          if ((m == n) && (i2 < localList1.size() - 1))
		          {
		        	  	int i7;
			            for ( i7 = i2 + 1; (excelHeaderProp.getHeaderColumnList().get(param1 - 1).get(i7)).getRowSpan() + param1 - 1 == i; i7++);
			            	paramArrayOfInt[param1][param2] = paramArrayOfInt[(param1 - 1)][i7];
			            break;
		          }
		        }
		        if (paramArrayOfInt[param1][param2] == 0)
		          paramArrayOfInt[param1][param2] = (paramArrayOfInt[param1][(param2 - 1)] + ((ExcelHeaderColumn)((List)excelHeaderProp.getHeaderColumnList().get(param1)).get(param2 - 1)).getColSpan());
		 
			}
		}
	}
	
	private static boolean sameSpan(int[] paramArrayOfInt, List<ExcelHeaderColumn> paramList,
			int param1, int param2, int param3){
		
		int i = 0;
		boolean bool = false;
		int j = paramArrayOfInt[paramArrayOfInt.length -1];
		for(int k = 0; k < param1; k ++){
			ExcelHeaderColumn excelHeaderColumn = paramList.get(k);
			int m = excelHeaderColumn.getRowSpan();
			int n = excelHeaderColumn.getColSpan();
			if(m + param2 - 1 != param3)
				i += n;
		}
		if(i == j) bool = true;
		
		return bool;
	}
	
	
	

	public static Row createRow(Sheet sheet, int paramInt){
		return sheet.createRow(paramInt);
	}
	
	public static Row getRow(Sheet paramSheet, int paramInt){
		return paramSheet.getRow(paramInt);
	}
	
	public static Cell createCell(Row row, int paramInt){
		return row.createCell(paramInt);
	}
	
	private static short int2Short(int paramInt){
		return (short)paramInt;
	}
	
	public static Cell createCell(Row row, int paramInt, String paramString1,
			String paramString2,CellStyle paramCellStyle, CreationHelper paramCreationHelper){
		Cell localCell = null;
		if(paramString2.equals(Constants.META_TYPE_STRING))
			createStringCell(row, paramInt,  paramCellStyle, paramCreationHelper,paramString1);
		else if (paramString2.equals(Constants.META_TYPE_NUMERIC))
	    {
	      if (!"".equals(paramString1))
	        localCell = createNumberCell(row, paramInt, paramCellStyle, paramCreationHelper, Double.parseDouble(paramString1));
	    }
	    else if (paramString2.equals(Constants.META_TYPE_INTEGER))
	    {
	      if (!"".equals(paramString1))
	        localCell = createIntegerCell(row, paramInt, paramCellStyle, paramCreationHelper, Integer.parseInt(paramString1));
	    }
	    else if (paramString2.equals(Constants.META_TYPE_DATE))
	    {
	      if (!"".equals(paramString1))
	        localCell = createDateCell(row, paramInt, paramCellStyle, paramCreationHelper, paramString1);
	    }
	    else
	      localCell = createStringCell(row, paramInt, paramCellStyle, paramCreationHelper, paramString1);
	
		return localCell;
	}
	
	public static Cell createStringCell(Row row,int paramInt,CellStyle cellStyle,
			CreationHelper paramCreationHelper, String paramString){
		 Cell localCell = row.createCell(paramInt);
	    String str = "";
	    if (paramString != null)
	      str = paramString.toString();
	    localCell.setCellValue(str);
	    localCell.setCellStyle(cellStyle);
	    return localCell;
	}
	
	  private static Cell createNumberCell(Row paramRow, int paramInt, CellStyle paramCellStyle, CreationHelper paramCreationHelper, double paramDouble)
	  {
	    Cell localCell = paramRow.createCell(paramInt);
	    paramCellStyle.setDataFormat(paramCreationHelper.createDataFormat().getFormat("#,##0.0"));
	    localCell.setCellValue(paramDouble);
	    localCell.setCellStyle(paramCellStyle);
	    return localCell;
	  }

	  private static Cell createIntegerCell(Row paramRow, int paramInt1, CellStyle paramCellStyle, CreationHelper paramCreationHelper, int paramInt2)
	  {
	    Cell localCell = paramRow.createCell(paramInt1);
	    paramCellStyle.setDataFormat(paramCreationHelper.createDataFormat().getFormat("#,##0"));
	    localCell.setCellValue(paramInt2);
	    localCell.setCellStyle(paramCellStyle);
	    return localCell;
	  }
	
	  
	  private static Cell createDateCell(Row paramRow, int paramInt, CellStyle paramCellStyle, CreationHelper paramCreationHelper, String paramString)
	  {
	    Cell localCell = paramRow.createCell(paramInt);
	    paramCellStyle.setDataFormat(paramCreationHelper.createDataFormat().getFormat("yyyy-MM-dd hh:mm:ss"));
	    localCell.setCellValue(paramString);
	    localCell.setCellStyle(paramCellStyle);
	    return localCell;
	  }
			  
		
	  public static TableHeaderProp getHeaderPropFromJson(String paramString)
	  {
	    TableHeaderProp localExcelHeaderProp = new TableHeaderProp();
	    JSONArray localJSONArray = JSONArray.fromObject(paramString);
	    JsonConfig localJsonConfig = new JsonConfig();
	    localJsonConfig.setRootClass(ExcelMergeRegion.class);
	    HashMap localHashMap = new HashMap();
	    localHashMap.put("subRegions", ExcelMergeRegion.class);
	    localJsonConfig.setClassMap(localHashMap);
	    Collection localCollection = JSONArray.toCollection(localJSONArray, localJsonConfig);
	    localExcelHeaderProp.setHearderList((List)localCollection);
	    return localExcelHeaderProp;
	  }
	  
	
	public static int[] usingColor(String paramString){
		int[] arrayOfInt = new int[3];
		if(paramString.length() != 6)
			paramString ="D6D3CE";
		String str4 = paramString.toUpperCase();
		String str1 = str4.substring(0, 2);
		String str2 = str4.substring(2, 4);
		String str3 = str4.substring(4, 6);
		
		try {
			arrayOfInt[0] = Integer.parseInt(str1 , 16);
			arrayOfInt[1] = Integer.parseInt(str2 , 16);
			arrayOfInt[2] = Integer.parseInt(str3 , 16);
			for(int i=0 ; i < arrayOfInt.length; i++ )
				if(arrayOfInt[i] > 255)
					arrayOfInt[i] = 255;
			System.out.println(" using color" + arrayOfInt[0] + "," + arrayOfInt[1] + "," + arrayOfInt[2]);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return arrayOfInt;
	}
	  
	  
	
	public static void main(String[] args) {
		System.out.println("hello world");
	}
	
}
