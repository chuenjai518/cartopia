package com.uow.cartopia;

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
	public String loginProcess(Model model) {
		Login login = new Login();
		login.setUsername("admin");
		login.setPassword("admin");
		int userID = userService.loginProcess(login);
		if(userID == 0) {
			return "login";
		}
		return "redirect:/index";
	}
	
	
}
