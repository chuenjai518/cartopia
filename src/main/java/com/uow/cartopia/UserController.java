package com.uow.cartopia;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
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
	public RedirectView loginProcess(@ModelAttribute Login login,RedirectAttributes model, HttpSession session) {
//		Login login = new Login();
//		login.setUsername("user");
//		login.setPassword("user");
		int userID = userService.loginProcess(login);
		if(userID == 0) {
			model.addFlashAttribute("message", "Incorrect username or password!");
			return new RedirectView("login");
		}
		session.setAttribute("userID", userID);
		return new RedirectView("index");
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
	
	@PostMapping("/registerProcess")
	public RedirectView registerProcess(@ModelAttribute User user,RedirectAttributes model, HttpSession session) {
		Login login = new Login();
		login.setUsername("user");
		login.setPassword("user");
		boolean valid = userService.registerProcess(user);
		if(!valid) {
			model.addFlashAttribute("message", "username has been used!");
			return new RedirectView("login");
		}
		return new RedirectView("login");
	}
	
	@GetMapping("user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") Integer id) {
		User user = userService.getUserInfo(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@GetMapping("user")
	public ResponseEntity<List<User>> getAllUser() {
		List<User> list = userService.getAllUser();
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
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
