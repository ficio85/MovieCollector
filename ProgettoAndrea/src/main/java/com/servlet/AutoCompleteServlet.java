package com.servlet;


import java.util.List;

import org.codehaus.jackson.map.JsonSerializer;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dto.LabelDTO;
import com.service.ActorService;
import com.service.DirectorService;
import com.service.SearchMovieService;

@RestController
public class AutoCompleteServlet  {
	@Autowired
	@Qualifier("searchMovieService")
	private SearchMovieService searchMovieService;
	
	
       @Autowired
       @Qualifier("directorService")
       private DirectorService directorService;
       
            
       @RequestMapping(value = "/loadDirectors",method = {RequestMethod.GET,RequestMethod.POST})
       @ResponseBody
       public   String[] getActorsList(@RequestParam(value="stringDirector") String stringDirectorSearch) {
    	   
              List<String> listaRegisti = directorService.getDirectorsList(stringDirectorSearch); 

              String [] arrayDirectors= new String[listaRegisti.size()];
              if(listaRegisti!=null && listaRegisti.size()!=0)
              {
                 
                  for(int j=0;  j< listaRegisti.size() ;j++)
                  {
                	  arrayDirectors[j]=listaRegisti.get(j); 
                  }

              }
              return arrayDirectors;
       }

       
       @RequestMapping(value = "/loadLabels",method = {RequestMethod.GET,RequestMethod.POST})
       @ResponseBody
       public   String[] getLabelList(@RequestParam(value="stringLabel") String stringLabelSearch) {
    	   
              List<LabelDTO> listaLabels = searchMovieService.getListaLabel(stringLabelSearch); 

              String [] arrayLabels= new String[listaLabels.size()];;
              if(listaLabels!=null && listaLabels.size()!=0)
              {
                 
                  for(int j=0;  j< listaLabels.size() ;j++)
                  {
                	  arrayLabels[j]=listaLabels.get(j).getName(); 
                  }

              }
              return arrayLabels;
       }

       
       
}

