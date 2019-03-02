package com.uow.cartopia;

import java.util.ArrayList;
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

import com.uow.Model.Bookmark;
import com.uow.Model.CarPark;
import com.uow.Model.DriverCar;
import com.uow.Model.Login;
import com.uow.Model.User;
import com.uow.Service.CarParkService;
import com.uow.Service.UserService;

@Controller
public class WebController {

	@Autowired
	CarParkService carParkService;
	@Autowired
	UserService userService;
	
	
	@GetMapping("/")
	public String index(Model model) {
		return "redirect:/home";
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
	

	
	@GetMapping("PageTest")
	public String pageTest(Model model, HttpSession session) {
		model.addAttribute("userID", session.getAttribute("userID"));
		return "test";
	}
	

	@GetMapping("/home")
	public String Home(Model model, HttpSession session) {
		int userID = 0;
		List<CarPark> bookmark = new ArrayList<CarPark>();
		User user = new User();
		if (session.getAttribute("userID") != null) {
			userID = (int) session.getAttribute("userID");
			user = userService.getUserInfo(userID);
		}
		model.addAttribute("userID",userID);
		List<CarPark> list = carParkService.getExceptCarPark(userID);
		model.addAttribute("carParkList", list);
		bookmark = carParkService.getBookMarkCarPark(userID);
		model.addAttribute("bookmark", bookmark);
		
		return "home";
	}
	
	@GetMapping("/carparkInfo")
	public String CarParkInfo(Model model) {
		return "carparkInfo";
	}
	
	@GetMapping("carList")
	public ResponseEntity<List<DriverCar>> carList(){
		List<DriverCar> list = userService.getAllCar();
		return new ResponseEntity<List<DriverCar>>(list, HttpStatus.OK);
	}
	
	@GetMapping("carList/{driverID}")
	public ResponseEntity<List<DriverCar>> carList(@PathVariable("driverID") Integer driverID){
		List<DriverCar> list = userService.getAllCar(driverID);
		return new ResponseEntity<List<DriverCar>>(list, HttpStatus.OK);
	}
}
