package dev.example.restaurantManager.controller;


import dev.example.restaurantManager.model.Customer;
import dev.example.restaurantManager.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

@RequestMapping("/web")
@Controller
public class CustomerWebController {

    @Autowired
    CustomerRepository customerRepository;

    // CRUD for customer
    @GetMapping("/home")
    public String home(Model model) {

        List<Customer> customers = customerRepository.findAll();

        //to-do: how to inject the list of customers in the view
        //using a container and the template will be able to access it
        //and thymeleaf will be able to render it
        model.addAttribute("customersToView", customers);

        return "home";
    }


}
