package com.mcf.shares.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/shares/infos")
public class InfoController {
	
	@RequestMapping("/hello.mc")
	public ModelAndView crawlercommodity() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("hello", "hello");
		mv.setViewName("/infos/hello");
		return mv;
	}

}
