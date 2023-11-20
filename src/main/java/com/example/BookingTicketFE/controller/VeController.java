package com.example.BookingTicketFE.controller;

import com.example.BookingTicketFE.entity.HangGhe;
import com.example.BookingTicketFE.entity.SanBay;
import com.example.BookingTicketFE.entity.Ve;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.SimpleFormatter;

@Controller
@Data
@RequestMapping("/datve")
public class VeController {
    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/them")
    public String getThemVe(Model model){
        List<SanBay> sanBays = Arrays.asList(restTemplate.getForObject("http://localhost:8080/sanbay/all", SanBay[].class));
        List<HangGhe> hangGhes = Arrays.asList(restTemplate.getForObject("http://localhost:8080/hangghe/all", HangGhe[].class));
        model.addAttribute("sanbays",sanBays);
        model.addAttribute("hangghes",hangGhes);
        return "/datve/themve";
    }

    @PostMapping("/them")
    public String themVe(@RequestParam(name = "sanbay") Long idNoidi, @RequestParam(name = "sanbay") Long idNoiden,
                         @RequestParam(name = "sohanhkhach") int SHK, @RequestParam(name = "ngaydi") String ngaydi,
                         @RequestParam(name = "ngaykhuhoi") String ngaykhuhoi, @RequestParam(name = "hangghe") Long idHG, Model model){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Ve ve = new Ve();

        SanBay sanBay = new SanBay();
        sanBay.setId(idNoidi);

        SanBay sanBay1 = new SanBay();
        sanBay1.setId(idNoiden);

        ve.setSohanhkhach(SHK);

        try {
            ve.setNgaydi(simpleDateFormat.parse(ngaydi));
            ve.setNgaykhuhoi(simpleDateFormat.parse(ngaykhuhoi));
        }
        catch (ParseException e){
            e.printStackTrace();
        }

        HangGhe hangGhe = new HangGhe();
        hangGhe.setId(idHG);

        ve.setNoidi(sanBay);
        ve.setNoiden(sanBay1);
        ve.setHangghe(hangGhe);

        Ve ve1 =restTemplate.postForObject("http://localhost:8080/datve", ve, Ve.class);
        model.addAttribute("ve", ve1);
        return "datve/thanhcong";
    }
}
