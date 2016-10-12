package com.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ActorDAO;
import com.dao.DirectorDAO;
import com.dao.GuidaTvDAO;
import com.dao.InsertMovieDAO;
import com.dao.LabelDAO;
import com.dao.MovieRankDAO;
import com.dao.SearchMovieDAO;
import com.dto.ActorDTO;
import com.dto.DirectorDTO;
import com.dto.GenereDTO;
import com.dto.LabelDTO;
import com.dto.MovieDTO;
import com.dto.ProgramTvMovieDTO;
import com.dto.UserMovieRateDTO;
import com.dto.WriterDTO;
import com.eccezione.WarningException;
import com.jsonResponse.RateResponse;
import com.util.JsoupUtil;
import com.util.SessionUtil;

@EnableAsync
@Service("guidaTVService")
public class GuidaTvService {
	
	@Autowired
	@Qualifier("guidaTvDAO")
	GuidaTvDAO guidaTvDAO;
	
	
	public void insertProgrammiTv(List<ProgramTvMovieDTO> programmi) {
		// TODO Auto-generated method stub
		for(ProgramTvMovieDTO program : programmi)
		{
			if(program.getMovie().getMovieKey()!=null)
			{
				guidaTvDAO.insertProgrammaTv(program);
			}
		}
	}
	
	public ProgramTvMovieDTO getFirstProgrammaTv() {
		
		return guidaTvDAO.getFirstProgrammaTv();
	}
	
	public int deleteGuidaTv() {
		
		return guidaTvDAO.deleteGuidaTv();
	}
}
