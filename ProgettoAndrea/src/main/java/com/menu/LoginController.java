package com.menu;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.UserDTO;
import com.util.PropertiesHandler;
@Controller
public class LoginController {
	@Autowired
	@Qualifier("provaDAO")
	private ProvaDAO provaDAO;

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
		return "main.page";  
		 
	}
}
