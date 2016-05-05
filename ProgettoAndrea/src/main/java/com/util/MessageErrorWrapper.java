package com.util;

import org.springframework.ui.Model;

public class MessageErrorWrapper {

	
	public static void saveMessage(Model model,String header, String msg)
	{
		MessageError error = new MessageError();
		error.setHeader(header);
		error.setMsg(msg);
		model.addAttribute("isError",true);
		model.addAttribute("error",error);
	}
	
	public static void saveMessage(Model model, String msg)
	{
		MessageError error = new MessageError();
		error.setMsg(msg);
		model.addAttribute("isError",true);
		model.addAttribute("error",error);
	}
}
