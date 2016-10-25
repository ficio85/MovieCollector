package com.menu;

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
import com.dto.MovieDTO;
import com.dto.UserDTO;
import com.dto.UserGuidaTvDTO;
import com.service.SearchMovieService;
import com.util.PropertiesHandler;
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
		List<UserGuidaTvDTO> userPreferredMovieList = guidaTvDAO.getPreferredUserGuidaTv(user.getCodPers());
		UserGuidaTvDTO userPreferredMovie = userPreferredMovieList.get(0);
		MovieDTO movieSuggested= searchMovieService.getAllMovieDetail(userPreferredMovie.getMovie(), user.getCodPers());
		movieSuggested.setRateSuggested(userPreferredMovie.getLike());
		request.setAttribute("movieSuggested", movieSuggested);
		
	}
}
