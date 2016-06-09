package com.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
			String link="http://www.mymovies.it/v9/tv/ajax/programmatv.asp"
					+ "?var_tipoprogramma=%20((FILM.ID_TIPO_FILM)=%22FILM%22)%20OR%20"
					+ "((FILM.ID_TIPO_FILM)=%22FLTV%22)%20%20&fascia=((PROGRAMMAZIONE_TV.ORA_FILM)%3E=2)"
					+ "%20AND%20((PROGRAMMAZIONE_TV.ORA_FILM)%3C24)%20OR%20((PROGRAMMAZIONE_TV.ORA_FILM)%3E=24)"
					+ "%20&var_id_canale=&var_tipo_canale=s&variabile3=&div=idprogramma";
			
			URL url = new URL(link);
			HttpURLConnection uc = ProxyUtil.connect(url);
			String line = null;
			StringBuffer tmp = new StringBuffer();
			BufferedReader in;
			try{
				in = new BufferedReader(new InputStreamReader(uc.getInputStream(),"UTF-8"));

			}
			catch(FileNotFoundException ex)
			{
				throw ex;
			}
			while ((line = in.readLine()) != null) {
				tmp.append(line);
			}


			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println("prova");
		}
		return null;
	}


}
