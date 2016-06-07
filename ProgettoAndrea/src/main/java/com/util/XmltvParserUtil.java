package com.util;

import java.io.File;
import java.util.List;

import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.controller.ProgrammaTvDTO;

public class XmltvParserUtil {

	
	//comando base per genererare il file xmltv
	
	//                cd C:\Users\ficeti\Dropbox\xmltv-0.5.67-win32
	// 
	
	public static List<ProgrammaTvDTO> getProgrammiTV() {
		// TODO Auto-generated method stub
		try {

			File fXmlFile = new File(ProjectConfigUtil.getXmltvPath());
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			org.w3c.dom.Document doc = dBuilder.parse(fXmlFile);
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println("prova");
		}
		return null;
	}


}
