package com.uow.cartopia;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uow.Service.CarParkService;

@Controller
public class WebController {

	@Autowired
	CarParkService carParkService;
			
	 @GetMapping("/")
		public String index(Model model) {
		 	model.addAttribute("ID", "helli");
			
			return "index";
		}
}
