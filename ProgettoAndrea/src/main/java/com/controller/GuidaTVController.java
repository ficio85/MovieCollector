package com.controller;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dto.ActorDTO;
import com.dto.FasciaOrariaDTO;
import com.dto.MovieDTO;
import com.dto.PiattaFormaDTO;
import com.dto.ProgramTvMovieDTO;
import com.dto.SearchDTO;
import com.dto.TipoProgrammaDTO;
import com.form.SearchMovieForm;
import com.service.GuidaTvService;
import com.service.InsertMovieService;
import com.service.SearchMovieService;
import com.util.DateUtil;
import com.util.MessageErrorWrapper;
import com.util.MovieCompareUtil;
import com.util.SearchUtil;
import com.util.SessionUtil;
import com.util.XmltvParserUtil;

@Controller
public class GuidaTVController {

	@Autowired
	@Qualifier("searchMovieService")
	private SearchMovieService searchMovieService;

	@Autowired
	@Qualifier("guidaTVService")
	private GuidaTvService guidaTvService;
	
	@Autowired
	@Qualifier("insertMovieService")
	private InsertMovieService insertMovieService;

	@RequestMapping(value = "/guidaTV", method = { RequestMethod.GET, RequestMethod.POST })
	public String displayGuidaTV ( HttpServletRequest request,@ModelAttribute("searchMovie") SearchMovieForm searchTv,Model model) throws Exception {
		//0ttieni la data odierna
	ProgramTvMovieDTO firstProgramma =guidaTvService.getFirstProgrammaTv();
		Timestamp oraInizio =firstProgramma.getOraInizio();
	Calendar calendar = Calendar.getInstance();
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.setTimeInMillis(oraInizio.getTime());		
		int dayOfMonthDB=calendar.get(Calendar.DAY_OF_MONTH);
		if(dayOfMonth!= dayOfMonthDB)
		{
			guidaTvService.deleteGuidaTv();
			List <ProgramTvMovieDTO> programmi = XmltvParserUtil.getProgrammiTV();
			extractMoviekey(programmi);
			guidaTvService.insertProgrammiTv(programmi);
		}

		Timestamp timeToday= new Timestamp(new Date().getTime());
		Timestamp timeBegin = DateUtil.subMinutes(timeToday, 30);
		Timestamp timeEnd = DateUtil.addMinutes(timeToday, 120);


		List <ProgramTvMovieDTO> programmiTv =searchMovieService.getProgrammiTv(SessionUtil.getCodPers(request),timeBegin, timeEnd);

		request.setAttribute("listPiattaforme", PiattaFormaDTO.getValues());
		request.setAttribute("listFasce", FasciaOrariaDTO.getValues());
		request.setAttribute("listTipoProgrammi", TipoProgrammaDTO.getValues());
		request.setAttribute("listMovies", programmiTv);
		return "guidaTv.page";

	}

	private void extractMoviekey(List<ProgramTvMovieDTO> programmi) {
		// TODO Auto-generated method stub
		for(int i =0; i<programmi.size();i++)
		{
			MovieDTO movie = programmi.get(i).getMovie();
			System.out.println(movie.getTitoloItaliano());
			
			

			if(movie.getTitoloItaliano()!=null && !movie.getTitoloItaliano().trim().equals(""))
			{				
				List <MovieDTO> movieResult =null;
			
				movieResult=searchMovieService.extractMovieForTvGuide(movie);
				
				if(movieResult!= null &&  movieResult.size()==1 )
				{
					MovieDTO movieToTranslate=null;
					if(movieResult.size()>1)
					{
						movieToTranslate = distinctMovie(movieResult,movie);
					}
					else
					{
						movieToTranslate=movieResult.get(0);
					}
					movie.setMovieKey(movieToTranslate.getMovieKey());
					insertMovieService.insertInternationalization(movie);
				}
				else
				{
					movie.setMovieKey("NONPRESENTE");
					//movie.setTitle(movie.getTitoloItaliano());
				}
				//se nn c' è gia internazionalizzazione
			}
			else
			{
				movie.setMovieKey("NONPRESENTE");
			}
		}
		
	}


	@RequestMapping(value = "/loadGuidaTV", method = { RequestMethod.GET, RequestMethod.POST })
	public String ricercaFilmRisultati ( HttpServletRequest request,Model model) throws Exception {
		return null;
		
	}

	
	
	private MovieDTO distinctMovie(List<MovieDTO> movieResult, MovieDTO movie) {
		// TODO Auto-generated method stub
		for(MovieDTO singleMovie: movieResult)
		{
			if(MovieCompareUtil.numberSameActors(singleMovie, movie)>2)
				return singleMovie;
		}
		return null;
	}
	
	



}
