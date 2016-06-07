package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dto.MovieDTO;
import com.dto.SearchDTO;
import com.form.SearchMovieForm;
import com.util.MessageErrorWrapper;
import com.util.SearchUtil;
import com.util.XmltvParserUtil;

@Controller
public class GuidaTVController {

	
	@RequestMapping(value = "/guidaTV", method = { RequestMethod.GET, RequestMethod.POST })
	public String displayGuidaTV ( HttpServletRequest request,Model model) throws Exception {
		List <ProgrammaTvDTO> programmi = XmltvParserUtil.getProgrammiTV();
		return null;
		
	}
	
	
	
	
}
