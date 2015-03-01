package cn.com.yws.toolset.relyother.office.excel;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.com.yws.toolset.base.common.store.Constants;
import cn.com.yws.toolset.relyother.office.DataTypeEnum;
import cn.com.yws.toolset.relyother.office.MergeCellUtil;
import cn.com.yws.toolset.relyother.office.TableHeaderColumn;
import cn.com.yws.toolset.relyother.office.TableHeaderProp;
import cn.com.yws.toolset.relyother.office.TableMergeRegion;


/**
 * <pre>
 * 文件名称: ExcelGenerator.java
 * 包路径: cn.com.frm.base.office.excel
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
public class ExcelGenerator {

	  public static void ReadExcelFile(String paramString, ExcelSheetProp paramExcelSheetProp)
	    throws Exception
	  {
	    FileInputStream localFileInputStream = new FileInputStream(paramString);
	    try
	    {
	      ReadExcelFile(localFileInputStream, paramExcelSheetProp);
	    }
	    catch (Exception localException)
	    {
	      localException.printStackTrace();
	      throw new Exception("读入文件失败!");
	    }
	  }

	  public static void ReadExcelFile(InputStream paramInputStream, ExcelSheetProp paramExcelSheetProp)
	    throws Exception
	  {
	    boolean bool = ExcelBaseOper.TYPE_EXCEL2007.equalsIgnoreCase(paramExcelSheetProp.getFileext());
	    Object localObject1 = null;
	    if (bool)
	      localObject1 = new XSSFWorkbook(paramInputStream);
	    else
	      localObject1 = new HSSFWorkbook(paramInputStream);
	    Sheet localSheet = ((Workbook)localObject1).getSheetAt(0);
	    ArrayList localArrayList = new ArrayList();
	    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	    int i = 0;
	    Iterator localIterator = localSheet.rowIterator();
	    while (localIterator.hasNext())
	    {
	      i++;
	      if (i < paramExcelSheetProp.getStartRow())
	      {
	        localIterator.next();
	      }
	      else
	      {
	        HashMap localHashMap = new HashMap();
	        Row localRow = (Row)localIterator.next();
	        int j = 0;
	        int k = paramExcelSheetProp.getStartCol() + paramExcelSheetProp.getColumnPropList().size() - 1;
	        int m = 0;
	        for (int n = paramExcelSheetProp.getStartCol() - 1; n < k; n++)
	        {
	          String str1 = ((ExcelColumnProp)paramExcelSheetProp.getColumnPropList().get(n)).getColumnType();
	          Cell localCell = localRow.getCell(n);
	          String str2 = "";
	          if (localCell != null)
	            switch (localCell.getCellType())
	            {
	            case 0:
	              double d;
	              Object localObject2;
	              if ((HSSFDateUtil.isCellDateFormatted(localCell)) || (str1.equals(Constants.META_TYPE_DATE)))
	              {
	                d = localCell.getNumericCellValue();
	                localObject2 = HSSFDateUtil.getJavaDate(d);
	                str2 = localSimpleDateFormat.format((Date)localObject2);
	              }
	              else if ((str1.equals(Constants.META_TYPE_INTEGER)) || (str1.equals(Constants.META_TYPE_INTEGER)) || (str1.equals(Constants.META_TYPE_DOUBLE)))
	              {
	                if (str1.equals(Constants.META_TYPE_INTEGER))
	                  str2 = String.valueOf((int)localCell.getNumericCellValue());
	                else
	                  str2 = String.valueOf(localCell.getNumericCellValue());
	              }
	              else if (str1.equals(Constants.META_TYPE_STRING))
	              {
	                d = localCell.getNumericCellValue();
	                localObject2 = new DecimalFormat("#.#");
	                str2 = ((DecimalFormat)localObject2).format(d);
	              }
	              break;
	            case 1:
	              str2 = localCell.getStringCellValue();
	              break;
	            case 4:
	              str2 = String.valueOf(localCell.getBooleanCellValue());
	              break;
	            case 2:
	            case 3:
	            default:
	              str2 = "";
	            }
	          if ((str2 != null) && (!"".equals(str2.trim())))
	            m = 1;
	          localHashMap.put(((ExcelColumnProp)paramExcelSheetProp.getColumnPropList().get(j)).getColumnCode(), str2);
	          j++;
	        }
	        if (m != 0)
	          localArrayList.add(localHashMap);
	      }
	    }
	    paramExcelSheetProp.setColumnList(localArrayList);
	  }

	  public static int ReadExcelFile(InputStream paramInputStream, Map<String, DataTypeEnum> paramMap, String paramString1, String paramString2, ExcelSheetProp paramExcelSheetProp)
	  {
	    int i = 0;
	    ArrayList localArrayList1 = new ArrayList();
	    try
	    {
	      boolean bool = ExcelBaseOper.TYPE_EXCEL2007.equalsIgnoreCase(paramExcelSheetProp.getFileext());
	      Object localObject1 = null;
	      if (bool)
	        localObject1 = new XSSFWorkbook(paramInputStream);
	      else
	        localObject1 = new HSSFWorkbook(paramInputStream);
	      Sheet localSheet = ((Workbook)localObject1).getSheetAt(0);
	      SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	      ArrayList localArrayList2 = new ArrayList();
	      ArrayList localArrayList3 = new ArrayList();
	      int[] arrayOfInt = _$1(paramString1, paramString2);
	      System.out.println(arrayOfInt[2] + "  " + arrayOfInt[3]);
	      int j = arrayOfInt[0];
	      int k = arrayOfInt[1];
	      int m = arrayOfInt[2];
	      int n = arrayOfInt[3];
	      Iterator localIterator = localSheet.rowIterator();
	      while (localIterator.hasNext())
	      {
	        i++;
	        Row localRow = (Row)localIterator.next();
	        if (i == m)
	        {
	          for (int i1 = j - 1; i1 < k; i1++)
	          {
	            Cell localCell = localRow.getCell(i1);
	            String str1 = localCell.getStringCellValue();
	            DataTypeEnum localDataTypeEnum1 = (DataTypeEnum)paramMap.get(str1.toUpperCase());
	            if (localDataTypeEnum1 == null)
	              localDataTypeEnum1 = (DataTypeEnum)paramMap.get(str1.toLowerCase());
	            if (localDataTypeEnum1 != null)
	            {
	              localArrayList3.add(Integer.valueOf(i1));
	              localArrayList2.add(localDataTypeEnum1);
	            }
	          }
	        }
	        else if (i >= m)
	        {
	          if (i > n)
	            break;
	          HashMap localHashMap = new HashMap();
	          int i2 = 0;
	          for (int i3 = 0; i3 < localArrayList3.size(); i3++)
	          {
	            int i4 = ((Integer)localArrayList3.get(i3)).intValue();
	            DataTypeEnum localDataTypeEnum2 = (DataTypeEnum)localArrayList2.get(i3);
	            String str2 = localDataTypeEnum2.getDataType();
	            HSSFCell localHSSFCell = (HSSFCell)localRow.getCell(i4);
	            String str3 = "";
	            if (localHSSFCell != null)
	            {
	              double d;
	              Object localObject2;
	              switch (localHSSFCell.getCellType())
	              {
	              case 0:
	                if ((HSSFDateUtil.isCellDateFormatted(localHSSFCell)) || (str2.equals(String.valueOf(Constants.FIELD_TYPE_DATE))))
	                {
	                  d = localHSSFCell.getNumericCellValue();
	                  localObject2 = HSSFDateUtil.getJavaDate(d);
	                  str3 = localSimpleDateFormat.format((Date)localObject2);
	                }
	                else if (str2.equals(Constants.META_TYPE_NUMERIC))
	                {
	                  str3 = String.valueOf(localHSSFCell.getNumericCellValue());
	                }
	                else if (str2.equals(Constants.META_TYPE_STRING))
	                {
	                  d = localHSSFCell.getNumericCellValue();
	                  localObject2 = String.valueOf(Double.valueOf(d).intValue());
	                  if ((localObject2 != null) && (!"".equals(((String)localObject2).trim())))
	                    str3 = String.valueOf(Double.valueOf(d).intValue());
	                }
	                break;
	              case 1:
	                if (str2.equals(Constants.META_TYPE_NUMERIC))
	                {
	                  str3 = localHSSFCell.getStringCellValue();
	                  d = Double.valueOf(str3).doubleValue();
	                }
	                else if (str2.equals(Constants.META_TYPE_DATE))
	                {
	                  d = localHSSFCell.getNumericCellValue();
	                  localObject2 = HSSFDateUtil.getJavaDate(d);
	                }
	                else if (str2.equals(Constants.META_TYPE_STRING))
	                {
	                  str3 = localHSSFCell.getStringCellValue();
	                }
	                break;
	              case 4:
	                str3 = String.valueOf(localHSSFCell.getBooleanCellValue());
	                break;
	              case 2:
	              case 3:
	              default:
	                str3 = "";
	              }
	            }
	            if ((str3 != null) && (!"".equals(str3.trim())))
	              i2 = 1;
	            localHashMap.put(localDataTypeEnum2.getName(), str3);
	          }
	          if (i2 != 0)
	            localArrayList1.add(localHashMap);
	        }
	      }
	    }
	    catch (Exception localException)
	    {
	      localException.printStackTrace();
	      System.out.println("pos=" + i + "column size=" + localArrayList1.size());
	    }
	    paramExcelSheetProp.setColumnList(localArrayList1);
	    return i;
	  }

	  public static void readExcelHeader(InputStream paramInputStream, ExcelSheetProp paramExcelSheetProp)
	  {
	  }

	  public int getAvaiableRows(InputStream paramInputStream, int paramInt)
	    throws Exception
	  {
	    HSSFWorkbook localHSSFWorkbook = new HSSFWorkbook(paramInputStream);
	    HSSFSheet localHSSFSheet = localHSSFWorkbook.getSheetAt(0);
	    int i = localHSSFSheet.getLastRowNum();
	    return i - paramInt;
	  }

	  @Deprecated
	  public static HSSFWorkbook GenerateExcelFile(ExcelSheetProp paramExcelSheetProp, String paramString)
	  {
	    HSSFWorkbook localHSSFWorkbook = new HSSFWorkbook();
	    String str = paramExcelSheetProp.getSheetName();
	    if ((paramString != null) && (paramString.length() > 0))
	      str = str + "_" + paramString;
	    HSSFSheet localHSSFSheet = localHSSFWorkbook.createSheet(str);
	    _$1(localHSSFSheet, localHSSFWorkbook, paramExcelSheetProp);
	    _$1(localHSSFWorkbook, localHSSFSheet, paramExcelSheetProp);
	    return localHSSFWorkbook;
	  }

	  public static Workbook GenerateExcelFile(ExcelSheetProp paramExcelSheetProp, TableHeaderProp paramTableHeaderProp)
	    throws Exception
	  {
	    Workbook localWorkbook = ExcelBaseOper.createWorkBook(paramExcelSheetProp);
	    String str = paramExcelSheetProp.getSheetName();
	    Sheet localSheet = localWorkbook.createSheet(str);
	    CreationHelper localCreationHelper = localWorkbook.getCreationHelper();
	    int i = 0;
	    if (paramTableHeaderProp == null)
	    {
	      _$1(localSheet, localWorkbook, paramExcelSheetProp, paramTableHeaderProp);
	      i = paramExcelSheetProp.getColumnList().size();
	    }
	    else
	    {
	      int j = paramTableHeaderProp.getContainRow();
	      i = _$1(localSheet, localWorkbook, paramExcelSheetProp, paramTableHeaderProp, localCreationHelper);
	    }
	    _$1(localWorkbook, localSheet, paramExcelSheetProp, paramTableHeaderProp, localCreationHelper);
	    _$1(localSheet, i);
	    return localWorkbook;
	  }

	  private static void _$1(Workbook paramWorkbook, ExcelSheetProp paramExcelSheetProp, TableHeaderProp paramTableHeaderProp)
	    throws Exception
	  {
	    String str = paramExcelSheetProp.getSheetName();
	    Sheet localSheet = paramWorkbook.createSheet(str);
	    CreationHelper localCreationHelper = paramWorkbook.getCreationHelper();
	    int i = 0;
	    if (paramTableHeaderProp == null)
	    {
	      _$1(localSheet, paramWorkbook, paramExcelSheetProp, paramTableHeaderProp);
	      i = paramExcelSheetProp.getColumnList().size();
	    }
	    else
	    {
	      int j = paramTableHeaderProp.getContainRow();
	      int k = paramTableHeaderProp.getContainRow();
	      i = _$1(localSheet, paramWorkbook, paramExcelSheetProp, paramTableHeaderProp, localCreationHelper);
	      paramTableHeaderProp.setContainRow(k);
	    }
	    _$1(paramWorkbook, localSheet, paramExcelSheetProp, paramTableHeaderProp, localCreationHelper);
	    _$1(localSheet, i);
	  }

	  public static Workbook GenerateExcelFileWithMutilSheet(List<ExcelProperty> paramList)
	    throws Exception
	  {
	    Workbook localWorkbook = ExcelBaseOper.createWorkBook(((ExcelProperty)paramList.get(0)).getSheetProp());
	    Iterator localIterator = paramList.iterator();
	    while (localIterator.hasNext())
	    {
	      ExcelProperty localExcelProperty = (ExcelProperty)localIterator.next();
	      _$1(localWorkbook, localExcelProperty.getSheetProp(), localExcelProperty.getTableProp());
	    }
	    return localWorkbook;
	  }

	  @Deprecated
	  private static void _$1(HSSFSheet paramHSSFSheet, HSSFWorkbook paramHSSFWorkbook, ExcelSheetProp paramExcelSheetProp)
	  {
	    HSSFRow localHSSFRow = paramHSSFSheet.createRow(0);
	    for (int i = 0; i < paramExcelSheetProp.getHeaderName().length; i++)
	    {
	      String str = paramExcelSheetProp.getHeaderName()[i];
	      if ((str != null) && (!"".equals(str)))
	      {
	        HSSFCell localHSSFCell = localHSSFRow.createCell(i);
	        HSSFCellStyle localHSSFCellStyle = paramHSSFWorkbook.createCellStyle();
	        localHSSFCellStyle.setAlignment((short)2);
	        localHSSFCell.setCellValue(str);
	        localHSSFCell.setCellStyle(localHSSFCellStyle);
	      }
	    }
	  }

	  private static void _$1(Sheet paramSheet, Workbook paramWorkbook, ExcelSheetProp paramExcelSheetProp, TableHeaderProp paramTableHeaderProp)
	  {
	    Row localRow = paramSheet.createRow(paramExcelSheetProp.getStartRow() - 1);
	    for (int i = 0; i < paramExcelSheetProp.getColumnPropList().size(); i++)
	    {
	      String str = ((ExcelColumnProp)paramExcelSheetProp.getColumnPropList().get(i)).getColumnName();
	      if ((str != null) && (!"".equals(str)))
	      {
	        Cell localCell = localRow.createCell(i);
	        CellStyle localCellStyle = ExcelBaseOper.getStyle(paramWorkbook, 1, 2, paramTableHeaderProp);
	        localCell.setCellStyle(localCellStyle);
	        localCell.setCellValue(str);
	      }
	    }
	  }

	  private static int _$1(Sheet paramSheet, Workbook paramWorkbook, ExcelSheetProp paramExcelSheetProp, TableHeaderProp paramTableHeaderProp, CreationHelper paramCreationHelper)
	  {
	    int i = paramExcelSheetProp.getStartCol() - 1;
	    int j = paramExcelSheetProp.getStartRow() - 1;
	    int k = paramTableHeaderProp.getContainRow();
	    Row[] arrayOfRow = new Row[k];
	    int m = i;
	    for (int n = 0; n < k; n++)
	      arrayOfRow[n] = ExcelBaseOper.createRow(paramSheet, j + n);
	    Object localObject;
	    if (paramTableHeaderProp.getHearderList().size() != 0)
	    {
	      for (int n = 0; n < paramTableHeaderProp.getHearderList().size(); n++)
	      {
	        TableMergeRegion localTableMergeRegion1 = (TableMergeRegion)paramTableHeaderProp.getHearderList().get(n);
	        localObject = localTableMergeRegion1.getName();
	        _$1(paramSheet, paramWorkbook, paramExcelSheetProp, paramTableHeaderProp, localTableMergeRegion1, arrayOfRow, paramCreationHelper, 0, m, (String)localObject);
	        m += localTableMergeRegion1.getColLength();
	      }
	    }
	    else if (paramTableHeaderProp.getHeaderColumnList().size() != 0)
	    {
	      int[][] arrayOfInt = MergeCellUtil.caculateHeaderRowStartcol(paramTableHeaderProp);
	      m = paramTableHeaderProp.getHeaderColumnList().size();
	      for (int i2 = 0; i2 < paramTableHeaderProp.getHeaderColumnList().size(); i2++)
	      {
	        localObject = (List)paramTableHeaderProp.getHeaderColumnList().get(i2);
	        for (int i3 = 0; i3 < ((List)localObject).size(); i3++)
	        {
	          TableHeaderColumn localTableHeaderColumn = (TableHeaderColumn)((List)localObject).get(i3);
	          localTableHeaderColumn.setStartCol(arrayOfInt[i2][i3]);
	          _$1(paramSheet, paramWorkbook, paramTableHeaderProp, (TableHeaderColumn)((List)paramTableHeaderProp.getHeaderColumnList().get(i2)).get(i3), arrayOfRow, paramCreationHelper);
	        }
	      }
	    }
	    for (int i1 = 0; i1 < paramTableHeaderProp.getHearderList().size(); i1++)
	    {
	      TableMergeRegion localTableMergeRegion2 = (TableMergeRegion)paramTableHeaderProp.getHearderList().get(i1);
	      _$1(paramSheet, paramWorkbook, paramTableHeaderProp, localTableMergeRegion2, arrayOfRow, paramCreationHelper);
	    }
	    return m;
	  }

	  private static void _$1(Sheet paramSheet, Workbook paramWorkbook, ExcelSheetProp paramExcelSheetProp, TableHeaderProp paramTableHeaderProp, TableMergeRegion paramTableMergeRegion, Row[] paramArrayOfRow, CreationHelper paramCreationHelper, int paramInt1, int paramInt2, String paramString)
	  {
	    String str = paramTableMergeRegion.getName();
	    Row localRow = paramArrayOfRow[paramInt1];
	    int i = localRow.getRowNum();
	    if ((str != null) && (!"".equals(str)))
	    {
	      CellStyle localCellStyle;
	      if (paramTableMergeRegion.getSubRegions().size() == 0)
	      {
	        if ((paramTableMergeRegion.getColHeigth() == 1) && (paramTableMergeRegion.getColLength() == 1))
	        {
	          localCellStyle = ExcelBaseOper.getStyle(paramWorkbook, 1, 2, paramTableMergeRegion, paramTableHeaderProp);
	          Cell localCell = ExcelBaseOper.createCell(localRow, paramInt2, paramString, Constants.META_TYPE_STRING, localCellStyle, paramCreationHelper);
	          localCell.setCellValue(str);
	        }
	        else
	        {
	          localCellStyle = ExcelBaseOper.getStyle(paramWorkbook, 3, 2, paramTableMergeRegion, paramTableHeaderProp);
	          ExcelBaseOper.merged(paramSheet, localRow, Constants.META_TYPE_STRING, i, paramInt2, i + paramTableMergeRegion.getColHeigth() - 1, paramInt2 + paramTableMergeRegion.getColLength() - 1, localCellStyle, paramString, paramCreationHelper);
	        }
	      }
	      else
	      {
	        localCellStyle = ExcelBaseOper.getStyle(paramWorkbook, 3, 2, paramTableMergeRegion, paramTableHeaderProp);
	        ExcelBaseOper.merged(paramSheet, localRow, Constants.META_TYPE_STRING, i, paramInt2, i + paramTableMergeRegion.getColHeigth() - 1, paramInt2 + paramTableMergeRegion.getColLength() - 1, localCellStyle, paramString, paramCreationHelper);
	      }
	    }
	    int j = paramInt2;
	    if (paramTableMergeRegion.getSubRegions().size() != 0)
	      for (int k = 0; k < paramTableMergeRegion.getSubRegions().size(); k++)
	      {
	        TableMergeRegion localTableMergeRegion = (TableMergeRegion)paramTableMergeRegion.getSubRegions().get(k);
	        _$1(paramSheet, paramWorkbook, paramExcelSheetProp, paramTableHeaderProp, localTableMergeRegion, paramArrayOfRow, paramCreationHelper, paramInt1 + 1, j, localTableMergeRegion.getName());
	        j += localTableMergeRegion.getColLength();
	      }
	  }

	  private static void _$1(Sheet paramSheet, Workbook paramWorkbook, TableHeaderProp paramTableHeaderProp, TableHeaderColumn paramTableHeaderColumn, Row[] paramArrayOfRow, CreationHelper paramCreationHelper)
	  {
	    CellStyle localCellStyle = null;
	    Row localRow = paramArrayOfRow[paramTableHeaderColumn.getStartRow()];
	    if ((paramTableHeaderColumn.getRowSpan() > 1) || (paramTableHeaderColumn.getColSpan() > 1))
	    {
	      localCellStyle = ExcelBaseOper.getStyle(paramWorkbook, 3, 2, paramTableHeaderProp);
	      ExcelBaseOper.merged(paramSheet, localRow, Constants.META_TYPE_STRING, paramTableHeaderColumn.getStartRow(), paramTableHeaderColumn.getStartCol(), paramTableHeaderColumn.getStartRow() + paramTableHeaderColumn.getRowSpan() - 1, paramTableHeaderColumn.getStartCol() + paramTableHeaderColumn.getColSpan() - 1, localCellStyle, paramTableHeaderColumn.getColumnName(), paramCreationHelper);
	    }
	    else
	    {
	      localCellStyle = ExcelBaseOper.getStyle(paramWorkbook, 1, 2, paramTableHeaderProp);
	      ExcelBaseOper.createCell(localRow, paramTableHeaderColumn.getStartCol(), paramTableHeaderColumn.getColumnName(), Constants.META_TYPE_STRING, localCellStyle, paramCreationHelper);
	    }
	  }

	  private static void _$1(Sheet paramSheet, Workbook paramWorkbook, TableHeaderProp paramTableHeaderProp, TableMergeRegion paramTableMergeRegion, Row[] paramArrayOfRow, CreationHelper paramCreationHelper)
	  {
	    CellStyle localCellStyle = null;
	    Row localRow = paramArrayOfRow[paramTableMergeRegion.getStartRow()];
	    if ((paramTableMergeRegion.getColHeigth() > 1) || (paramTableMergeRegion.getColLength() > 1))
	    {
	      localCellStyle = ExcelBaseOper.getStyle(paramWorkbook, 3, 2, paramTableHeaderProp);
	      ExcelBaseOper.merged(paramSheet, localRow, Constants.META_TYPE_STRING, paramTableMergeRegion.getStartRow(), paramTableMergeRegion.getStartCol(), paramTableMergeRegion.getStartRow() + paramTableMergeRegion.getColHeigth() - 1, paramTableMergeRegion.getStartCol() + paramTableMergeRegion.getColLength() - 1, localCellStyle, paramTableMergeRegion.getName(), paramCreationHelper);
	    }
	    else
	    {
	      localCellStyle = ExcelBaseOper.getStyle(paramWorkbook, 1, 2, paramTableHeaderProp);
	      ExcelBaseOper.createCell(localRow, paramTableMergeRegion.getStartCol(), paramTableMergeRegion.getName(), Constants.META_TYPE_STRING, localCellStyle, paramCreationHelper);
	    }
	  }

	  private static void _$1(Workbook paramWorkbook, Sheet paramSheet, ExcelSheetProp paramExcelSheetProp, TableHeaderProp paramTableHeaderProp, CreationHelper paramCreationHelper)
	    throws Exception
	  {
	    try
	    {
	      int i = 1;
	      if (paramTableHeaderProp != null)
	        i = paramTableHeaderProp.getContainRow();
	      else
	        throw new Exception("Excel Header is null");
	      int j = paramExcelSheetProp.getStartRow() + paramTableHeaderProp.getHeaderRows() - 1;
	      int k = paramExcelSheetProp.getStartCol() - 1;
	      int size = paramExcelSheetProp.getColumnPropList().size();
	      String[] arrayOfString = new String[size];
	      int[] arrayOfInt = new int[size];
	      boolean[] arrayOfBoolean = new boolean[size];
	      CellStyle localCellStyle1 = ExcelBaseOper.getStyle(paramWorkbook, 1, 2, paramTableHeaderProp);
	      CellStyle localCellStyle2 = ExcelBaseOper.getStyle(paramWorkbook, 2, 2, paramTableHeaderProp);
	      for (int m = 0; m < size; m++)
	        arrayOfInt[m] = -1;
	      if (paramExcelSheetProp.getColumnPropList().size() != 0)
	      {
	        List localList = paramExcelSheetProp.getColumnList();
	        for (int n = 0; n < localList.size(); n++)
	        {
	          Map localMap = (Map)localList.get(n);
	          Row localRow = ExcelBaseOper.createRow(paramSheet, j + n);
	          ExcelColumnProp localExcelColumnProp;
	          String str5;
	          String str7;
	          for (int str2 = 0; str2 < paramExcelSheetProp.getColumnPropList().size(); str2++)
	          {
	            localExcelColumnProp = (ExcelColumnProp)paramExcelSheetProp.getColumnPropList().get(str2);
	            String str3 = localExcelColumnProp.getColumnCode();
	            str5 = localExcelColumnProp.getColumnType();
	            boolean bool3 = localExcelColumnProp.isNeedMerge();
	            str7 = ((String)localMap.get(str3)).toString();
	            if (str7 == null)
	              str7 = (String)localMap.get(str3.toUpperCase());
	            if (str7 == null)
	              str7 = (String)localMap.get(str3.toLowerCase());
	            if (bool3)
	            {
	              if (_$1(localList, n, str3))
	              {
	                arrayOfString[str2] = str7;
	                if (arrayOfInt[str2] == -1)
	                  arrayOfInt[str2] = n;
	              }
	              else if ((arrayOfString[str2] != null) && (!"".equalsIgnoreCase(arrayOfString[str2].trim())))
	              {
	                if (arrayOfInt[str2] != -1)
	                {
	                  ExcelBaseOper.merged(paramSheet, ExcelBaseOper.getRow(paramSheet, arrayOfInt[str2] + j), str5, arrayOfInt[str2] + j, k + str2, n + j, k + str2, localCellStyle2, str7, paramCreationHelper);
	                  arrayOfInt[str2] = -1;
	                  arrayOfString[str2] = "";
	                  arrayOfBoolean[str2] = true;
	                }
	                else
	                {
	                  ExcelBaseOper.createCell(localRow, str2, str7, str5, localCellStyle1, paramCreationHelper);
	                }
	              }
	              else
	              {
	                ExcelBaseOper.createCell(localRow, str2, str7, str5, localCellStyle1, paramCreationHelper);
	              }
	            }
	            else
	              ExcelBaseOper.createCell(localRow, str2, str7, str5, localCellStyle1, paramCreationHelper);
	          }
	          for (int s2 = 0; s2 < size; s2++)
	          {
	            localExcelColumnProp = (ExcelColumnProp)paramExcelSheetProp.getColumnPropList().get(s2);
	            boolean bool2 = localExcelColumnProp.isNeedMerge();
	            str5 = localExcelColumnProp.getColumnType();
	            if (bool2)
	            {
	              boolean i1 = arrayOfBoolean[s2];
	              if (i1)
	              {
	                for (int s7 = s2; s7 < size; s7++)
	                {
	                  boolean bool4 = ((ExcelColumnProp)paramExcelSheetProp.getColumnPropList().get(s7)).isNeedMerge();
	                  if ((bool4) && (arrayOfString[s7] != null) && (!"".equals(arrayOfString[s7].trim())) && (arrayOfInt[s7] != -1))
	                  {
	                    if (arrayOfInt[s7] != n)
	                      ExcelBaseOper.merged(paramSheet, ExcelBaseOper.getRow(paramSheet, arrayOfInt[s7] + j), str5, arrayOfInt[s7] + j, k + s2, n + j, k + s2, localCellStyle2, arrayOfString[s7], paramCreationHelper);
	                    else
	                      ExcelBaseOper.createCell(ExcelBaseOper.getRow(paramSheet, arrayOfInt[s7] + j), s7, arrayOfString[s7], str5, localCellStyle1, paramCreationHelper);
	                    arrayOfInt[s7] = -1;
	                    arrayOfString[s7] = "";
	                  }
	                }
	                arrayOfBoolean[s2] = false;
	                break;
	              }
	            }
	          }
	          if (n == paramExcelSheetProp.getColumnList().size() - 1)
	            for (int s2 = 0; s2 < paramExcelSheetProp.getColumnPropList().size(); s2++)
	            {
	              boolean bool1 = ((ExcelColumnProp)paramExcelSheetProp.getColumnPropList().get(s2)).isNeedMerge();
	              if (bool1)
	                if ((arrayOfInt[s2] != -1) && (!"".equals(arrayOfString[s2])))
	                {
	                  ExcelBaseOper.merged(paramSheet, ExcelBaseOper.getRow(paramSheet, arrayOfInt[s2] + j), ((ExcelColumnProp)paramExcelSheetProp.getColumnPropList().get(s2)).getColumnType(), arrayOfInt[s2] + j, k + n, n + j, k + n, localCellStyle2, arrayOfString[s2], paramCreationHelper);
	                }
	                else
	                {
	                  String s4 = ((ExcelColumnProp)paramExcelSheetProp.getColumnPropList().get(s2)).getColumnCode();
	                  str5 = ((ExcelColumnProp)paramExcelSheetProp.getColumnPropList().get(s2)).getColumnType();
	                  String str6 = (String)localMap.get(s4);
	                  if (str6 == null)
	                    str6 = (String)localMap.get(s4.toUpperCase());
	                  if (str6 == null)
	                    str6 = (String)localMap.get(s4.toLowerCase());
	                  ExcelBaseOper.createCell(ExcelBaseOper.getRow(paramSheet, j + n), s2, str6, str5, localCellStyle1, paramCreationHelper);
	                }
	            }
	        }
	      }
	    }
	    catch (Exception localException)
	    {
	      throw localException;
	    }
	  }

	  private static void _$1(Sheet paramSheet, int paramInt)
	  {
	    for (int i = 0; i < paramInt; i++)
	      paramSheet.autoSizeColumn(i);
	  }

	  private static boolean _$1(List<Map<String, String>> paramList, int paramInt, String paramString)
	  {
	    if (paramInt + 1 < paramList.size())
	    {
	      String str1 = (String)((Map)paramList.get(paramInt)).get(paramString);
	      String str2 = (String)((Map)paramList.get(paramInt + 1)).get(paramString);
	      return str2.trim().equals(str1.trim());
	    }
	    return false;
	  }

	  @Deprecated
	  private static void _$1(HSSFWorkbook paramHSSFWorkbook, HSSFSheet paramHSSFSheet, ExcelSheetProp paramExcelSheetProp)
	  {
	    if (paramExcelSheetProp.getColumnList().size() != 0)
	    {
	      int i = 0;
	      Iterator localIterator = paramExcelSheetProp.getColumnList().iterator();
	      while (localIterator.hasNext())
	      {
	        HashMap localHashMap = (HashMap)localIterator.next();
	        HSSFRow localHSSFRow = paramHSSFSheet.createRow(i + 1);
	        for (int j = 0; j < paramExcelSheetProp.getColumnName().length; j++)
	        {
	          String str1 = paramExcelSheetProp.getColumnName()[j];
	          Object localObject = localHashMap.get(str1);
	          if (localObject == null)
	            localObject = localHashMap.get(str1.toUpperCase());
	          if (localObject == null)
	            localObject = localHashMap.get(str1.toLowerCase());
	          String str2 = "";
	          if (localObject != null)
	            str2 = localObject.toString();
	          String str3 = paramExcelSheetProp.getColumnType()[j];
	          if ((str1 != null) && (!"".equals(str1)))
	            if (str3.equals(Constants.META_TYPE_STRING))
	              _$2(paramHSSFWorkbook, localHSSFRow, (short)j, (short)2, str2);
	            else if (str3.equals(Constants.META_TYPE_NUMERIC))
	            {
	              if (!"".equals(str2))
	                _$1(paramHSSFWorkbook, localHSSFRow, (short)j, (short)2, Double.parseDouble(str2));
	            }
	            else if (str3.equals(Constants.META_TYPE_DATE))
	            {
	              if (!"".equals(str2))
	                _$1(paramHSSFWorkbook, localHSSFRow, (short)j, (short)2, str2);
	            }
	            else
	              _$2(paramHSSFWorkbook, localHSSFRow, (short)j, (short)2, str2);
	        }
	        i++;
	      }
	    }
	  }

	  private static void _$2(HSSFWorkbook paramHSSFWorkbook, HSSFRow paramHSSFRow, int paramInt, short paramShort, String paramString)
	  {
	    HSSFCell localHSSFCell = paramHSSFRow.createCell(paramInt);
	    HSSFCellStyle localHSSFCellStyle = paramHSSFWorkbook.createCellStyle();
	    localHSSFCellStyle.setAlignment(paramShort);
	    localHSSFCell.setCellStyle(localHSSFCellStyle);
	    String str = "";
	    if (paramString != null)
	      str = paramString.toString();
	    localHSSFCell.setCellValue(str);
	  }

	  private static void _$1(HSSFWorkbook paramHSSFWorkbook, HSSFRow paramHSSFRow, int paramInt, short paramShort, double paramDouble)
	  {
	    HSSFCell localHSSFCell = paramHSSFRow.createCell(paramInt);
	    HSSFCellStyle localHSSFCellStyle = paramHSSFWorkbook.createCellStyle();
	    localHSSFCellStyle.setAlignment(paramShort);
	    localHSSFCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.0"));
	    localHSSFCellStyle.setAlignment(paramShort);
	    localHSSFCell.setCellStyle(localHSSFCellStyle);
	    localHSSFCell.setCellValue(paramDouble);
	  }

	  private static void _$1(HSSFWorkbook paramHSSFWorkbook, HSSFRow paramHSSFRow, int paramInt, short paramShort, String paramString)
	  {
	    HSSFCell localHSSFCell = paramHSSFRow.createCell(paramInt);
	    HSSFCellStyle localHSSFCellStyle = paramHSSFWorkbook.createCellStyle();
	    localHSSFCellStyle.setAlignment(paramShort);
	    localHSSFCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("yyyy-MM-dd hh:mm:ss"));
	    localHSSFCellStyle.setAlignment(paramShort);
	    localHSSFCell.setCellStyle(localHSSFCellStyle);
	    localHSSFCell.setCellValue(paramString);
	  }

	  private static int[] _$1(String paramString1, String paramString2)
	  {
	    int i = paramString1.length();
	    int j = paramString2.length();
	    int k = 0;
	    int m = 0;
	    int n = 0;
	    int i1 = 0;
	    for (int i2 = 0; i2 < i; i2++)
	      if (_$2(paramString1.charAt(i2)))
	      {
	        m = k * 26 + _$1(paramString1.toUpperCase().charAt(i2));
	      }
	      else
	      {
	        k = Integer.parseInt(paramString1.substring(i2, paramString1.length()));
	        break;
	      }
	    for (int i2 = 0; i2 < j; i2++)
	      if (_$2(paramString2.charAt(i2)))
	      {
	        i1 = i1 * 26 + _$1(paramString2.toUpperCase().charAt(i2));
	      }
	      else
	      {
	        n = Integer.parseInt(paramString2.substring(i2, paramString2.length()));
	        break;
	      }
	    return new int[] { m, i1, k, n };
	  }

	  private static boolean _$2(char paramChar)
	  {
	    return Pattern.matches("[A-Z]", String.valueOf(paramChar));
	  }

	  public static boolean isValidExcelInput(String paramString)
	  {
	    return Pattern.matches("[A-Z]+[0-9]+", paramString);
	  }

	  private static int _$1(char paramChar)
	  {
	    char c = 'A';
	    return paramChar - c + 1;
	  }
}
