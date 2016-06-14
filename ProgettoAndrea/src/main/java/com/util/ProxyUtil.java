package com.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

public class ProxyUtil {

	
	public static String proxy="seea";
	
	public static HttpURLConnection connect(URL url) throws IOException {
		HttpURLConnection connection;
		if(proxy.equals(""))
		{
			   connection = (HttpURLConnection)url.openConnection();
			   connection.connect();

		}
		else
		{
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy.sdc.hp.com", 8080)); // or whatever your proxy is
			 connection = (HttpURLConnection)url.openConnection(proxy);
			  connection.connect();
		}
		
		return connection;
	}
}
