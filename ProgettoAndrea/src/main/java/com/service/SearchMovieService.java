package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dao.GenereDAO;
import com.dao.InsertMovieDAO;
import com.dao.SearchMovieDAO;
import com.dto.GenereDTO;

@Service("searchMovieService")
public class SearchMovieService {

	
	@Autowired
	@Qualifier("genereDAO")
	GenereDAO genereDAO;
	
	public List <GenereDTO> getListaGeneri(){
		return genereDAO.getListaGeneri();
		
	}
}
