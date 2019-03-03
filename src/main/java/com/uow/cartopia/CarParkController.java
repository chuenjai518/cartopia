package com.uow.cartopia;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.uow.Model.Bookmark;
import com.uow.Model.CarPark;
import com.uow.Model.User;
import com.uow.Service.CarParkService;
import com.uow.Service.UserService;
import com.uow.Model.Comment;
import com.uow.Model.Driver;
import com.uow.Model.DriverCar;

@Controller
public class CarParkController {
	@Autowired
	CarParkService carParkService;

	@Autowired
	UserService userService;

	@GetMapping("carparkInfo/{carParkID}")
	public String carpark(Model model, @PathVariable("carParkID") Integer carParkID, HttpSession session) {
		int userID = 0;
		List<Bookmark> bookmark = new ArrayList<Bookmark>();
		List<DriverCar> driverCarList = new ArrayList<DriverCar>();
		User user = new User();
		if (session.getAttribute("userID") != null) {
			userID = (int) session.getAttribute("userID");
			bookmark = carParkService.getBookmark(userID);
			user = userService.getUserInfo(userID);
			Driver driver = userService.getDriverInfo(userID);
			driverCarList = userService.getAllCar(driver.getDriverID());
			model.addAttribute("driverCarList", driverCarList);
		}
		//int realTimeSpace = carParkService.getCarparkRealTimeSpace(carParkID);
		//model.addAttribute("realTimeSpace", realTimeSpace);
		CarPark carPark = carParkService.getCarPark(carParkID);
		model.addAttribute("user", user);
		model.addAttribute("carPark", carPark);
		model.addAttribute("bookmark", bookmark);
		List<Comment> comment = carParkService.getComment(carParkID);
		model.addAttribute("comment", comment);
		return "carparkInfo";
	}
	
	@PostMapping("bookCarPark")
	public String bookCarPark(@RequestParam Integer driverCarID, @RequestParam Integer carParkID, @RequestParam Integer carTypeID, @RequestParam Integer driverID, HttpSession session) {
		if(session.getAttribute("userID") == null) {
			return "redirect:/login";
		}
		userService.bookCarPark(driverCarID, carParkID, carTypeID, driverID);
		return ("redirect:/carparkinfo/{carParkID}");
	}

	@GetMapping("carpark")
	public ResponseEntity<List<CarPark>> getAllCarPark() {
		List<CarPark> list = carParkService.getAllCarPark();
		return new ResponseEntity<List<CarPark>>(list, HttpStatus.OK);
	}
	
	@GetMapping("cpo")
	public ResponseEntity<List<User>> getCPOList() {
		List<User> list = carParkService.getCPOList();
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}

	@PostMapping("addCarPark")
	public String addCarPark(@ModelAttribute CarPark carPark, @RequestParam Integer userID, Model model) {
		carParkService.addCarPark(carPark, userID);
		return ("redirect:/admin/carpark");
	}

	@PostMapping("updateCarPark/{id}")
	public String editCarPark(@ModelAttribute CarPark carPark, @PathVariable("id") Integer id, Model model) {
		carPark.setCarParkID(id);
		System.out.println(carPark.getDescription());
		carParkService.updateCarPark(carPark);
		return ("redirect:/admin/carpark");
	}

	@PostMapping("deleteCarPark/{carParkID}")
	public String deleteCarPark(@PathVariable("carParkID") Integer carParkID, Model model) {
		carParkService.deleteCarPark(carParkID);
		return ("redirect:/admin/carpark");
	}
	
	@PostMapping("addComment")
	public String addComment(@ModelAttribute Comment comment, @PathVariable("carParkID") Integer carParkID, Model model) {
		carParkService.addComment(comment);
		return ("redirect:/carparkinfo/" +carParkID);
	}
	

}
