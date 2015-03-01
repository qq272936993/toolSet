package cn.com.yws.toolset.base.common.xml;

import java.io.File;
import java.io.OutputStream;
import java.io.StringWriter;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class DocumentOutput {
	private Document doc;
	
	public DocumentOutput(Document doc){
		this.doc = doc;
	}
	
	public String toXmlString(){
		 // Use a Transformer for output
        TransformerFactory tFactory =
          TransformerFactory.newInstance();
        Transformer transformer = null;
		try {
			transformer = tFactory.newTransformer();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
			throw new RuntimeException("Create xml transformer error");
		}

        DOMSource source = new DOMSource(doc);
        StringWriter sw = new StringWriter();
        StreamResult result = new StreamResult(sw);
        try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			e.printStackTrace();
			throw new RuntimeException("Transfor xml error");
		} 
        return sw.getBuffer().toString();
	}
	
	public void toOutputStream(OutputStream os){
		TransformerFactory tFactory =
	          TransformerFactory.newInstance();
	    Transformer transformer;
		try {
			transformer = tFactory.newTransformer();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
			throw new RuntimeException("Create xml transformer error");
		}

	    DOMSource source = new DOMSource(doc);
	    StreamResult result = new StreamResult(os);
	    try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			e.printStackTrace();
			throw new RuntimeException("Transfor xml error");
		} 
	}
	
	public void toFile(File file){
		TransformerFactory tFactory =
	          TransformerFactory.newInstance();
	    Transformer transformer;
		try {
			transformer = tFactory.newTransformer();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
			throw new RuntimeException("Create xml transformer error");
		}

	    DOMSource source = new DOMSource(doc);
	    StreamResult result = new StreamResult(file);
	    try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			e.printStackTrace();
			throw new RuntimeException("Transfor xml error");
		}
	}
}
