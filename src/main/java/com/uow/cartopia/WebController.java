package com.uow.cartopia;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uow.Model.CarPark;
import com.uow.Model.Login;
import com.uow.Model.User;
import com.uow.Service.CarParkService;

@Controller
public class WebController {

	@Autowired
	CarParkService carParkService;
	
	
	@GetMapping("/")
	public String index(Model model) {
		return "redirect:/index";
	}

	@GetMapping("/index")
	public String home(Model model) {
		return "test";
	}



	@GetMapping("login")
	public String login(Model model, HttpSession session) {
		model.addAttribute("login", new Login());
		model.addAttribute("user", new User());
		if(session.getAttribute("userID") != null) {
			return "redirect:/index";
		}
		return "login";
	}
	
	@GetMapping("driverPage")
	public String driverPage(Model model, HttpSession session) {
//		if(session.getAttribute("userID") == null) {
//			return "redirect:/login";
//		}
		model.addAttribute("username", session.getAttribute("username"));
		return "driverHome";
	}
	
	@GetMapping("driverProfile")
	public String driverProfile(Model model, HttpSession session) {
//		if(session.getAttribute("userID") == null) {
//			return "redirect:/login";
//		}
		model.addAttribute("username", session.getAttribute("username"));
		return "driverProfile";
	}
	
	@GetMapping("PageTest")
	public String pageTest(Model model, HttpSession session) {
		model.addAttribute("userID", session.getAttribute("userID"));
		return "test";
	}
	

	@GetMapping("/home")
	public String Home(Model model) {
		return "home";
	}

	@GetMapping("/carparkInfo")
	public String CarParkInfo(Model model) {
		return "carparkInfo";
	}
}
