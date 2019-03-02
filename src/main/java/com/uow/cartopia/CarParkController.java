package com.uow.cartopia;

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
import com.uow.Service.CarParkService;

@Controller
public class CarParkController {
	@Autowired
	CarParkService carParkService;
	
	@GetMapping("carparkInfo/{carParkID}")
	public String carpark(Model model, @PathVariable("carParkID") Integer id) {
		CarPark carPark = carParkService.getCarPark(id);
		//List<CarPark> list = carParkService.getAllCarPark();
		//model.addAttribute("carParkList", list);
		model.addAttribute("carPark", carPark);
		return "carparkInfo";
	}
	
	@GetMapping("carpark")
	public ResponseEntity<List<CarPark>> getAllCarPark() {
		List<CarPark> list = carParkService.getAllCarPark();
		return new ResponseEntity<List<CarPark>>(list, HttpStatus.OK);
	}
	
	@GetMapping("carParkInfo/{id}")
	public String getBookamrk(@PathVariable("id") Integer id, Model model, HttpSession session) {
		List<Bookmark> Bookmark = carParkService.getBookmark(id);
		model.addAttribute("Bookmark", Bookmark);
		return "Bookmark";
	}
	
	@PostMapping("addCarPark")
	public String addCarPark(@ModelAttribute CarPark carPark,Model model) {
		carParkService.addCarPark(carPark);
		return ("redirect:/admin/carpark");
	}
	@PostMapping("updateCarPark/{id}")
	public String editCarPark(@ModelAttribute CarPark carPark, @PathVariable("id")Integer id, Model model) {
		carPark.setCarParkID(id);
		System.out.println(carPark.getDescription());
		carParkService.updateCarPark(carPark);
		return ("redirect:/admin/carpark");	
	}
	
	@PostMapping("deleteCarPark/{carParkID}")
	public String deleteCarPark(@PathVariable("carParkID") Integer carParkID,Model model) {
		carParkService.deleteCarPark(carParkID);
		return ("redirect:/admin/carpark");
	}
	
	@PostMapping("getCarparkRealTimeSpace")
	public String getCarparkSpace(@ModelAttribute CarPark carPark,Model model) {
		carParkService.getCarparkRealTimeSpace(carPark);
		return ("redirect:/admin/carpark");
	}
}
