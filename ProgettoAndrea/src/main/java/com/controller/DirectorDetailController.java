package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dto.ActorDTO;
import com.dto.DirectorDTO;
import com.dto.MovieDTO;
import com.dto.SearchDTO;
import com.service.InsertMovieService;
import com.service.SearchMovieService;
import com.util.ActorGeneratorUtil;
import com.util.DirectorGeneratorUtil;
import com.util.MessageErrorWrapper;
import com.util.SessionUtil;
@Controller
public class DirectorDetailController {
	


	@Autowired
	@Qualifier("searchMovieService")
	private SearchMovieService searchMovieService;

	@Autowired
	@Qualifier("insertMovieService")
	private InsertMovieService insertMovieService;
	
	
	@RequestMapping(value = "/detailDirector", method = { RequestMethod.GET, RequestMethod.POST })
	public String dettaglioRegista ( HttpServletRequest request,Model model) throws Exception {
		int recordPerPage=15;

		String director= (String) request.getParameter("director").trim();
		DirectorDTO directorDto= searchMovieService.getAllDirectorDetail(director);
		SearchDTO search = new SearchDTO();
		ArrayList <String> directors = new ArrayList <String>();
		directors.add(director);
		search.setDirectors(directors);
		search.setSearchDirector(true);
		List <MovieDTO> movieList = searchMovieService.getListaFilm(search);
		model.addAttribute("listMovies",movieList);
		DirectorDTO directorToComplete = new DirectorDTO(director);
		//insertMovieService.inspectImdbForDirector(movieList.get(0).getImdbKey(), directorToComplete);
		DirectorGeneratorUtil.getCompleteInfoDirector(directorToComplete);
		int numPages=search.getCountResult()/recordPerPage+1;
//		generateHiddenForm(search,request);
		request.setAttribute("director", directorToComplete);
		request.setAttribute("numPages", numPages);	
		return "detailDirector.page";
	}
	
	
	


}
