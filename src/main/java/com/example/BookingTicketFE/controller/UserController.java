package com.example.BookingTicketFE.controller;

import com.example.BookingTicketFE.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("user")
public class UserController {
    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/them")
    public String getViewUser(Model model){
        model.addAttribute("user",new User());
        return "user/themuser";
    }
    //  GD thêm sân bay thành công
    @PostMapping("/them")
    public String create(User user, Model model){
        user = restTemplate.postForObject("http://localhost:8080/user/register", user, user.getClass());
        model.addAttribute("user", user);
        return "index";
    }
}
