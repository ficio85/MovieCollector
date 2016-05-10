package com.controller;

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
import com.util.MessageErrorWrapper;
import com.util.SearchUtil;
@Controller
public class MovieDetailController {

	@Autowired
	@Qualifier("searchMovieService")
	private SearchMovieService searchMovieService;

	@RequestMapping(value = "/detailMovie", method = { RequestMethod.GET, RequestMethod.POST })
	public String ricercaFilmRisultati ( HttpServletRequest request,Model model) {
		String indexMovie= (String) request.getParameter("indexMovie");
		MovieDTO movie= searchMovieService.getAllMovieDetail(indexMovie);
		request.setAttribute("movie", movie);
		return "detailMovie.page";
	}
	
	
	
}
