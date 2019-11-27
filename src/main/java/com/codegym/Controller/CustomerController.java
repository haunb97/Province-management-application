package com.codegym.Controller;

import com.codegym.model.Customer;
import com.codegym.model.Province;
import com.codegym.service.CustomerService;
import com.codegym.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProvinceService provinceService;
    @GetMapping("/customers")
    public String customerList(Model model) {
        List<Customer> customers = (List<Customer>) customerService.findAll();
        model.addAttribute("customers", customers);
        return "customer/list";
    }
    @GetMapping("/create-customer")
    public String createCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/create";
    }
    @PostMapping("/create-customer")
    public String saveNewCustomer(Customer customer, Model model) {
        customerService.save(customer);
        model.addAttribute("message", "Added new customer");
        return "customer/create";
    }
    @GetMapping("/edit-customer/{id}")
    public String editCustomer(@PathVariable Long id, Model model) {
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "customer/edit";
    }
    @PostMapping("/edit-customer")
    public String saveEditCustomer(Model model, Customer customer) {
        customerService.save(customer);
        model.addAttribute("message","Saved");
        return "customer/edit";
    }
    @GetMapping("/delete-customer/{id}")
    public String deleteCustomer(@PathVariable Long id, Model model) {
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "customer/delete";
    }
    @PostMapping("/delete-customer")
    public String saveDeleteCustomer(Customer customer) {
        customerService.remove(customer.getId());
        return "customer/delete";
    }
    @ModelAttribute("provinces")
    public List<Province> provinces(){
        return (List<Province>) provinceService.findAll();
    }
    @GetMapping("/")
    public String home(){
        return "home";
    }
}