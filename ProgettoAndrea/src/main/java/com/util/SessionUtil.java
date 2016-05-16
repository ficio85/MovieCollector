package com.util;

import javax.servlet.http.HttpServletRequest;

import com.dto.UserDTO;

public class SessionUtil {

	public static String getCodPers(HttpServletRequest request){
		
		return ((UserDTO)request.getSession().getAttribute("userSession")).getCodPers();
	}

}
