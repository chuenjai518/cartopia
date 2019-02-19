package com.uow.cartopia;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.uow.Model.CarPark;
import com.uow.Model.Driver;
import com.uow.Model.Login;
import com.uow.Model.User;
import com.uow.Service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	//Need change to Post
	@PostMapping("/loginProcess")
	public String loginProcess(@ModelAttribute Login login, Model model, HttpSession session) {
//		Login login = new Login();
//		login.setUsername("user");
//		login.setPassword("user");
		int userID = userService.loginProcess(login);
		if(userID == 0) {
			model.addAttribute("message", "Incorrect username or password!");
			return "login";
		}
		session.setAttribute("userID", userID);
		return "redirect:/index";
	}
	
	//Need change to Post
	@PostMapping("/logout")
	public String logout(Model model, HttpSession session) {
		
		if(session.getAttribute("userID") != null) {
			session.removeAttribute("userID");
		}
		
		if(session.getAttribute("driver") != null) {
			session.removeAttribute("driver");
		}
		
		return "redirect:/index";
	}
	
	@GetMapping("user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") Integer id) {
		User user = userService.getUserInfo(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@GetMapping("driver/{id}")
	public ResponseEntity<Driver> getDriverById(@PathVariable("id") Integer id, HttpSession session) {
		Driver driver = userService.getDriverInfo(id);
		session.setAttribute("driver", driver);
		return new ResponseEntity<Driver>(driver, HttpStatus.OK);
	}
	
	//Need change to Post
	@GetMapping("/addCredit/{amount}")
	public String addCredit(@PathVariable("amount") Integer amount, HttpSession session) {
		Driver driver = (Driver)session.getAttribute("driver");
		boolean success = userService.addCredit(driver.getDriverID(), amount);
		if(success) {
			System.out.println("Credit Updated!");
		}else {
			System.out.println("Credit Update failed");
		}
		return "redirect:/driver/"+ driver.getDriverID();
	}
	
	
	
}
