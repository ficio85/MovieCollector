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

import com.dao.GuidaTvDAO;
import com.dao.UserMovieDAO;
import com.dto.MovieDTO;
import com.dto.SearchDTO;
import com.dto.UserMovieDTO;
import com.dto.UserMovieRateDTO;
import com.form.SearchMovieForm;
import com.service.InsertMovieService;
import com.service.SearchMovieService;
import com.util.JsoupUtil;
import com.util.MessageErrorWrapper;
import com.util.SearchUtil;
import com.util.SessionUtil;

@Controller
public class MyProfileController {
	@Autowired
	@Qualifier("searchMovieService")
	private SearchMovieService searchMovieService;
	
	@Autowired
	@Qualifier("insertMovieService")
	private InsertMovieService insertMovieService;

	@Autowired
	@Qualifier("userMovieDAO")
	private UserMovieDAO userMovieDAO;
	
	
	@RequestMapping(value = "/mostraMioProfilo", method = { RequestMethod.GET, RequestMethod.POST })
	public String ricercaFilmRisultati ( HttpServletRequest request,Model model) throws Exception {
		int recordPerPage=15;
		
			String user = SessionUtil.getCodPers(request);
			SearchDTO search = new SearchDTO();
			search.setCodPers(user);
			search.setSearchByUser(true);
			List<MovieDTO> movieList = searchMovieService.getListaFilm(search);
			model.addAttribute("listMovies",movieList);
			int numPages=search.getCountResult()/recordPerPage+1;	
			request.setAttribute("numPages", numPages);
			return "myProfile.page";  				 
	}

	


	
}
