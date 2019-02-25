package com.uow.cartopia;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.uow.Model.Login;
import com.uow.Model.User;
import com.uow.Service.AdminService;
import com.uow.Service.UserService;

@Controller
public class AdminController {
	
	@Autowired
	AdminService adminService;
	@Autowired
	UserService userService;
	
	@GetMapping("admin")
	public String admin(Model model) {
		
		return "AdminDash";
	}
	
	@GetMapping("admin/user")
	public String userOverview(Model model) {
		List<User> list = userService.getAllUser();
		model.addAttribute("userList", list);
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
<<<<<<< HEAD
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
=======
	
	@PostMapping("/adminCreateUserProcess")
	public RedirectView createUserProcess(@ModelAttribute User user,RedirectAttributes model, HttpSession session) {
		boolean valid = adminService.createUserProcess(user);
		if(!valid) {
			model.addFlashAttribute("message", "username has been used!");
			return new RedirectView("admin/user/create");
		}
		return new RedirectView("admin/user");
	}
	
>>>>>>> ab96d8abb52f1c4a5551be9b2f3eda5c598f6a92
}
