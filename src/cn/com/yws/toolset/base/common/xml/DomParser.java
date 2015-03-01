package cn.com.yws.toolset.base.common.xml;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomParser {
	private Document doc;
	
	public DomParser(){}
	
	public Document getDoc(){
		return this.doc;
	}
	
	public DomParser(String xml){
		try {
			initDoc(xml);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error while create document for: "+ xml);
		}
	}
	
	public DomParser(InputStream is){
		try {
			initDoc(is);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error while create document for: "+is);
		}
	}
	
	public DomParser(File file){
		InputStream is;
		try {
			is = new FileInputStream(file);
			initDoc(is);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error while create document for: "+file.getPath());
		}
		
	}
	
	public void initDoc(InputStream is) throws Exception{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		doc = builder.parse(is);
	}
	
	public void initDoc(String xml) throws Exception{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		doc = builder.parse(new ByteArrayInputStream(xml.getBytes("utf-8")));
	}
	
	public List<Node> findNodeListByXpath(String xpath){
		List<Node> nlist = new ArrayList<Node>();
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
        
        for (int i=0; i<nodelist.getLength(); i++){
			nlist.add(nodelist.item(i));
		}
        
        return nlist;
	}
	
	public List<Node> findNodeListByTagName(String tagName){
		List<Node> nlist = new ArrayList<Node>();
		NodeList nodelist = doc.getDocumentElement().getElementsByTagName(tagName);
		for (int i=0; i<nodelist.getLength(); i++){
			nlist.add(nodelist.item(i));
		}
		
		return nlist;
	}
	
	public Node findNodeByXpath(String xpath){
		List<Node> nlist = findNodeListByXpath(xpath);
		if (nlist.size()>0){
			return nlist.get(0);
		}
		
		return null;
	}
	
	public Node findNodeByTagName(String tagName){
		List<Node> nlist = findNodeListByTagName(tagName);
		
		if (nlist.size()>0){
			return nlist.get(0);
		}
		
		return null;
	}

}
