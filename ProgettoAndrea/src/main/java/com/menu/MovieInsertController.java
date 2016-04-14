package com.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.DirectorDTO;
import com.dto.MovieDTO;
import com.service.MovieService;
import com.util.KeyGenerator;
import com.util.MovieGeneratorUtil;
import com.util.StringParseUtil;
import com.util.WikiUtil;

@Controller
public class MovieInsertController {


	@Autowired
	@Qualifier("movieService")
	private MovieService movieService;

	private String url="";

	@RequestMapping(value = "/movie/inserimento", method = { RequestMethod.GET, RequestMethod.POST })
	public String processMovie ( HttpServletRequest request,Model model,@RequestParam String movieName, @RequestParam String action) throws Exception {

		System.out.println(movieName);
		movieName=parseMovieName(movieName);
		System.out.println(movieName);
		if(action.equals("insert"))
		{
			executeScriptInsert(movieName,model);
		}
		
		else if(action.equals("cleanDb"))
		{
			executeScriptCleanDb(model);
		}
		else if(action.equals("wikiTry"))
		{
			wikiInsert();
		}
		else if(action.equals("jsoupTry"))
		{
			jsoupInsert();
		}


		return "imdbSearch.page";  
	}
	
	
	
	private  void jsoupInsert() throws IOException {
		// TODO Auto-generated method stub
		URL url = new URL("https://it.wikipedia.org/wiki/Brad_Pitt");
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy.sdc.hp.com", 8080)); // or whatever your proxy is
		HttpURLConnection uc = (HttpURLConnection)url.openConnection(proxy);
		uc.connect();
		String line = null;
		StringBuffer tmp = new StringBuffer();
		BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
		while ((line = in.readLine()) != null) {
			tmp.append(line);
		}
		Document doc = Jsoup.parse(String.valueOf(tmp));
		Element content = doc.getElementById("Cinema");
		List<MovieDTO> moviesActor = new ArrayList <MovieDTO>();
		List<Node> childNodes = content.parentNode().nextSibling().childNodes();		 
		 for(Node node:childNodes)
		 {
			 MovieDTO movie = new MovieDTO();
			  Elements elements = ((Element)node).getElementsByTag("i");
			  int i=0;
			  for(Element el:elements)
			  {
				  
				  if(!el.getElementsByTag("a").isEmpty())
				  {
					  if(i==0)
					  {
						  movie.setTitoloItaliano(el.getElementsByTag("a").get(0).ownText()) ;
					  }
					  else
					  {
						  movie.setTitle(el.getElementsByTag("a").get(0).ownText());
					  }
					 
				  }
				  else
				  {
					  if(i==0)
					  {
						  movie.setTitoloItaliano(el.getElementsByTag("a").get(0).ownText()) ;
					  }
					  else
					  {
						  movie.setTitle(el.getElementsByTag("a").get(0).ownText());
					  }
				  }
				  i++;
			  }
			moviesActor.add(movie);
		 }
		 
			for(MovieDTO film: moviesActor)
			{
				MovieDTO movieDTOtoUpdate =movieService.getInternationalization(film);
				//Se è presente il titolo nel db internazionalizzazione, ma non in italiano inserire solo il titolo italiano (controllare che sia presente anche l'attore dal momento che l'info data dall'API imdb sugli attori è parziale)
				if (movieDTOtoUpdate!=null)
				{
					movieService.updateMovieInternationalization("Brad Pitt", film);
				}
				else
				{
					URL url2 = new URL("http://www.omdbapi.com/?t="+film.getTitle()+"&y=&plot=short&r=json");
					createMovieInstance(url,model);					
				}
				
			}
			
		 
		 
		 
		 
		 
		 movieService.generateDbByActor(moviesActor,"Brad Pitt");

	}

	private void jsoupInsertNonProxy() throws IOException {
		// TODO Auto-generated method stub
		URL url = new URL("https://it.wikipedia.org/wiki/Pagina_principale");
		  Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy.sdc.hp.com", 8080)); // or whatever your proxy is
		  HttpURLConnection uc = (HttpURLConnection)url.openConnection(proxy);

		  uc.connect();

		    String line = null;
		    StringBuffer tmp = new StringBuffer();
		    BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
		    while ((line = in.readLine()) != null) {
		      tmp.append(line);
		    }

		    Document doc = Jsoup.parse(String.valueOf(tmp));
		    doc.text();
	}

	
	
	private void wikiInsert() throws IOException {
		// TODO Auto-generated method stub
		WikiUtil.provaWikiRender();
	}

	@RequestMapping(value = "/movie/inserimentoIndicizzato", method = { RequestMethod.GET, RequestMethod.POST })
	public String processMovieByIndex ( HttpServletRequest request,Model model,@RequestParam String movieStartIndex, @RequestParam String movieEndIndex) throws Exception {

	
		executeScriptRandomInsert(model,Integer.parseInt(movieStartIndex), Integer.parseInt(movieEndIndex));
		

		return "imdbSearch.page";  
	}







	private String executeScriptCleanDb(Model model) {
		// TODO Auto-generated method stub
		movieService.cleanDB();
		return "imdbSearch.page";  

	}



	private void executeScriptRandomInsert(Model model,int startIndex, int endIndex) throws IOException {
		// TODO Auto-generated method stub
		String pattern="tt";
		for(int i =startIndex;i<endIndex;i++)
		{
			String index=""+i;
			String index0="";
			for(int j=0;j<7-index.length();j++)
			{
				index0+="0";
			}
			index=pattern+index0+index;
			URL url = new URL("http://www.omdbapi.com/?i="+index+"&y=&plot=long&r=json");
			createMovieInstance(url,model);

		}
	}



	private void createMovieInstance(URL url, Model model) throws IOException {
		// TODO Auto-generated method stub
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		int code = connection.getResponseCode();
		System.out.println(code);
		BufferedReader read = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line ;
		StringBuffer stringBuffer = new StringBuffer();
		while((line = read.readLine())!=null)
		{
			stringBuffer.append(line);
		}
		int result=0;
		MovieDTO movie=null;
		JSONObject jSONObject = new JSONObject(stringBuffer.toString());
		if(jSONObject.get("Response").equals("False"))
		{
			result=5;
		}
		else
		{
			movie = generateMovieDTO(jSONObject);
			result = createMovieTable(movie);
		}

		generateResponseMessage(result,model, movie);
	}



	private void executeScriptInsert(String movieName, Model model) throws IOException {
		// TODO Auto-generated method stub
		URL url = new URL("http://www.omdbapi.com/?t="+movieName+"&y=&plot=short&r=json");
		createMovieInstance(url,model);
	}



	private void generateResponseMessage(int result, Model model,MovieDTO movieDTO) {
		// TODO Auto-generated method stub
		String message="";
		switch(result)
		{
		case 0:message="Errore di inserimento";
		break;
		case 1:message="Inserimento andato a buon fine";
		break;
		case 2:message="Record già inserito";
		break;
		default:message="Errore anomalo situazione non gestita";
		}
		message= message+" "+MovieGeneratorUtil.printMovie(movieDTO);
		model.addAttribute("responseMessage",message );
	}



	private String parseMovieName(String movieName) {
		// TODO Auto-generated method stub
		return movieName.replace(" ", "+");
	}

	


	private MovieDTO generateMovieDTO(JSONObject jSONObject)
	{
		Iterator<String> keys = jSONObject.keys();
		MovieDTO movie = new MovieDTO();
		while (keys.hasNext())
		{
			String key = keys.next();
			System.out.println(key);
			System.out.println(jSONObject.get(key));
			switch(key.trim())
			{
			case  "Released":break;
			case   "Title": movie.setName(((String) jSONObject.get("Title")));break;
			case   "imdbVotes":movie.setImdbRating(MovieGeneratorUtil.convertImdbRating((String)jSONObject.get("imdbVotes")));break;
			case   "Runtime":movie.setLength(StringParseUtil.fromStringLengthToInt((String)jSONObject.get("Runtime")));break;
			case   "Country":movie.setCountries(MovieGeneratorUtil.parseCountries((String)jSONObject.get("Country")));break;
			case   "Year":movie.setYear(Integer.parseInt((String) jSONObject.get("Year")));break;
			case   "Genre":movie.setGenre(( MovieGeneratorUtil.parseGenres((String)jSONObject.get("Genre"))));break;
			case   "Actors":movie.setActors((( MovieGeneratorUtil.parseActors((String)jSONObject.get("Actors")))));break;
			case    "Plot": movie.setPlot((String)jSONObject.get("Plot"));


			}

		}
		movie.setMovieKey(KeyGenerator.movieKeyGeneratorUtil(movie));
		return movie;
	}

	
	private int createMovieTable(MovieDTO movie) {
		// TODO Auto-generated method stub			
		return movieService.insertMovie(movie);
	}



	//	private String parseCountry(Object object) {
	//		// TODO Auto-generated method stub
	//		CountryDTO countryDTO = new CountryDTO;
	//		return null;
	//	}

	private DirectorDTO parseDirector(String directorString ) {
		// TODO Auto-generated method stub
		DirectorDTO director = new DirectorDTO();
		director.setName("Martin");
		director.setSurname("Scorsese");
		return director;
	}


}
