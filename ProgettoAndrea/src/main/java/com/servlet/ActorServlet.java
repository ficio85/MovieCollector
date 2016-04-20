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

import com.service.ActorService;

@RestController
public class ActorServlet  {

       @Autowired
       @Qualifier("actorService")
       private ActorService actorService;
       
            
       @RequestMapping(value = "/loadActors",method = {RequestMethod.GET,RequestMethod.POST},headers="Accept=*/*",produces = "application/json")
       @ResponseBody
       public   JSONObject getActorsList(@RequestParam(value="stringActor") String stringActorSearch) {
    	   
              List<String> listaAttori = actorService.getActorsList(stringActorSearch); 
              JSONObject json = new JSONObject();
              json.put("prova", "Brad Pitt");
              return json;
       }

       
       

       
       
}

