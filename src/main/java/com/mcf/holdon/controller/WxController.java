package com.mcf.holdon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class WxController {
	
	@RequestMapping("login")
	@ResponseBody
	public String login(){
		return "ok";
	}
}
