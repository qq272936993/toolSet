package cn.com.yws.toolset.base.common.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.helpers.DefaultHandler;

public class SaxParser {
	private InputStream is;
	
	public SaxParser(InputStream is){
		this.is = is;
	}
	
	public SaxParser(File file){
		try {
			this.is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void parser(DefaultHandler defaultHandler){
		 SAXParserFactory factory = SAXParserFactory.newInstance();  
		 SAXParser parser;
		try {
			parser = factory.newSAXParser();
			parser.parse(is, defaultHandler);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error while sax parser ");
		} 
	}

}
