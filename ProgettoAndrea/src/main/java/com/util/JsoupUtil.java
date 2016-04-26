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

import com.dto.DirectorDTO;
import com.dto.MovieDTO;

public class JsoupUtil {

	private static String [] parsingArray={"Cinema","Cinema_2","Attore","Attrice","Filmografia"};
	
	public static List <MovieDTO> wikiInspect(String actor) throws Exception {
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
			childNodes = extractChildNodes(doc);
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

	private static List<Node> extractChildNodes(Document doc) throws Exception{

		List<Node> childNodes = null;
		for(String parsingString:parsingArray)
		{
			Element content = doc.getElementById(parsingString);
			if(content==null)
			{
				continue;
			}
			else
			{
				childNodes = extractNodes(content, childNodes);
				if (childNodes!=null)
				{
					return childNodes;
				}

			}

		}
		
		if(childNodes==null)
		{
			throw new Exception();
		}
		return childNodes;

	}

	private static List<Node> extractNodes(Element content, List<Node> childNodes) {
		Tag tag =((Element)content.parentNode().nextSibling()).tag();
		if(tag.getName().equals("ul"))
		{
			childNodes = content.parentNode().nextSibling().childNodes();
		}
		return childNodes;
	}

	private static String parseActor(String actor) {
		// TODO Auto-generated method stub
		return actor.trim().replace(" ", "_");

	}

}
