package com.menu;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dao.GuidaTvDAO;
import com.dao.SearchMovieDAO;
import com.dto.MovieDTO;
import com.dto.TipoData;
import com.dto.UserDTO;
import com.dto.UserGuidaTvDTO;
import com.service.SearchMovieService;
import com.util.DateUtil;
import com.util.PropertiesHandler;
import com.util.XmltvParserUtil;
@Controller
public class LoginController {
	@Autowired
	@Qualifier("provaDAO")
	private ProvaDAO provaDAO;
	
	@Autowired
	@Qualifier("guidaTvDAO")
	private GuidaTvDAO guidaTvDAO ;

	@Autowired
	@Qualifier("searchMovieService")
	private SearchMovieService searchMovieService ;
	
	@Autowired
	@Qualifier("searchMovieDAO")
	private SearchMovieDAO searchMovieDAO ;
	
	@RequestMapping(value = "/accetta", method = { RequestMethod.GET, RequestMethod.POST })
	public String accetta ( HttpServletRequest request,Model model) throws Exception {
		//ProvaDTO dto = provaDAO.provaQuery();
		UserDTO userDto = new UserDTO();
		if(request.getParameter("username")!=null && !request.getParameter("username").trim().equals(""))
		{
			userDto.setCodPers(request.getParameter("username"));
		}
		else
		{
			userDto.setCodPers("ANDREAFICETIPROVA");
		}
	System.out.println(PropertiesHandler.retrieveInternetInfo());
//		model.addAttribute("user",dto);
		request.getSession().setAttribute("userSession", userDto);
//		System.out.println(dto.getUser() + " "+ dto.getPassword());
		caricaHomePage(userDto, request);
		return "main.page";  
		 
	}

	private void caricaHomePage(UserDTO user,HttpServletRequest request) {
		// TODO Auto-generated method stub
		loadSuggestedMovie(user, request);
		//loadBirthdayMovie(request);
		XmltvParserUtil.getProgrammiNetflix();
		
	}

	private void loadBirthdayMovie(HttpServletRequest request) {
		// TODO Auto-generated method stub
		List <java.sql.Date> sqlDates= new ArrayList <java.sql.Date>();
		Date date1 = DateUtil.getDateofYearsAgo(10);
		sqlDates.add(DateUtil.convertFromJavaToSqlDate(date1));
		Date date2 = DateUtil.getDateofYearsAgo(20);
		sqlDates.add(DateUtil.convertFromJavaToSqlDate(date2));
		Date date3 = DateUtil.getDateofYearsAgo(30);
		sqlDates.add(DateUtil.convertFromJavaToSqlDate(date3));
		Date date4 = DateUtil.getDateofYearsAgo(40);
		sqlDates.add(DateUtil.convertFromJavaToSqlDate(date4));
		List<MovieDTO> movies = searchMovieDAO.getMovieByReleases(sqlDates);
		for(MovieDTO movie: movies)
		{
			System.out.println("");
			System.out.println(movie.getTitle());
			System.out.println(movie.getReleaseDate());
			System.out.println("");

		}
		//prendi il primo film
		TipoData dataoggi = new TipoData(new Date());
	
		MovieDTO movieBirthDay= movies.get(0);
		TipoData dataBirthDay= new TipoData(movieBirthDay.getReleaseDate());
		request.setAttribute("movieBirthDayYears", dataoggi.getYear()-dataBirthDay.getYear());
		request.setAttribute("movieBirthDay", movieBirthDay);
		
	}

	private void loadSuggestedMovie(UserDTO user, HttpServletRequest request) {
		List<UserGuidaTvDTO> userPreferredMovieList = guidaTvDAO.getPreferredUserGuidaTv(user.getCodPers());
		if(userPreferredMovieList!=null && userPreferredMovieList.size()!=0)
		{
			UserGuidaTvDTO userPreferredMovie = userPreferredMovieList.get(0);
			MovieDTO movieSuggested= searchMovieService.getAllMovieDetail(userPreferredMovie.getMovie(), user.getCodPers());
			movieSuggested.setRateSuggested(userPreferredMovie.getLike());
			request.setAttribute("movieSuggested", movieSuggested);
			request.setAttribute("isUserGuidaTv", 1);
		}
		else
		{
			request.setAttribute("isUserGuidaTv", 0);
		}
	}
}
