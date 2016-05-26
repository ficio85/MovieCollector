package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dto.ActorDTO;
import com.dto.MovieDTO;
import com.dto.SearchDTO;
import com.service.InsertMovieService;
import com.service.SearchMovieService;
import com.util.MessageErrorWrapper;
import com.util.SessionUtil;

public class ActorDetailController {
	


	@Autowired
	@Qualifier("searchMovieService")
	private SearchMovieService searchMovieService;

	@Autowired
	@Qualifier("insertMovieService")
	private InsertMovieService insertMovieService;
	
	
	@RequestMapping(value = "/detailActor", method = { RequestMethod.GET, RequestMethod.POST })
	public String dettaglioAttore ( HttpServletRequest request,Model model) throws Exception {
		int recordPerPage=15;

		String act= (String) request.getParameter("actor").trim();
		ActorDTO actor= searchMovieService.getAllActorDetail(act);
		SearchDTO search = new SearchDTO();
		search.isSearchActor();
		ArrayList <String> actors = new ArrayList <String>();
		actors.add(act);
		search.setActors(actors);
		List <MovieDTO> movieList = searchMovieService.getListaFilm(search);
		model.addAttribute("listMovies",movieList);

		if(movieList == null || movieList.isEmpty())
		{
			MessageErrorWrapper.saveMessage(model, "Non sono stati trovati risultati","Ripetere la ricerca");
			return "errors.page";
		}
		
		int numPages=search.getCountResult()/recordPerPage+1;
		
//		generateHiddenForm(search,request);
		request.setAttribute("numPages", numPages);	
		return "detailActor.page";
	}
	
	
	


}
