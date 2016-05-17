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
import com.util.MovieGeneratorUtil;
import com.util.SearchUtil;
import com.util.SessionUtil;

@Controller
public class InsertMovieController {
	@Autowired
	@Qualifier("searchMovieService")
	private SearchMovieService searchMovieService;

	@Autowired
	@Qualifier("insertMovieService")
	private InsertMovieService insertMovieService;

	@RequestMapping(value = "/insertLabel", method = { RequestMethod.GET, RequestMethod.POST })
	public String ricercaFilmRisultati ( HttpServletRequest request,@ModelAttribute("searchMovieForm") SearchMovieForm searchMovie,Model model) throws Exception {
		//		String prova = request.getParameter("labels");
		String movie= request.getParameter("indexMovie");
		
		//insertMovieService.deleteUserLabel(SessionUtil.getCodPers(request),movie,MovieGeneratorUtil.getLabels(request.getParameterValues("labels[]")));
		insertMovieService.insertUserLabel(SessionUtil.getCodPers(request),movie,MovieGeneratorUtil.getLabels(request.getParameterValues("labels[]")));
		return null;
	}





}
