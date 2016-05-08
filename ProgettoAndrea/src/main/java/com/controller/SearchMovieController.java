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

	@RequestMapping(value = "/searchMovieResult", method = { RequestMethod.GET, RequestMethod.POST })
	public String ricercaFilmRisultati ( HttpServletRequest request,@ModelAttribute("searchMovieForm") SearchMovieForm searchMovie,Model model) throws Exception {
		int recordPerPage=15;
		
		model.addAttribute("tableResult", true);
		SearchDTO search =SearchUtil.convertFromModelToSearchDTO(searchMovie,request);
		
		//insertMovieService.insertTranslation(search.getDirectors(),"director");
		Integer count;
		List<MovieDTO> movieList = searchMovieService.getListaFilm(search);
		model.addAttribute("generiList",searchMovieService.getListaGeneri());
		model.addAttribute("listMovies",movieList);

		if(movieList == null || movieList.isEmpty())
		{
			MessageErrorWrapper.saveMessage(model, "Non sono stati trovati risultati","Ripetere la ricerca");
			return "errors.page";
		}
		
		int numPages=search.getCountResult()/recordPerPage;
		if(numPages>10)
		{
			MessageErrorWrapper.saveMessage(model, "Numero di risultati elevato. Verranno visualizzate le prime dieci pagine","Ripetere la ricerca con criteri più restrittivi");
		}
		generateHiddenForm(search,request);
		request.setAttribute("numPages", numPages);
		
		System.out.println(search.getCountResult());
		return "searchMovieResult.page";  
		 
	}

	private void generateHiddenForm(SearchDTO search, HttpServletRequest request) {
		// TODO Auto-generated method stub
		request.setAttribute("genereList", search.getGenres());
		request.setAttribute("actorList", search.getActors());
		if(search.isAndGenres())
		{
			request.setAttribute("unisciGeneri", 1);
		}
		request.setAttribute("year", search.getYears());

	}


	
}
