package com.uow.cartopia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.uow.Model.CarPark;
import com.uow.Service.CarParkService;

@Controller
public class CarParkController {
	@Autowired
	CarParkService carParkService;
	
	@GetMapping("carpark/{carParkID}")
	public String carpark(Model model, @PathVariable("carParkID") Integer id) {
		CarPark carPark = carParkService.getCarPark(id);
		List<CarPark> list = carParkService.getAllCarPark();
		model.addAttribute("carParkList", list);
//		model.addAttribute("carPark", carPark);
		return "index";
	}
	


	@GetMapping("carpark")
	public ResponseEntity<List<CarPark>> getAllCarPark() {
		List<CarPark> list = carParkService.getAllCarPark();
		return new ResponseEntity<List<CarPark>>(list, HttpStatus.OK);
	}
	
}
