package cn.com.yws.toolset.base.common.domain;

/**
 * <pre>
 * 文件名称: Page.java
 * 包路径: com.tgwoo.core.dao.pojo
 * 描述:
 * 内容摘要 
 *    作者: 杨文松
 *    版本: 1.0
 *    时间: 2014年9月25日
 *    邮箱: 272936993@qq.com
 * 修改历史:  
 * 修改日期           修改人员        版本	       修改内容  		说明
 * ---------------------------------------------- 
 * 
 * </pre>
 */
public class Page {
	// 分页查询开始记录位置  
    private int begin;  
    // 分页查看下结束位置  
    private int end;  
    // 每页显示记录数  
    private int size;  
    // 查询结果总记录数  
    private int count;  
    // 当前页码  
    private int current;  
    // 总共页数  
    private int total;  
  
    public Page() {  
    }  
  
    /** 
     * 构造函数 
     *  
     * @param begin 
     * @param length 
     */  
    public Page(int begin, int size) {  
        this.begin = begin;  
        this.size = size;  
        this.end = this.begin + this.size;  
        this.current = (int) Math.floor((this.begin * 1.0d) / this.size) + 1;  
    }  
  
    /** 
     * @param begin 
     * @param length 
     * @param count 
     */  
    public Page(int begin, int length, int count) {  
        this(begin, length);  
        this.count = count;  
    }  
  
    /** 
     * @return the begin 
     */  
    public int getBegin() {  
        return begin;  
    }  
  
    /** 
     * @return the end 
     */  
    public int getEnd() {  
        return end;  
    }  
  
    /** 
     * @param end 
     *            the end to set 
     */  
    public void setEnd(int end) {  
        this.end = end;  
    }  
  
    /** 
     * @param begin 
     *            the begin to set 
     */  
    public void setBegin(int begin) {  
        this.begin = begin;  
        if (this.size != 0) {  
            this.current = (int) Math.floor((this.begin * 1.0d) / this.size) + 1;  
        }  
    }  
  
    /** 
     * @return the length 
     */  
    public int getSize() {  
        return size;  
    }  
  
    /** 
     * @param length 
     *            the length to set 
     */  
    public void setSize(int size) {  
        this.size = size;  
        if (this.begin != 0) {  
            this.current = (int) Math.floor((this.begin * 1.0d) / this.size) + 1;  
        }  
    }  
  
    /** 
     * @return the count 
     */  
    public int getCount() {  
        return count;  
    }  
  
    /** 
     * @param count 
     *            the count to set 
     */  
    public void setCount(int count) {  
        this.count = count;  
        this.total = (int) Math.floor((this.count * 1.0d) / this.size);  
        if (this.count % this.size != 0) {  
            this.total++;  
        }  
    }  
  
    /** 
     * @return the current 
     */  
    public int getCurrent() {  
        return current;  
    }  
  
    /** 
     * @param current 
     *            the current to set 
     */  
    public void setCurrent(int current) {  
        this.current = current;  
    }  
  
    /** 
     * @return the total 
     */  
    public int getTotal() {  
        if (total == 0) {  
            return 1;  
        }  
        return total;  
    }  
  
    /** 
     * @param total 
     *            the total to set 
     */  
    public void setTotal(int total) {  
        this.total = total;  
    }  
}
