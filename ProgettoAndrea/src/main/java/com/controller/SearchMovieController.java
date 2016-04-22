package com.controller;

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
import com.service.SearchMovieService;
import com.util.SearchUtil;

@Controller
public class SearchMovieController {
	@Autowired
	@Qualifier("searchMovieService")
	private SearchMovieService searchMovieService;
	

	@RequestMapping(value = "movie/searchMovieResult", method = { RequestMethod.GET, RequestMethod.POST })
	public String ricercaFilmRisultati ( HttpServletRequest request,@ModelAttribute("searchMovieForm") SearchMovieForm searchMovie,Model model) throws Exception {

		
		model.addAttribute("tableResult", true);
		SearchDTO search = new SearchDTO();
		search.setSearchActor(true);
		List <String> actors = new ArrayList <String> ();
		actors.add(searchMovie.getActor());
		search.setActors(actors);
		searchMovieService.getListaFilm(search);
		model.addAttribute("generiList",searchMovieService.getListaGeneri());
//		searchMovieService..SearchUtil.convertFromModelToSearchDTO(searchMovie);
		return "searchMovie.page";  
		 
	}
}
