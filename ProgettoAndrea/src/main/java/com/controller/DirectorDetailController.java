package com.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dao.DirectorDAO;
import com.dto.ActorDTO;
import com.dto.DirectorDTO;
import com.dto.MovieDTO;
import com.dto.SearchDTO;
import com.service.DirectorService;
import com.service.InsertMovieService;
import com.service.SearchMovieService;
import com.service.TranslateServiceAsync;
import com.util.ActorGeneratorUtil;
import com.util.DirectorGeneratorUtil;
import com.util.JsoupUtil;
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
	

	@Autowired
	@Qualifier("directorService")
	private DirectorService directorService;
	
	@Autowired
	@Qualifier("translateServiceAsync")
	private TranslateServiceAsync translateServiceAsync;
	
	@Autowired
	@Qualifier("directorDAO")
	DirectorDAO directorDAO;
	
	@RequestMapping(value = "/detailDirector", method = { RequestMethod.GET, RequestMethod.POST })
	public String dettaglioRegista ( HttpServletRequest request,Model model) throws Exception {
		int recordPerPage=15;

		String director= (String) request.getParameter("director").trim();
		DirectorDTO directorDto= searchMovieService.getAllDirectorDetail(director);
		SearchDTO search = new SearchDTO();
		ArrayList <String> directors = new ArrayList <String>();
		search.setDirectors(directors);
		search.setSearchDirector(true);
		List <MovieDTO> movieList = searchMovieService.getListaFilm(search);
		model.addAttribute("listMovies",movieList);
		if(directorDto.getiCompleto()!=1 )
		{
			request.setAttribute("iCompleto", 0);
			request.setAttribute("movieToParse", movieList.get(0).getImdbKey());
		}
		else
		{
			request.setAttribute("iCompleto", 1);
		}
		directorService.getCompleteInfoDirector(movieList.get(0).getImdbKey(),directorDto);
		int numPages=search.getCountResult()/recordPerPage+1;
//		generateHiddenForm(search,request);
		request.setAttribute("director", directorDto);
		request.setAttribute("numPages", numPages);	
		return "detailDirector.page";
	}
	
	@RequestMapping(value = "/loadDirectorDetail", method = { RequestMethod.GET, RequestMethod.POST })
	public String loadDirectorDetail ( HttpServletRequest request,Model model) throws Exception {
	{
		
	
		DirectorDTO director = directorDAO.getDirectorDetail(dir);
		if(director.getiCompleto()!=1)
		{
			System.out.println("Inizio thread");
			DirectorDTO directorInspected = JsoupUtil.imdbInspectDirectorFromMovie(movieToParse);
			director.setImdbIndex(directorInspected.getImdbIndex());
			JsoupUtil.generateImdbDirectorInfo(director);
			director.setiCompleto(1);
			director.setTimeiCompleto(new Timestamp(new Date().getTime()));
			directorDAO.updateDirectorInfo(director);
		}
		if(director.getImages()==null || director.getImages().size()==0)
		{
			JsoupUtil.generateWikiDirectorInfo(director);
	
			directorDAO.insertDirectorImages(director);
		
		}
	}
	
	
	


}
