package com.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.controller.ProgramTvMovieDTO;
import com.dto.ActorDTO;
import com.dto.DirectorDTO;
import com.dto.MovieDTO;
import com.dto.ProgramMovieDTO;

public class XmltvParserUtil {


	//comando base per genererare il file xmltv

	//                cd C:\Users\ficeti\Dropbox\xmltv-0.5.67-win32
	// 

	public static List<ProgramTvMovieDTO> getProgrammiTV() {
		// TODO Auto-generated method stub
		List <ProgramTvMovieDTO> programmi = new ArrayList <ProgramTvMovieDTO>();
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
			org.jsoup.nodes.Document doc = Jsoup.parse(String.valueOf(tmp));
			Elements elements = doc.select("div[style*=background-color: #e6eaf5;]");
			Iterator<Element> it = elements.iterator();
			while(it.hasNext())
			{
				ProgramTvMovieDTO program = new ProgramTvMovieDTO();
				Element movieTab = it.next();
				extractTime(program, movieTab);
				Elements movieTabChildren=movieTab.children();
				extractFirstChildren(movieTabChildren.get(0),program);
				extractSecondChildren(movieTabChildren.get(2),program.getMovie());
				programmi.add(program);

			}
			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println("prova");
		}
		return programmi;
	}

	private static void extractTime(ProgramTvMovieDTO program, Element movieTab) {
		Element timeElement = movieTab.previousElementSibling();
		String timeProgram=timeElement.select("strong").get(0).ownText().trim();
		String hoursMinutes = timeProgram.split(" ")[1];
		program.setOraInizio(DateUtil.setDateToday(Integer.parseInt(hoursMinutes.split(",")[0]),Integer.parseInt(hoursMinutes.split(",")[1])));
	}

	private static void extractFirstChildren(Element element, ProgramMovieDTO program) {
		program.setPlatform(element.getElementsByTag("a").get(0).ownText());

	}

	private static void extractSecondChildren(Element element, MovieDTO movie) {
		System.out.println(element.select("table").get(0).select("tr").get(0).select("td").get(0).select("a").get(0).ownText());
		movie.setTitoloItaliano(element.select("table").get(0).select("tr").get(0).select("td").get(0).select("a").get(0).ownText());
		movie.setYear(Integer.parseInt(element.select("h3").get(0).ownText().split("\\(")[1].split("\\)")[0]));
		Elements elements = element.select("div.linkblu").get(0).select("a");

		Iterator<Element> it = elements.iterator();
		List <ActorDTO> actors = new ArrayList <ActorDTO>();
		List <DirectorDTO> directors = new ArrayList <DirectorDTO>();

		while (it.hasNext())
		{
			Element link = it.next();
			if(link.attr("href").contains("?r="))
			{
				directors.add(new DirectorDTO(link.ownText()));
			}
			else if(link.attr("href").contains("?a="))
			{
				actors.add(new ActorDTO(link.ownText()));
			}


		}
		movie.setDirectors(directors);
		movie.setActors(actors);


	}

}
