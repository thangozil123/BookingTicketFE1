package com.example.BookingTicketFE.controller;

import com.example.BookingTicketFE.entity.HangGhe;
import com.example.BookingTicketFE.entity.SanBay;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("hangghe")
public class HangGheController {
    private RestTemplate restTemplate = new RestTemplate();
    //  GD thêm hạng ghế
    @GetMapping("/them")
    public String getViewHangGhe(Model model){
        model.addAttribute("hangghe",new HangGhe());
        return "hangghe/themhangghe";
    }
    //  GD thêm hạng ghế thành công
    @PostMapping("/them")
    public String create(HangGhe hangGhe, Model model){
        hangGhe = restTemplate.postForObject("http://localhost:8080/hangghe", hangGhe, hangGhe.getClass());
        model.addAttribute("hangghe", hangGhe);
        return "hangghe/thanhcong";
    }
    //  GD sửa hạng ghế
    @GetMapping("/sua/{id}")
    public String getViewHangGheById(@PathVariable Long id, Model model){
        HangGhe hangGhe= restTemplate.getForObject("http://localhost:8080/hangghe/{id}",HangGhe.class,id);
        model.addAttribute("hangghe",hangGhe);
        return "hangghe/suahangghe";
    }
    //  GD sửa hạng ghế thành công
    @PostMapping("/sua/{id}")
    public  String updateHangGhe(HangGhe hangGhe,Model model){
        restTemplate.put("http://localhost:8080/hangghe/{id}",hangGhe,hangGhe.getId());
        model.addAttribute("hangghe",hangGhe);
        return "hangghe/thanhcong";
    }
    //  Xóa hạng ghế thành công
    @GetMapping("/xoa/{id}")
    public String delete(@PathVariable Long id){
        restTemplate.delete("http://localhost:8080/hangghe/{id}",id);
        return "index";
    }
}
