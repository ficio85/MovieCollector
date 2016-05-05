package com.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dto.MovieDTO;
import com.dto.SearchDTO;
import com.form.SearchMovieForm;
import com.service.InsertMovieService;
import com.service.SearchMovieService;
import com.util.JsoupUtil;
import com.util.MessageErrorWrapper;
import com.util.SearchUtil;

@Controller
public class SearchMovieController {
	@Autowired
	@Qualifier("searchMovieService")
	private SearchMovieService searchMovieService;
	
	@Autowired
	@Qualifier("insertMovieService")
	private InsertMovieService insertMovieService;

	@RequestMapping(value = "movie/searchMovieResult", method = { RequestMethod.GET, RequestMethod.POST })
	public String ricercaFilmRisultati ( HttpServletRequest request,@ModelAttribute("searchMovieForm") SearchMovieForm searchMovie,Model model) throws Exception {

		
		model.addAttribute("tableResult", true);
		SearchDTO search =SearchUtil.convertFromModelToSearchDTO(searchMovie);

		//insertMovieService.insertTranslation(search.getDirectors(),"director");

		List<MovieDTO> movieList = searchMovieService.getListaFilm(search);
		model.addAttribute("generiList",searchMovieService.getListaGeneri());
		model.addAttribute("listMovies",movieList);

		if(movieList == null || movieList.isEmpty())
		{
			MessageErrorWrapper.saveMessage(model, "Non sono stati trovati risultati","Ripetere la ricerca");
		}
		return "searchMovie.page";  
		 
	}


	
}
