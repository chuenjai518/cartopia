package com.uow.cartopia;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.uow.Model.CarPark;
import com.uow.Model.Login;
import com.uow.Model.User;
import com.uow.Service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/loginProcess")
	public String loginProcess(Model model, HttpSession session) {
		Login login = new Login();
		login.setUsername("user");
		login.setPassword("user");
		int userID = userService.loginProcess(login);
		if(userID == 0) {
			model.addAttribute("message", "Incorrect username or password!");
			return "login";
		}
		session.setAttribute("userID", userID);
		return "redirect:/index";
	}
	
	@GetMapping("/logout")
	public String logout(Model model, HttpSession session) {
		
		if(session.getAttribute("userID") != null) {
			session.removeAttribute("userID");
		}
		
		return "redirect:/index";
	}
	
	
}
