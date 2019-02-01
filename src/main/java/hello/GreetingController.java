package hello;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cartopia.Database.Database;
import com.cartopia.Entity.*;
import com.cartopia.DAO.*;

@Controller
public class GreetingController {
	
	CarParkDAO carParkDAO;
	

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
    

    @GetMapping("/wilson")
    public String wilson(Model model) throws SQLException {
    	
    	CarPark carPark = new CarPark();
    	carPark = carParkDAO.getCarPark(1);
    	carPark.setName("Felix No CARPARK");
    	model.addAttribute("carPark", carPark);
        
        return "felix";
    }
    

}
