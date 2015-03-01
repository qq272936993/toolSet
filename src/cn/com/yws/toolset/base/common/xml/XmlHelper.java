package cn.com.yws.toolset.base.common.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public final class XmlHelper {
	private XmlHelper(){}
	
	public static String getAttrValue(String xml, String nodeXpath, String attrName){
		DomParser parser = new DomParser(xml);
		return DocumentHelper.getAttrValue(parser.getDoc(), nodeXpath, attrName);
	}
	
	public static String getTextValue(String xml, String nodeXpath){
		DomParser parser = new DomParser(xml);
		Node node = DocumentHelper.findNodeByXpath(parser.getDoc(), nodeXpath);
		if(node == null) return null;
		return DocumentHelper.getTextValue(parser.getDoc(), nodeXpath);
	}
	
	public static String getCData(String xml, String nodeXpath){
		DomParser parser = new DomParser(xml);
		return DocumentHelper.getCDataText(parser.getDoc(), nodeXpath);
	}
	
	public static Node getElement(String xml, String nodeXpath){
		DomParser parser = new DomParser(xml);
		return DocumentHelper.getElementNode(parser.getDoc(), nodeXpath);
	}
	
	public static String addElement(String xml, String baseXpath, String nodeName, String nodeText){
		DomParser parser = new DomParser(xml);
		DocumentHelper.addElementNode(parser.getDoc(), baseXpath, nodeName, nodeText);
		DocumentOutput output = new DocumentOutput(parser.getDoc());
		return output.toXmlString();
	}
	
	public static NodeType getNodeType(String xml, String nodeXpath){
		DomParser parser = new DomParser(xml);
		Document doc = parser.getDoc();
		Node node = DocumentHelper.getElementNode(doc, nodeXpath);
		return DocumentHelper.getNodeType(node);
	}
	
	public static String setAttribute(String xml, String nodeXpath, String attrName, String attrValue){
		DomParser parser = new DomParser(xml);
		DocumentHelper.setAttribute(parser.getDoc(), nodeXpath, attrName, attrValue);
		DocumentOutput output = new DocumentOutput(parser.getDoc());
		return output.toXmlString();
	}
	
	public static String setText(String xml, String nodeXpath, String text){
		DomParser parser = new DomParser(xml);
		DocumentHelper.setTextValue(parser.getDoc(), nodeXpath, text);
		DocumentOutput output = new DocumentOutput(parser.getDoc());
		return output.toXmlString();
	}
	
	public static String setCData(String xml, String nodeXpath, String cdata){
		DomParser parser = new DomParser(xml);
		DocumentHelper.setCData(parser.getDoc(), nodeXpath, cdata);
		DocumentOutput output = new DocumentOutput(parser.getDoc());
		return output.toXmlString();
	}
	
	public static String replaceElement(String xml, String nodeXpath, String elementName){
		DomParser parser = new DomParser(xml);
		Element ele = parser.getDoc().createElement(elementName);
		DocumentHelper.replaceElement(parser.getDoc(), nodeXpath, ele);
		DocumentOutput output = new DocumentOutput(parser.getDoc());
		return output.toXmlString();
	}
	
	public static String removeText(String xml, String nodeXpath){
		DomParser parser = new DomParser(xml);
		DocumentHelper.removeText(parser.getDoc(), nodeXpath);
		DocumentOutput output = new DocumentOutput(parser.getDoc());
		return output.toXmlString();
	}
	
	public static String removeAttribute(String xml, String nodeXpath, String attrName){
		DomParser parser = new DomParser(xml);
		DocumentHelper.removeAttribute(parser.getDoc(), nodeXpath, attrName);
		DocumentOutput output = new DocumentOutput(parser.getDoc());
		return output.toXmlString();
	}
	
	public static String removeCData(String xml, String nodeXpath){
		DomParser parser = new DomParser(xml);
		DocumentHelper.removeCData(parser.getDoc(), nodeXpath);
		DocumentOutput output = new DocumentOutput(parser.getDoc());
		return output.toXmlString();
	}
	
	public static String removeElement(String xml, String nodeXpath){
		DomParser parser = new DomParser(xml);
		DocumentHelper.removeElement(parser.getDoc(), nodeXpath);
		DocumentOutput output = new DocumentOutput(parser.getDoc());
		return output.toXmlString();
	}
	
	public static String addNode(String xml ,String nodeXpath,String nodeName,String nodeVal){
		DomParser parser = new DomParser(xml);
		DocumentHelper.addElementNode(parser.getDoc(), nodeXpath, nodeName, nodeVal);
		DocumentOutput output = new DocumentOutput(parser.getDoc());
		return output.toXmlString();
	}
	
}
