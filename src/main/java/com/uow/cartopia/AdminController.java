package com.uow.cartopia;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
	
	@GetMapping("admin")
	public String admin(Model model) {
		
		return "AdminDash";
	}
	
	@GetMapping("admin/user")
	public String userOverview(Model model) {
		
		return "userCRUD";
	}
	
	@GetMapping("admin/user/create")
	public String userCreate(Model model) {
		
		return "userC";
	}
	
	@GetMapping("admin/user/read")
	public String userRead(Model model) {
		
		return "userR";
	}
	@GetMapping("admin/carpark")
	public String carparkOverview(Model model) {
		
		return "carparkCRUD";
	}
	
	@GetMapping("admin/carpark/create")
	public String carparkCreate(Model model) {
		
		return "carparkC";
	}
	
	@GetMapping("admin/carpark/read")
	public String carparkRead(Model model) {
		
		return "carparkR";
	}
}
