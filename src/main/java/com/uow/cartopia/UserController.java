package com.uow.cartopia;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.uow.Model.Bookmark;
import com.uow.Model.CarPark;
import com.uow.Model.Driver;
import com.uow.Model.DriverBookmark;
import com.uow.Model.DriverCar;
import com.uow.Model.Login;
import com.uow.Model.User;
import com.uow.Service.UserService;
import com.uow.Model.Transaction;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
//	@PostMapping("/userInfo/{userID}")
//	public String user(Model model, @PathVariable("userID") Integer id) {
//		User user = userService.getUserInfo(id);
//		model.addAttribute("user", user);
////		List<Bookmark> Bookmark = carParkService.getBookmark(id);
////		model.addAttribute("Bookmark", Bookmark);
//		return "userInfo";
//	}
//	

	//Need change to Post
	@PostMapping("/loginProcess")
	public RedirectView loginProcess(@ModelAttribute Login login,RedirectAttributes model, HttpSession session) {
//		Login login = new Login();
//		login.setUsername("user");
//		login.setPassword("user");
		User user = userService.loginProcess(login);
		if(user == null) {
			model.addFlashAttribute("message", "Incorrect username or password!");
			return new RedirectView("login");
		}
		session.setAttribute("userID", user.getUserID());
		session.setAttribute("username", login.getUsername());
		session.setAttribute("roleID", user.getRoleID());
		if(user.getRoleID()==1) {
			Driver driver = userService.getDriverInfo(user.getUserID());
			session.setAttribute("driver", driver);
			return new RedirectView("driverPage");
		}else if(user.getRoleID() == 2) {
			return new RedirectView("admin");
		}else{
			return new RedirectView("cpo");
		}
			
	}
	
	@GetMapping("/logout")
	public String logout(Model model, HttpSession session) {
		
		if(session.getAttribute("userID") != null) {
			session.removeAttribute("userID");
		}
		
		if(session.getAttribute("driver") != null) {
			session.removeAttribute("driver");
		}
		
		return "redirect:/home";
	}
	
	@PostMapping("/registerProcess")
	public RedirectView registerProcess(@ModelAttribute User user,RedirectAttributes model, HttpSession session) {
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
	@PostMapping("/addCredit")
	public String addCredit(@RequestParam Integer amount, HttpSession session) {
		Driver driver = (Driver)session.getAttribute("driver");
		boolean success = userService.addCredit(driver.getDriverID(), amount);
		if(success) {
			System.out.println("Credit Updated!");
		}else {
			System.out.println("Credit Update failed");
		}
		return "redirect:/driverPage";
	}
	

	
	//Need change to Post
	@GetMapping("/bookmark/{carParkID}")
	public String bookmarkCarPark(Model model, HttpSession session,@PathVariable("carParkID") Integer carParkID,HttpServletRequest request) {
		if(session.getAttribute("userID") == null) {
			return "redirect:/login";
		}
		int userID = (int)session.getAttribute("userID");
		Bookmark bookmark = new Bookmark();
		bookmark.setUserID(userID);
		bookmark.setCarParkID(carParkID);
		userService.bookmark(bookmark);	
		
		 return "redirect:/carparkInfo/"+carParkID;
	}
	

	
	
	@GetMapping("driverPage")
	public String driverPage(Model model, HttpSession session) {
		if(session.getAttribute("userID") == null) {
			return "redirect:/login";
			
		}
		
		int userID = (int) session.getAttribute("userID");
		Driver driver = userService.getDriverInfo(userID);
		User user = userService.getUserInfo(userID);
		List<DriverCar> list = userService.getAllCar(driver.getDriverID());
		List<DriverBookmark> bookmark = userService.getBookmark(userID);
		model.addAttribute("bookmarkList", bookmark);
		model.addAttribute("driver", driver);
		model.addAttribute("user", user);
		model.addAttribute("carList", list);
		model.addAttribute("driverCar", new DriverCar());
		return "driverHome";
	}
	
	

	
	
	@PostMapping("driverProfile/addCar")
	public String addCar(@ModelAttribute DriverCar car, Model model, HttpSession session) {
//		if(session.getAttribute("userID") == null) {
//		return "redirect:/login";
//	}
		Driver driver = (Driver)session.getAttribute("driver");
		
//		DriverCar car = new DriverCar();
//		car.setCarTypeID(1);
//		car.setLicensePlateNum("UX1234");
		
		car.setDriverID(driver.getDriverID());
		userService.addCar(car);
		return "redirect:/driverPage";
	}
	
	@PostMapping ("driverProfile/updateCar")
	public String updateCar(@ModelAttribute DriverCar car,Model model) {
		userService.updateCar(car);
		return "redirect:/driverPage";
	}
	
	@PostMapping("driverProfile/updateUserProcess")
	public String updateUserProcess(@ModelAttribute User user,Model model, HttpSession session) {
		user.setUserID((int)session.getAttribute("userID"));
		userService.updateUserProcess(user);
		return "redirect:/driverPage";
	}

	@GetMapping ("driverProfile/deleteCar/{driverCarID}")
	public String deleteCar(@PathVariable("driverCarID") Integer driverCarID, Model model) {
		userService.deleteCar(driverCarID);
		return "redirect:/driverPage#profile";
	}
	
	
	
	@PostMapping("resetPassword")
	public String resetPassword(@RequestParam String password, Model model, HttpSession session) {
		int userID = (int)session.getAttribute("userID");
		userService.resetPassword(password, userID);
		return "redirect:/driverPage";
	}
	
	@GetMapping("carparkOwner")
	public String carparkOwner(Model model, HttpSession session) {
		// Get Login session
		int userID = 3;
		//
		User user = userService.getUserInfo(userID);
		model.addAttribute("user", user);
		return "cpoR";
	}
	@GetMapping("carparkOwner/update/{id}")
	public String carparkOwnerUpdate(@PathVariable("id") Integer id, Model model, HttpSession session) {
		// Get Login session
		int userID = 3;
		//
		User user = userService.getUserInfo(userID);
		model.addAttribute("user", user);
		return "cpoR";
	}
	
	
}
