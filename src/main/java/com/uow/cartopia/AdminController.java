package com.uow.cartopia;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.uow.Model.CarPark;
import com.uow.Model.Driver;
import com.uow.Model.Login;
import com.uow.Model.User;
import com.uow.Service.AdminService;
import com.uow.Service.CarParkService;
import com.uow.Service.UserService;

@Controller
public class AdminController {

	@Autowired
	AdminService adminService;
	@Autowired
	UserService userService;
	@Autowired
	CarParkService carParkService;


	@GetMapping("admin")
	public String admin(Model model, HttpSession session) {
//		if(session.getAttribute("userID") == null) {
//			return "redirect:/login";
//		}
//		if((int)session.getAttribute("userID") != 2) {
//			return "redirect:/login";
//		}
		return "AdminDash";
	}

	@GetMapping("admin/user")
	public String userOverview(Model model, HttpSession session) {
//		if(session.getAttribute("userID") == null) {
//		return "redirect:/login";
//	}
//	if((int)session.getAttribute("userID") != 2) {
//		return "redirect:/login";
//	}
		List<User> list = userService.getAllUser();
		model.addAttribute("userList", list);
		return "userCRUD";
	}

	@GetMapping("admin/user/create")
	public String userCreate(Model model, HttpSession session) {
//		if(session.getAttribute("userID") == null) {
//		return "redirect:/login";
//	}
//	if((int)session.getAttribute("userID") != 2) {
//		return "redirect:/login";
//	}	
		model.addAttribute("user", new User());
		return "userC";
	}

	@GetMapping("admin/user/read/{userID}")
	public String userRead(Model model, @PathVariable("userID") Integer id, HttpSession session) {
//		if(session.getAttribute("userID") == null) {
//		return "redirect:/login";
//	}
//	if((int)session.getAttribute("userID") != 2) {
//		return "redirect:/login";
//	}
		User user = userService.getUserInfo(id);
		model.addAttribute("user", user);
		if (user.getRoleID() == 1) {
			Driver driver = userService.getDriverInfo(id);
			model.addAttribute("driver", driver);
			return "userR";
		} else if (user.getRoleID() == 3) {
			List<CarPark> carParkList = userService.getCPOCarPark(id);
			model.addAttribute("carParkList", carParkList);
			return "cpoR";
		} else {
			return "redirect:/admin/user";
		}
	}
	
	@GetMapping("admin/user/update/{userID}")
	public String userUpdate(Model model, @PathVariable("userID") Integer id, HttpSession session) {
		User user = userService.getUserInfo(id);
		model.addAttribute("user", user);
		if (user.getRoleID() == 1) {
			Driver driver = userService.getDriverInfo(id);
			model.addAttribute("driver", driver);
			return "userU";
		} else if (user.getRoleID() == 3) {
			List<CarPark> carParkList = userService.getCPOCarPark(id);
			List<CarPark> carParkList2 = carParkService.getAllCarPark();
			model.addAttribute("carParkList", carParkList);
			model.addAttribute("allList", carParkList2);
			return "cpoU";
		} 
		return "userCRUD";
	}
	
	@GetMapping("admin/carpark")
	public String carparkOverview(Model model, HttpSession session) {
//		if(session.getAttribute("userID") == null) {
//		return "redirect:/login";
//	}
//	if((int)session.getAttribute("userID") != 2) {
//		return "redirect:/login";
//	}
		List<CarPark> list = carParkService.getAllCarPark();
		model.addAttribute("carParkList", list);
		return "carparkCRUD";
	}

	@GetMapping("admin/carpark/create")
	public String carparkCreate(Model model, HttpSession session) {
//		if(session.getAttribute("userID") == null) {
//		return "redirect:/login";
//	}
//	if((int)session.getAttribute("userID") != 2) {
//		return "redirect:/login";
//	}	
		List<User> list = carParkService.getCPOList();
		model.addAttribute("cpoList", list);
		model.addAttribute("cpo", "");
		model.addAttribute("carpark", new CarPark());
		return "carparkC";
	}

	@GetMapping("admin/carpark/read/{carparkID}")
	public String carparkRead(Model model,@PathVariable("carparkID") Integer id, HttpSession session) {
//		if(session.getAttribute("userID") == null) {
//		return "redirect:/login";
//	}
//	if((int)session.getAttribute("userID") != 2) {
//		return "redirect:/login";
//	}
		CarPark carPark = carParkService.getCarPark(id);
		model.addAttribute("carPark", carPark);
		return "carparkR";
	}
	
	@GetMapping("admin/carpark/update/{carParkID}")
	public String carparkUpdate(Model model, @PathVariable("carParkID") Integer id, HttpSession session) {
		CarPark carPark = carParkService.getCarPark(id);
		model.addAttribute("carPark", carPark);
		return "carParkU";
	}
	
	
	@PostMapping("/adminCreateUserProcess")
	public RedirectView createUserProcess(@ModelAttribute User user, RedirectAttributes model, HttpSession session) {
//		if(session.getAttribute("userID") == null) {
//		return "redirect:/login";
//	}
//	if((int)session.getAttribute("userID") != 2) {
//		return "redirect:/login";
//	}
		boolean valid = adminService.createUserProcess(user);
		if (!valid) {
			model.addFlashAttribute("message", "username has been used!");
			return new RedirectView("admin/user/create");
		}
		return new RedirectView("admin/user");
	}
	
	@PostMapping("/adminUpdateUserProcess/{id}")
	public String adminUpdateUserProcess(@ModelAttribute User user, @PathVariable("id") Integer id, Model model, HttpSession session) {
//		if(session.getAttribute("userID") == null) {
//		return "redirect:/login";
//	}
//	if((int)session.getAttribute("userID") != 2) {
//		return "redirect:/login";
//	}	
		user.setUserID(id);
		adminService.updateUserProcess(user);
		
		return ("redirect:/admin/user");
	}
	
	@PostMapping("createCarPark")
	public String adminCreateCarPark(@ModelAttribute CarPark carPark, @RequestParam Integer userID, Model model) {
		
		carParkService.addCarPark(carPark, userID);
		
		
		return ("redirect:/admin/carpark");
	}
}
