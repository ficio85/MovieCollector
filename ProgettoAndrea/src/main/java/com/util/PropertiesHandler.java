package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;


public class PropertiesHandler  {

	@Autowired
	static 	Environment env;

	public static	String propertiesFilePath = System.getProperty("jboss.server.config.dir")+"/movieCollector.properties";
 ;



	protected static PropertiesHandler instance = null;
	private Hashtable<String, Properties> resource = null;



	protected PropertiesHandler() {
		super();
	}

	public static String getResource(String key) {
		try {


			String ret = getInstance().getResourceBundle(propertiesFilePath).getProperty(key.trim());                                        
			return ret.trim();
		} catch (Exception missing) {
			missing.printStackTrace();
			System.err.println("risorsa non trovata : resourceName='"+ propertiesFilePath+ "', key='"+ key+ "'");
			missing.printStackTrace();
			return "";
		}
	}

	public static boolean retrieveInternetInfo(){
		
		String value= getResource("internet.info");
		if(value!=null && !value.trim().equals(""))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	private Properties getResourceBundle(String resourceBundleName) throws FileNotFoundException, IOException {


			File f = new File(resourceBundleName);  
			Properties p = new Properties();  
			p.load(new FileInputStream(f));  
			return p;
			                           
		}


	protected static PropertiesHandler getInstance() {
		if (instance == null) {
			instance = new PropertiesHandler();
			instance.setDefault();
		}
		return instance;
	}


	protected PropertiesHandler setDefault() {
		resource = new Hashtable<String, Properties>();
		return this;
	}
}

