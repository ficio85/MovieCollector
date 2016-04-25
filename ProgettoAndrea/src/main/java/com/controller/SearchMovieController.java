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
		SearchDTO search = new SearchDTO();
		search.setSearchActor(true);
		List <String> actors = new ArrayList <String> ();
		actors.add(searchMovie.getActor());
		search.setActors(actors);
		wikiImdbCompleta(search);
		List<MovieDTO> movieList = searchMovieService.getListaFilm(search);
		model.addAttribute("generiList",searchMovieService.getListaGeneri());
		model.addAttribute("listMovies",movieList);

//		searchMovieService..SearchUtil.convertFromModelToSearchDTO(searchMovie);
		return "searchMovie.page";  
		 
	}


	private void wikiImdbCompleta(SearchDTO search) throws Exception {
		// TODO Auto-generated method stub
		if(search.isSearchActor())
		{
			List<String> listActors = search.getActors();
			for(String actor: listActors)
			{
				List <MovieDTO> moviesByActor;
				try{
					moviesByActor=JsoupUtil.wikiInspect(actor);

				}
				catch(FileNotFoundException ex)
				{
					continue;
				}
				catch(Exception ex)
				{
					throw ex;
				}
				moviesByActor=JsoupUtil.wikiInspect(actor);
				insertMovieService.updateMoviesByActor(moviesByActor, actor);
				
			}
		}
	}
}