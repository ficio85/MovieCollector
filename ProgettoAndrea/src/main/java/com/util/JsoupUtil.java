package com.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

import com.dto.ActorDTO;
import com.dto.DirectorDTO;
import com.dto.MovieDTO;

public class JsoupUtil {

	private static String [] parsingArrayActors={"Cinema","Cinema_2","Attore","Attrice","Filmografia"};
	private static String [] parsingArrayDirectors={"Cinema","Cinema_2","Regista","Lungometraggi","Cortometraggi","Cortometraggi_documentari","Filmografia"};


	public static List <MovieDTO> wikiInspect(String actor, String type) throws Exception {
		// TODO Auto-generated method stub
		String actorToQuery = parseActor(actor);
		URL url = new URL("https://it.wikipedia.org/wiki/"+actorToQuery);
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
		Document doc = Jsoup.parse(String.valueOf(tmp));
		List<Node> childNodes;
		try {
			childNodes = extractChildNodes(doc,type);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}

		ArrayList<MovieDTO> moviesActor = new ArrayList();
		for(Node node:childNodes)
		{
			Pattern pattern = Pattern.compile("^d{4}$");
			MovieDTO movie = new MovieDTO();
			Elements element = ((Element)node).getElementsMatchingOwnText(pattern);
			setMovieYear(node, movie);
			setMovieDirector(node,movie);
			Elements elements = ((Element)node).getElementsByTag("i");
			int i=0;
			if(elements.size()==1)
			{
				movie.setTitoloItaliano(elements.get(0).getElementsByTag("a").get(0).ownText()) ;
				movie.setTitle(elements.get(0).getElementsByTag("a").get(0).ownText());
				moviesActor .add(movie);
			}
			else
			{
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
							movie.setTitoloItaliano(el.ownText()) ;
						}
						else
						{
							movie.setTitle(el.ownText());
						}
					}
					i++;
				}
				moviesActor.add(movie);  
			}

		}

		return moviesActor;



	}

	private static void setMovieDirector(Node node, MovieDTO movie) {
		// TODO Auto-generated method stub
		DirectorDTO dir = new DirectorDTO();
		List <DirectorDTO> directorList = new ArrayList <DirectorDTO>();

		Elements elementWithLinks = ((Element)node).getElementsByTag("a");
		if(elementWithLinks!=null && elementWithLinks.size()>1 && elementWithLinks.get(1)!=null)
		{
			dir.setName(elementWithLinks.get(1).ownText());
		}
		else
		{
			dir.setName("NN");
		}
		directorList.add(dir);

		movie.setDirectors(directorList);

	}

	private static void setMovieYear(Node node, MovieDTO movie) {
		Elements elementWithLinks = ((Element)node).getElementsByTag("a");
		int year = 0;
		try{
			year= Integer.parseInt(elementWithLinks.get(elementWithLinks.size()-1).ownText());
		}
		catch(Exception ex)
		{
			Pattern  pat=Pattern.compile("\\((\\d{4})\\)$");
			Element element= ((Element)node);
			Matcher matcher = pat.matcher(element.html());
			matcher.find();
			String matching;
			try{
				matching= matcher.group(1);
			}
			catch(IllegalStateException ex2)
			{
				Pattern  pat2=Pattern.compile("%[^0-9]%");
				matcher = pat2.matcher(element.text());
				matcher.find();
				try{
					matching= matcher.group(1); 
				}
				catch(IllegalStateException ex3)
				{
					matching="0";
				}
			}

			year=Integer.parseInt(matching);

		}
		movie.setYear(year);

	}

	private static List<Node> extractChildNodes(Document doc,String type) throws Exception{
		String[] parsingArray = null;
		if(type.equals("actor")){
			parsingArray=parsingArrayActors;
		}
		else if(type.equals("director"))
		{
			parsingArray=parsingArrayDirectors;

		}
		List<Node> childNodes = new ArrayList <Node>();
		for(int i=0;i<=2;i++)
		{
			for(String parsingString:parsingArray)
			{
				Element content = doc.getElementById(parsingString);
				if(content==null)
				{
					continue;
				}
				else
				{
					List<Node> nodeParsed = null;

					nodeParsed = extractNodes(content, childNodes,i);
					if (nodeParsed!=null)
					{
						 childNodes.addAll(nodeParsed);
					}

				}

			}
		}

		if(childNodes==null)
		{
			throw new Exception();
		}
		return childNodes;

	}

	private static List<Node> extractNodes(Element content, List<Node> childNodes,int deep) {

		Node uncle = null;
		if(deep ==0)
		{
			uncle=content.parentNode().nextSibling();
		}
		else if (deep ==1)
		{
			uncle=content.parentNode().nextSibling().nextSibling();

		}
		else if (deep==2)
		{
			uncle=content.parentNode().nextSibling().nextSibling().nextSibling();
		}
		Tag tag =((Element)uncle).tag();
		if(tag.getName().equals("ul"))
		{
			childNodes = uncle.childNodes();
		}
		return childNodes;
	}

	private static String parseActor(String actor) {
		// TODO Auto-generated method stub
		return actor.trim().replace(" ", "_");

	}

	
	public static List<ActorDTO> imdbInspect(String imdbIndex) throws Exception {
		// TODO Auto-generated method stub
		URL url = new URL("http://www.imdb.com/title/"+imdbIndex+"/fullcredits?ref_=tt_ql_1");
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
		Document doc = Jsoup.parse(String.valueOf(tmp));
		Element table = doc.select("table.cast_list").get(0); //select the first table.
		Elements rows = table.select("tr");

		for (int i = 1; i < rows.size(); i++) { //first row is the col names so skip it.
		    Element row = rows.get(i);
		    Element colNameActor = row.select("td.itemprop").get(0);
		    List<Node> nodeChildren = colNameActor.childNodes();
		    for(Node node:nodeChildren)
		    {
		    	if(((Element)node).tag().getName().equals("a"))
		    	{
		    		String link =node.attr("href");
		    		
		    		System.out.println(link);
		    		//prendo il nome dell'attore
		    		Node nodeActor=((Element)node).children().get(0);
		    		System.out.println(((Element)nodeActor).ownText());
		    		
		    	}
		    	else
		    	{
		    		
		    	}
		    }
		    Element colNameCharacter = row.select("td.character").get(0);
		    Node nodeCharacter = colNameActor.childNodes().get(0).childNodes().get(0);
		    System.out.println(((Element)nodeCharacter).ownText());
		  
		    
		}
		return null;
		



	}

}
