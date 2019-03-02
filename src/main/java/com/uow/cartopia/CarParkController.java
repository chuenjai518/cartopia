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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.uow.Model.Bookmark;
import com.uow.Model.CarPark;
import com.uow.Model.User;
import com.uow.Service.CarParkService;
import com.uow.Service.UserService;
import com.uow.Model.Comment;

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
		User user = new User();
		if (session.getAttribute("userID") != null) {
			userID = (int) session.getAttribute("userID");
			bookmark = carParkService.getBookmark(userID);
			user = userService.getUserInfo(userID);
		}
		CarPark carPark = carParkService.getCarPark(carParkID);

		model.addAttribute("user", user);
		model.addAttribute("carPark", carPark);
		List<Bookmark> Bookmark = carParkService.getBookmark(userID);
		model.addAttribute("Bookmark", Bookmark);
		List<Comment> Comment = carParkService.getComment(carParkID);
		model.addAttribute("Comment", Comment);
		return "carparkInfo";
	}

	@GetMapping("carpark")
	public ResponseEntity<List<CarPark>> getAllCarPark() {
		List<CarPark> list = carParkService.getAllCarPark();
		return new ResponseEntity<List<CarPark>>(list, HttpStatus.OK);
	}

	@PostMapping("addCarPark")
	public String addCarPark(@ModelAttribute CarPark carPark, Model model) {
		carParkService.addCarPark(carPark);
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

	@PostMapping("getCarparkRealTimeSpace")
	public String getCarparkSpace(@ModelAttribute CarPark carPark, Model model) {
		carParkService.getCarparkRealTimeSpace(carPark);
		return ("redirect:/admin/carpark");
	}

	@GetMapping("carParkInfo/{id}")
	public String getBookamrk(@PathVariable("id") Integer id, Model model, HttpSession session) {
		return "Bookmark";
	}
}
