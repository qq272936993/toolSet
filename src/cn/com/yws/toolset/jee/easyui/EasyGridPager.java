package cn.com.yws.toolset.jee.easyui;

import java.util.List;

/**
 * 文件名称: EasyGridPager.java
 * 包路径: cn.com.yws.easyui
 * 描述:
 * 内容摘要 
 *    作者: 杨文松
 *    版本: 1.0
 *    时间: 2013-12-26
 *    邮箱: yangwensong@talkweb.com.cn
 * 修改历史:  
 * 修改日期           修改人员        版本	       修改内容  		说明
 * ---------------------------------------------- 
 * 
 */
public class EasyGridPager {
	
	/**
	 * @category in args(传入参数)
	 * 当前页,相当于easyUI中的传入参数page
	 * */
	private String currentPage;
	
	/**
	 * @category in args(传入参数)
	 * 一页多少条记录数,相当于easyUI中传入的rows
	 * */
	private String pageSize;
	
	/**
	 * @category out args(传出参数)
	 * 数据库中一共有多少条记录
	 * */
	private String total;
	
	/**
	 * @category out args(传出参数)
	 * 查询的结果集
	 * */
	private List<?> rows;

	
	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
	
}
