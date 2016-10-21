package com.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
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

import com.algorithm.MovieLikeAlgorithm;
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
	
	@Autowired
	@Qualifier("searchMovieService")
	private SearchMovieService searchMovieService;
	
	@Autowired
	@Qualifier("movieLikeAlgorithm")
	private MovieLikeAlgorithm movieLikeAlgorithm;

	
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
	
	public List<ProgramTvMovieDTO> getProgrammiTv(String user, Timestamp dateBegin, Timestamp dateEnd, String channel, String platform, String type) {
		// TODO Auto-generated method stub
		
		List<ProgramTvMovieDTO> programmiTv = guidaTvDAO.getMovieTvList(dateBegin,dateEnd,channel,platform,type);
		
		for(ProgramTvMovieDTO programma : programmiTv)
		{
			if(!programma.getMovie().getMovieKey().equals("NONPRESENTE"))
			{
				programma.setMovie(searchMovieService.getAllMovieDetail(programma.getMovie().getMovieKey(), user));
				float rateMovie = movieLikeAlgorithm.getMovieLikeRate(programma.getMovie(), user);
				UserMovieRateDTO userRateDTO = new UserMovieRateDTO();
				userRateDTO.setMovie(programma.getMovie().getMovieKey());
				userRateDTO.setRate(rateMovie);
				userRateDTO.setUser(user);
				guidaTvDAO.insertUserGuidaTv(userRateDTO);

			}
		}
		return programmiTv;
	}
	
	
	
	
	
	
	public List<ProgramTvMovieDTO> getFirstProgrammaTv() {
		
		return guidaTvDAO.getFirstProgrammaTv();
	}
	
	public int deleteGuidaTv() {
		
		return guidaTvDAO.deleteGuidaTv();
	}
}
