package cn.com.yws.toolset.base.common.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DocumentHelper {
	
	public static NodeType getNodeType(Node node){
		switch (node.getNodeType()) {
		case Node.ATTRIBUTE_NODE:
			return NodeType.ATTRIBUTE_NODE;
			
		case Node.ELEMENT_NODE:
			return NodeType.ELEMENT_NODE;
			
		case Node.TEXT_NODE:
			return NodeType.TEXT_NODE;
			
		case Node.CDATA_SECTION_NODE:
			return NodeType.CDATA_SECTION_NODE;
			
		case Node.ENTITY_REFERENCE_NODE:
			return NodeType.ENTITY_REFERENCE_NODE;
			
		case Node.ENTITY_NODE:
			return NodeType.ENTITY_NODE;
			
		case Node.DOCUMENT_NODE:
			return NodeType.DOCUMENT_NODE;
			
		case Node.DOCUMENT_TYPE_NODE:
			return NodeType.DOCUMENT_TYPE_NODE;
			
		case Node.DOCUMENT_FRAGMENT_NODE:
			return NodeType.DOCUMENT_FRAGMENT_NODE;
			
		case Node.NOTATION_NODE:
			return NodeType.NOTATION_NODE;
			
		case Node.PROCESSING_INSTRUCTION_NODE:
			return NodeType.PROCESSING_INSTRUCTION_NODE;
			
		case Node.COMMENT_NODE:
			return NodeType.COMMENT_NODE;
			
		default:
			break;
		} 
		
		throw new RuntimeException("No NodeType detected");
	}
	
	public static boolean isNodeTypeMatches(Node node, NodeType nodeType){
		return ((int)node.getNodeType())==nodeType.getValue();
	}
	
	/**
	 * 通过Xpath查找节点，并删除其属性Attribute
	 * 如果Node定位到了多个， 请用[]定位到某一个, 只会删除第一个Node的Attribute
	 * @param doc
	 * @param xpath
	 */
	public static void removeAttribute(Document doc, String xpath, String attrName){
        Node node = findNodeByXpath(doc, xpath);
        if (node != null && isNodeTypeMatches(node, NodeType.ELEMENT_NODE)){
        	Element element = (Element)node;
        	element.removeAttribute(attrName);
        }
	}
	
	/**
	 * 通过Xpath查找节点， 并删除其文本Text
	 * 如果Node定位到了多个， 请用[]定位到某一个, 只会删除第一个Node的Text
	 * @param doc
	 * @param xpath
	 */
	public static void removeText(Document doc, String xpath){
        Node node = findNodeByXpath(doc, xpath);
        if (node != null){
        	node.setTextContent(null);
        }
	}
	
	/**
	 * 通过Xpath查找并删除Element节点
	 * 如果Node定位到了多个， 请用[]定位到某一个, 只会删除第一个Node
	 * @param doc
	 * @param xpath
	 */
	public static void removeElement(Document doc, String xpath){
        Node node = findNodeByXpath(doc, xpath);
        if (node != null && isNodeTypeMatches(node, NodeType.ELEMENT_NODE)){
        	Element element = (Element)node;
        	element.getParentNode().removeChild(element);
        }
	}
	
	/**
	 * 通过Xpath定位Cdata所在的Node节点， 为其删除Cdata
	 * 如果Node定位到了多个， 请用[]定位到某一个, 只会删除第一个的Cdata
	 * @param doc
	 * @param nodeXpath
	 */
	public static void removeCData(Document doc, String nodeXpath){
        Node node = findNodeByXpath(doc, nodeXpath);
        
        if (node != null){
	        NodeList clist = node.getChildNodes();
	        for (int j=0; j<clist.getLength(); j++){
	        	Node cNode = clist.item(j);
	        	if (isNodeTypeMatches(cNode, NodeType.CDATA_SECTION_NODE)){
	        		node.removeChild(cNode);
	        	}
	        }
        }
	}
	
	/**
	 * 通过Xpath替换Element节点
	 * 如果Node定位到了多个， 请用[]定位到某一个, 只会替换第一个
	 * @param doc
	 * @param xpath
	 * @param ele
	 */
	public static void replaceElement(Document doc, String xpath, Element ele){
        Node node = findNodeByXpath(doc, xpath);
        if (node != null){
        	Node pNode = node.getParentNode();
        	pNode.removeChild(node);
        	pNode.appendChild(ele);
        }
	}
	
	/**
	 * 通过Xpath为某个节点设置属性 Attribute
	 * 如果Node定位到了多个， 请用[]定位到某一个， 只会设置第一个Node的Attribute
	 * @param doc
	 * @param nodeXpath
	 * @param attrName
	 * @param attrValue
	 */
	public static void setAttribute(Document doc, String nodeXpath, String attrName, String attrValue){
		Node node = findNodeByXpath(doc, nodeXpath);
        if (node != null && isNodeTypeMatches(node, NodeType.ELEMENT_NODE)){
        	Element element = (Element)node;
        	element.setAttribute(attrName, attrValue);
        }
	}
	
	/**
	 * 通过Xpath为某个节点设置Text值
	 * 如果Node定位到了多个， 请用[]定位到某一个， 只会设置第一个Node的Text
	 * @param doc
	 * @param nodeXpath
	 * @param value
	 */
	public static void setTextValue(Document doc, String nodeXpath, String value){
		Node node = findNodeByXpath(doc, nodeXpath);
		if (node != null){
			node.setTextContent(value);
		}
	}
	
	/**
	 * 通过Xpath定位节点，并在节点下添加Cdata
	 * 如果Node定位到了多个， 请用[]定位到某一个， 只会添加第一个Node的CData
	 * @param doc
	 * @param nodeXpath
	 * @param data
	 */
	public static void setCData(Document doc, String nodeXpath, String data){
		   CDATASection cdata = doc.createCDATASection(data);
		   Node node = findNodeByXpath(doc, nodeXpath);
		   if (node != null){
			   if (node.getTextContent() != null){ //clear text first
				   node.setTextContent(null);
			   }
			   node.appendChild(cdata);
		   }
	}
	
	/**
	 * 通过Xpath定位Node，并为其添加子节点
	 * 如果Node定位到了多个， 请用[]定位到某一个， 只会在第一个Node下添加
	 * @param doc
	 * @param nodeXpath
	 * @param name
	 * @param text
	 */
	public static void addElementNode(Document doc, String nodeXpath, String name, String text){
		Node pNode = findNodeByXpath(doc, nodeXpath);
		if (pNode != null){
			Element eleNew = doc.createElement(name);
			eleNew.setTextContent(text);
			pNode.appendChild(eleNew);
		}
	}
	
	
	/**
	 * 通过Xpath定位到Node， 并返回其CData值
	 * 如果Node定位到了多个， 请用[]定位到某一个， 只会返回第一个Node的CData
	 * @param doc
	 * @param nodeXpath
	 * @return
	 */
	public static String getCDataText(Document doc, String nodeXpath){
		Node node = findNodeByXpath(doc, nodeXpath);
		if (node == null) {
			return null;
		}
		
		Node cdNode = null;
		NodeList nlist = node.getChildNodes();
		for (int i=0; i<nlist.getLength(); i++){
			Node cNode = nlist.item(i);
			if (isNodeTypeMatches(cNode, NodeType.CDATA_SECTION_NODE)){
				cdNode = cNode;
				break;
			}
		}
		
		return cdNode==null ? null: cdNode.getNodeValue();
	}
	
	
	/**
	 * 通过Xpath定位到Element， 并将其返回
	 * 如果定位到多个， 请用[]定位到某一个， 只会返回第一个
	 * @param doc
	 * @param nodeXpath
	 * @return
	 */
	public static Element getElementNode(Document doc, String nodeXpath){
		Node node = findNodeByXpath(doc, nodeXpath);
		if (node != null && isNodeTypeMatches(node, NodeType.ELEMENT_NODE)){
			return (Element)node;
		}
		
		return null;
	}
	
	/**
	 * 通过Xpath定位Node，返回其Text值
	 * 如果Node有多个，请用[]定位到某一个， 只会返回第一个Node的Text值
	 * @param doc
	 * @param nodeXpath
	 * @return
	 */
	public static String getTextValue(Document doc, String nodeXpath){
		Node node = findNodeByXpath(doc, nodeXpath);
		if (node != null){
			return node.getTextContent();
		}
		
		return null;
	}
	
	/**
	 * 通过Xpath定位Node，返回其Attribute值
	 * 如果Node有多个，请用[]定位到某一个， 只会返回第一个Node的Attribute值
	 * @param doc
	 * @param nodeXpath
	 * @return
	 */
	public static String getAttrValue(Document doc, String nodeXpath, String attrName){
		Node node = findNodeByXpath(doc, nodeXpath);
		if (node != null){
			Element ele = (Element)node;
			return ele.getAttribute(attrName);
		}
		
		return null;
	}
	
	
	
	public static NodeList findNodeListByXpath(Document doc, String xpath){
		XPathFactory pathFactory = XPathFactory.newInstance();     
        //使用XPathFactory工厂创建 XPath 对象  
        XPath x_path = pathFactory.newXPath();     
        //使用XPath对象编译XPath表达式  
        Object obj = null;
        try{
	        XPathExpression pathExpression = x_path.compile(xpath);  
	        obj = pathExpression.evaluate(doc, XPathConstants.NODESET);  
        }catch(Exception ex){
        	ex.printStackTrace();
        	throw new RuntimeException("Error while xpath search");
        }
        NodeList nodelist = (NodeList)obj;
        
        return nodelist;
	}
	
	public static List<Node> findNodeListByXpath(Document doc, String xpath, NodeType nodeType){
		XPathFactory pathFactory = XPathFactory.newInstance();     
        //使用XPathFactory工厂创建 XPath 对象  
        XPath x_path = pathFactory.newXPath();     
        //使用XPath对象编译XPath表达式  
        Object obj = null;
        try{
	        XPathExpression pathExpression = x_path.compile(xpath);  
	        obj = pathExpression.evaluate(doc, XPathConstants.NODESET);  
        }catch(Exception ex){
        	ex.printStackTrace();
        	throw new RuntimeException("Error while xpath search");
        }
        NodeList nodelist = (NodeList)obj;
        
        List<Node> nlist = new ArrayList<Node>();
        for (int i=0; i<nodelist.getLength(); i++){
        	Node n = nodelist.item(i);
        	if (isNodeTypeMatches(n, nodeType)){
        		nlist.add(n);
        	}
        }
        
        return nlist;
	}
	
	public static Node findNodeByXpath(Document doc, String xpath){
		NodeList nodelist = findNodeListByXpath(doc, xpath);
		if (nodelist.getLength()>0){
			return nodelist.item(0);
		}
		return null;
	}
	
	public static Document createDocument(){
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return builder.newDocument();
	}
	
	public static String toXml(Document doc){
		DocumentOutput output = new DocumentOutput(doc);
		return output.toXmlString();
	}
	
	
}
