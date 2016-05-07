package com.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.DirectorDTO;
import com.dto.MovieDTO;
import com.form.SearchMovieForm;
import com.service.SearchMovieService;
import com.util.KeyGenerator;
import com.util.StringParseUtil;
@Controller
public class DispatchMenuController {
	
	@Autowired
	@Qualifier("searchMovieService")
	private SearchMovieService searchMovieService;
	
	@RequestMapping(value = "/imdbWebService", method = { RequestMethod.GET, RequestMethod.POST })
	public String accetta ( HttpServletRequest request,Model model) throws Exception {
		return "imdbSearch.page";  
		 
	}
	
	@RequestMapping(value = "/searchMovie", method = { RequestMethod.GET, RequestMethod.POST })
	public String ricerca ( HttpServletRequest request,@ModelAttribute("searchMovie") SearchMovieForm searchMovie,Model model) throws Exception {
//		searchMovie.setGenereList(searchMovieService.getListaGeneri());
		searchMovie.setActor("Robert De Niro");
		model.addAttribute("searchMovieForm",searchMovie);
		model.addAttribute("generiList",searchMovieService.getListaGeneri());
		return "searchMovie.page";  
		 
	}
	
	@RequestMapping(value = "/goHome", method = { RequestMethod.GET, RequestMethod.POST })
	public String goHome ( HttpServletRequest request,@ModelAttribute("searchMovie") SearchMovieForm searchMovie,Model model) throws Exception {

		return "main.page";  
		 
	}
	




	


}
