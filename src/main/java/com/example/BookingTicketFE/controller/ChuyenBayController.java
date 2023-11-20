package com.example.BookingTicketFE.controller;

import com.example.BookingTicketFE.entity.ChuyenBay;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

@Controller
@Data
@RequestMapping("/chuyenbay")
public class ChuyenBayController {
    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/them")
    public String getThemChuyenBay(Model model){
        List<SanBay> sanBays = Arrays.asList(restTemplate.getForObject("http://localhost:8080/chuyenbay/all", SanBay[].class));

        model.addAttribute("sanbays",sanBays);

        return "/chuyenbay/themchuyenbay";
    }

    @PostMapping("/them")
    public String themChuyenbay(@RequestParam(name = "sanbay") Long idNoidi, @RequestParam(name = "sanbay") Long idNoiden,
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
        return "chuyenbay/thanhcong";
    }
}
