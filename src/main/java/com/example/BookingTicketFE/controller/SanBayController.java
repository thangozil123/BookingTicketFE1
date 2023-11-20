package com.example.BookingTicketFE.controller;

import com.example.BookingTicketFE.entity.SanBay;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("sanbay")
public class SanBayController {
    private RestTemplate restTemplate = new RestTemplate();
//  GD thêm sân bay
    @GetMapping("/them")
    public String getViewSanBay(Model model){
        model.addAttribute("sanbay",new SanBay());
        return "sanbay/themsanbay";
    }
//  GD thêm sân bay thành công
    @PostMapping("/them")
    public String create(SanBay sanBay, Model model){
        sanBay = restTemplate.postForObject("http://localhost:8080/sanbay", sanBay, sanBay.getClass());
        model.addAttribute("sanbay", sanBay);
        return "sanbay/thanhcong";
    }
//  GD sửa sân bay
    @GetMapping("/sua/{id}")
    public String getViewSanBayById(@PathVariable Long id, Model model){
        SanBay sanBay= restTemplate.getForObject("http://localhost:8080/sanbay/{id}",SanBay.class,id);
        model.addAttribute("sanbay",sanBay);
        return "sanbay/suasanbay";
    }
//  GD sửa sân bay thành công
    @PostMapping("/sua/{id}")
    public  String updateSanBay(SanBay sanBay,Model model){
        restTemplate.put("http://localhost:8080/sanbay/{id}",sanBay,sanBay.getId());
        model.addAttribute("sanbay",sanBay);
        return "sanbay/thanhcong";
    }
//  Xóa sân bay thành công
    @GetMapping("/xoa/{id}")
    public String delete(@PathVariable Long id){
        restTemplate.delete("http://localhost:8080/sanbay/{id}",id);
        return "index";
}
}
