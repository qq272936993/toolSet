package cn.com.yws.toolset.jee.easyui;

import java.util.List;
import java.util.Map;

public class EasyTreeNode {
	
	private Object id;	//一般是Integer和Long类型
	private String text;	//显示的文本
	private String iconCls;	//显示的css样式
	private Map<String,Object> attributes;	//额外参数
	private String state;	//状态	"open"\"close"
	private List<EasyTreeNode> children;	//子节点
	
	
	public Object getId() {
		return id;
	}
	public void setId(Object id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<EasyTreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<EasyTreeNode> children) {
		this.children = children;
	}
	
	
	
	 
	
	
}
