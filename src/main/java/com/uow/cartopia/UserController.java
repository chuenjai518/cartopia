package com.uow.cartopia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.uow.Model.CarPark;
import com.uow.Model.User;
import com.uow.Service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/addUser")
	public String index(Model model) {
		User user = new User();
//		user.setRoleID(1);
//		user.setUsername("cpo");
//		user.setPassword("cpo");
//		user.setFirstName("Owner");
//		user.setLastName("CarPark");
//		user.setEmail("carParkOwner@chuen.com");
//		userService.addUser(user);
		return "index";
	}
}
