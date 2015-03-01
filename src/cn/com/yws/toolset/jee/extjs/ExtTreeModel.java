package cn.com.yws.toolset.jee.extjs;

import java.io.Serializable;

/****
 *<pre>
 * project 				: toolfrm
 * package class 	: cn.com.frm.base.model.extjs ExtTreeModel.java
 * author				: 杨文松
 * dateTime 			: 2014-5-18 上午08:47:50
 * TODO 				: 用于生成EXT JS 树形节点的对象
 * </pre>
 */
public class ExtTreeModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	/**
	 * 树形文本
	 * */
	private String text;		
	
	/**
	 * 节点的id值
	 * */
	private String id;
	
	/**
	 * 树形节点的样式
	 * */
	private String cls;
	
	/**
	 * 是否为叶子节点
	 * true /false
	 * */
	private Boolean leaf;

	
	
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}

	public Boolean getLeaf() {
		return leaf;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	/**
	 * 
	 */
	public ExtTreeModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param text
	 * @param id
	 * @param cls
	 * @param leaf
	 */
	public ExtTreeModel(String text, String id, String cls, Boolean leaf) {
		super();
		this.text = text;
		this.id = id;
		this.cls = cls;
		this.leaf = leaf;
	}
	
}


	