package cn.com.yws.toolset.relyother.office;

/**
 * <pre>
 * 文件名称: MergeCellUtil.java
 * 包路径: cn.com.frm.base.office
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
import java.util.Iterator;
import java.util.List;

public class MergeCellUtil
{
  public static int[][] caculateHeaderRowStartcol(TableHeaderProp paramTableHeaderProp)
  {
    int i = paramTableHeaderProp.getHeaderColumnList().size();
    int[][] arrayOfInt = new int[i][paramTableHeaderProp.getTotalCol()];
    int j = _$1(paramTableHeaderProp);
    paramTableHeaderProp.setContainRow(j);
    for (int k = 0; k < arrayOfInt.length; k++)
      arrayOfInt[k] = new int[paramTableHeaderProp.getTotalCol()];
    for (int k = 0; k < i; k++)
    {
      int m = ((List)paramTableHeaderProp.getHeaderColumnList().get(k)).size();
      for (int n = 0; n < m; n++)
        _$1(k, n, paramTableHeaderProp, arrayOfInt);
    }
    return arrayOfInt;
  }

  private static void _$1(int paramInt1, int paramInt2, TableHeaderProp paramTableHeaderProp, int[][] paramArrayOfInt)
  {
    try
    {
      if (paramInt1 == 0)
      {
        if (paramInt2 == 0)
          paramArrayOfInt[0][0] = 0;
        else
          paramArrayOfInt[0][paramInt2] = (paramArrayOfInt[0][(paramInt2 - 1)] + ((TableHeaderColumn)((List)paramTableHeaderProp.getHeaderColumnList().get(0)).get(paramInt2 - 1)).getColSpan());
      }
      else
      {
        int i = paramTableHeaderProp.getHeaderColumnList().size();
        int m;
        int n;
        int i1;
        int i3;
        if (paramInt2 == 0)
        {
          int j = 0;
          int k = 0;
          m = paramInt2 + 1;
          for (n = paramInt1 - 1; n < paramInt1; n++)
          {
        	List<TableHeaderColumn> localObject = paramTableHeaderProp.getHeaderColumnList().get(n);
            for (i1 = 0; i1 < localObject.size(); i1++)
            {
              TableHeaderColumn localTableHeaderColumn1 = localObject.get(i1);
              i3 = localTableHeaderColumn1.getRowSpan();
              int i4 = localTableHeaderColumn1.getColSpan();
              if (i3 + n != i)
                k++;
              if (k == m)
              {
                paramArrayOfInt[paramInt1][paramInt2] = paramArrayOfInt[n][i1];
                return;
              }
            }
          }
        }
        else
        {
          List localList1 = (List)paramTableHeaderProp.getHeaderColumnList().get(paramInt1 - 1);
          List localList2 = (List)paramTableHeaderProp.getHeaderColumnList().get(paramInt1);
          m = 0;
          n = 0;
          int[] spans = new int[paramInt2];
          for (i1 = 0; i1 < paramInt2; i1++)
          {
            n += ((TableHeaderColumn)localList2.get(i1)).getColSpan();
            spans[i1] = n;
          }
          i1 = 0;
          for (int i2 = 0; i2 < localList1.size(); i2++)
          {
            i3 = 0;
            TableHeaderColumn localTableHeaderColumn2 = (TableHeaderColumn)localList1.get(i2);
            int i5 = localTableHeaderColumn2.getRowSpan();
            int i6 = localTableHeaderColumn2.getColSpan();
            if (i5 + paramInt1 - 1 != i)
            {
              m += i6;
              i1++;
            }
            for (int i7 = 0; i7 < spans.length; i7++)
              if (spans[i7] >= m)
                i3 = 0;
              else
                i3++;
            if (m > n)
            {
              if (_$1(spans, localList1, i2, paramInt1, i))
              {
                paramArrayOfInt[paramInt1][paramInt2] = paramArrayOfInt[(paramInt1 - 1)][i2];
                break;
              }
              paramArrayOfInt[paramInt1][paramInt2] = (paramArrayOfInt[paramInt1][(paramInt2 - 1)] + ((TableHeaderColumn)((List)paramTableHeaderProp.getHeaderColumnList().get(paramInt1)).get(paramInt2 - 1)).getColSpan());
              break;
            }
            if ((m == n) && (i2 < localList1.size() - 1))
            {
              for (int i7 = i2 + 1; ((TableHeaderColumn)((List)paramTableHeaderProp.getHeaderColumnList().get(paramInt1 - 1)).get(i7)).getRowSpan() + paramInt1 - 1 == i; i7++)
            	  paramArrayOfInt[paramInt1][paramInt2] = paramArrayOfInt[(paramInt1 - 1)][i7];
              break;
            }
            paramArrayOfInt[paramInt1][paramInt2] = paramArrayOfInt[(paramInt1 - 1)][i2];
          }
          if (paramArrayOfInt[paramInt1][paramInt2] == 0)
            paramArrayOfInt[paramInt1][paramInt2] = (paramArrayOfInt[paramInt1][(paramInt2 - 1)] + ((TableHeaderColumn)((List)paramTableHeaderProp.getHeaderColumnList().get(paramInt1)).get(paramInt2 - 1)).getColSpan());
        }
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  private static boolean _$1(int[] paramArrayOfInt, List<TableHeaderColumn> paramList, int paramInt1, int paramInt2, int paramInt3)
  {
    int i = 0;
    boolean bool = false;
    int j = paramArrayOfInt[(paramArrayOfInt.length - 1)];
    for (int k = 0; k < paramInt1; k++)
    {
      TableHeaderColumn localTableHeaderColumn = (TableHeaderColumn)paramList.get(k);
      int m = localTableHeaderColumn.getRowSpan();
      int n = localTableHeaderColumn.getColSpan();
      if (m + paramInt2 - 1 != paramInt3)
        i += n;
    }
    if (i == j)
      bool = true;
    return bool;
  }

  private static int _$1(TableHeaderProp paramTableHeaderProp)
  {
    List localList = (List)paramTableHeaderProp.getHeaderColumnList().get(0);
    int i = 0;
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      TableHeaderColumn localTableHeaderColumn = (TableHeaderColumn)localIterator.next();
      i += localTableHeaderColumn.getColSpan();
    }
    return i;
  }
}