package com.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
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

import com.dao.GuidaTvDAO;
import com.dto.ActorDTO;
import com.dto.ChannelDTO;
import com.dto.DirectorDTO;
import com.dto.MovieDTO;
import com.dto.PiattaFormaDTO;
import com.dto.ProgramMovieDTO;
import com.dto.ProgramNetflixDTO;
import com.dto.ProgramTvMovieDTO;

public class XmltvParserUtil {


	//comando base per genererare il file xmltv

	//                cd C:\Users\ficeti\Dropbox\xmltv-0.5.67-win32
	// 

	public static List<ProgramTvMovieDTO> getProgrammiTV() {
		// TODO Auto-generated method stub
		List <ProgramTvMovieDTO> programmi = new ArrayList <ProgramTvMovieDTO>();
		try {
			String varTipoProgramma="";
			String fascia="";
			
			String linkSky="http://www.mymovies.it/v9/tv/ajax/programmatv.asp"
					+ "?var_tipoprogramma="+varTipoProgramma
					+ "&fascia="+fascia
					+ "&var_id_canale=&var_tipo_canale=s&variabile3=&div=idprogramma";
			linkSky="http://www.mymovies.it/tv/sky/";
			String linkDigitale="http://www.mymovies.it/v9/tv/ajax/programmatv.asp"
					+ "?var_tipoprogramma="+varTipoProgramma
					+ "&fascia="+fascia
					+ "&var_id_canale=&var_tipo_canale=d&variabile3=&div=idprogramma";
			linkDigitale="http://www.mymovies.it/tv/";
			
			URL urlSky = new URL(linkSky);
			URL urlDigitale = new URL(linkDigitale);

			extractProgrammi(programmi, urlSky, PiattaFormaDTO.SKY.codPiattaforma);
			extractProgrammi(programmi, urlDigitale, PiattaFormaDTO.DIGITTERR.codPiattaforma);
			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return programmi;
	}

	public static List<ProgramNetflixDTO> getProgrammiNetflix() {
		// TODO Auto-generated method stub
		List <ProgramNetflixDTO> programmi = new ArrayList <ProgramNetflixDTO>();
		try {
			String fascia="";
			
			String linkNetflix="https://whatsnewonnetflix.com/italy/";

			
			URL urlNetflix = new URL(linkNetflix);
			extractProgrammiNetflix(programmi, urlNetflix);
			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return programmi;
	}
	
	
	private static void extractProgrammiNetflix(List<ProgramNetflixDTO> programmi, URL urlNetflix) throws UnsupportedEncodingException, IOException {
	//	System.setProperty("https.protocols", "TLSv1");
	//	HttpURLConnection uc = ProxyUtil.connect(urlNetflix);
		String secondUrl="http://it.allflicks.net/";
		HttpURLConnection uc2 = ProxyUtil.connect(new URL(secondUrl));

		String line = null;
		StringBuffer tmp = new StringBuffer();
		BufferedReader in;
		try{
			in = new BufferedReader(new InputStreamReader(uc2.getInputStream(),"UTF-8"));

		}
		catch(FileNotFoundException ex)
		{
			throw ex;
		}
		while ((line = in.readLine()) != null) {
			tmp.append(line);
		}
		org.jsoup.nodes.Document doc = Jsoup.parse(String.valueOf(tmp));
		Elements elements = doc.select("div[class*=entry");
		Iterator<Element> it = elements.iterator();
		while(it.hasNext())
		{
			Element movieTab = it.next();
			
		}
	}

	public static List<ProgramTvMovieDTO> getProgrammiTVChannelByChannel(List <ChannelDTO> channels) {
		ArrayList<ProgramTvMovieDTO> programmi;
		// TODO Auto-generated method stub
		programmi = new ArrayList <ProgramTvMovieDTO>();

		try {
			for(ChannelDTO canale:channels)				
			{
				String codCanale=canale.getChannel().trim().replace(" ","" ).toLowerCase();
				String linkCanale="http://www.mymovies.it/tv/"+codCanale;
				URL urlCanale = new URL(linkCanale);

				extractProgrammi(programmi, urlCanale,codCanale);

			}

			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return programmi;
	}

	
	
	
	private static void setPlatform(List<ProgramTvMovieDTO> programmi, String codPiattaforma) {
		// TODO Auto-generated method stub
		for(ProgramTvMovieDTO program: programmi)
		{
			program.setPlatform(codPiattaforma);
		}
	}

	private static void extractProgrammi(List<ProgramTvMovieDTO> programmi, URL url, String platform)
			throws IOException, UnsupportedEncodingException, FileNotFoundException {
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
			extractPlatform(movieTabChildren.get(0),program);
			
			try{
				extractMovieInfo(movieTabChildren.get(2),program.getMovie());
				extractTipo(movieTabChildren.get(2),program);
				program.setTitolo(program.getMovie().getTitoloItaliano());
			}
			catch(Exception ex)
			{
				extractOtherInfo(movieTabChildren.get(2),program);
			}
			//printProgram(program);
			program.setPlatform(platform);
			programmi.add(program);
			
		}
	}

	private static void extractTipo(Element element, ProgramTvMovieDTO program) {
		// TODO Auto-generated method stub
		program.setTipo(element.select("h3").get(0).ownText().split("\\(")[0].trim());

	}

	private static void printProgram(ProgramTvMovieDTO program) {
		// TODO Auto-generated method stub
		
		MovieDTO movie =program.getMovie();
		System.out.println("");
		if(movie.getTitoloItaliano()!=null)
		{
			System.out.println(movie.getTitoloItaliano());
			if(movie.getDirectors()!= null && movie.getDirectors().size()!=0)
			{
				System.out.println(movie.getDirectors().get(0).getName());
				System.out.println(movie.getYear());
			}

		}
		System.out.println("");
	}

	private static void extractOtherInfo(Element element, ProgramTvMovieDTO program) {
		// TODO Auto-generated method stub
		//program.setTitolo(element.select("h2 style='color:#191919'").get(0).ownText());
		//program.setTipo(element.select("h3 style='color:#444444'").get(0).ownText());
		
		program.setTitolo(element.select("h2").get(0).ownText());
		program.setTipo(element.select("h3").get(0).ownText());
	}

	private static void extractTime(ProgramTvMovieDTO program, Element movieTab) {
		Element timeElement = movieTab.previousElementSibling();
		String timeProgram=timeElement.select("strong").get(0).ownText().trim();
		String hoursMinutes = timeProgram.split(" ")[1];
		program.setOraInizio(DateUtil.setDateToday(Integer.parseInt(hoursMinutes.split(",")[0]),Integer.parseInt(hoursMinutes.split(",")[1])));
	}

	private static void extractPlatform(Element element, ProgramMovieDTO program) {
		program.setChannel(element.getElementsByTag("a").get(0).ownText());

	}

	private static void extractMovieInfo(Element element, MovieDTO movie) {
		
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
