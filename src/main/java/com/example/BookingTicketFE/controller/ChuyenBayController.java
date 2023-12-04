package com.example.BookingTicketFE.controller;

import com.example.BookingTicketFE.entity.ChuyenBay;
import com.example.BookingTicketFE.entity.HangGhe;
import com.example.BookingTicketFE.entity.SanBay;
import com.example.BookingTicketFE.entity.Ve;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        List<SanBay> sanBays = Arrays.asList(restTemplate.getForObject("http://localhost:8080/sanbay/all", SanBay[].class));

        model.addAttribute("sanbays",sanBays);
        return "/chuyenbay/themchuyenbay";
    }

    @PostMapping("/them")
    public String themChuyenbay(
            @RequestParam(required=false,name = "noidi") Long idNoidi,
            @RequestParam(required=false,name = "noiden") Long idNoiden,
            @RequestParam(name = "giodi") String Giodi, @RequestParam(name = "gioden") String Gioden,
            @RequestParam(name = "hanghangkhong") String HHK, @RequestParam(name = "giave") Long GV,
            @RequestParam(name="ngaybay") String ngayBay,@RequestParam(name = "maCB") String maCB, Model model) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ChuyenBay chuyenBay = new ChuyenBay();

        chuyenBay.setGioDi(Giodi);
        chuyenBay.setGioDen(Gioden);
        chuyenBay.setHangHangKhong(HHK);
        chuyenBay.setGiaVe(GV);
        chuyenBay.setMaCB(maCB);
        try {
            chuyenBay.setNgayBay(simpleDateFormat.parse(ngayBay));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        SanBay sanBay = new SanBay();
        sanBay.setId(idNoidi);

        SanBay sanBay1 = new SanBay();
        sanBay1.setId(idNoiden);

        chuyenBay.setNoiDi(sanBay);
        chuyenBay.setNoiDen(sanBay1);

        ChuyenBay chuyenBay1 =restTemplate.postForObject("http://localhost:8080/chuyenbay", chuyenBay, ChuyenBay.class);
        model.addAttribute("chuyenbay", chuyenBay1);
        return "chuyenbay/thanhcong";
    }

    @GetMapping("/sua/{id}")
    public String getViewChuyenBayById( @PathVariable Long id,Model model){
        ChuyenBay chuyenBay = restTemplate.getForObject("http://localhost:8080/chuyenbay/{id}",ChuyenBay.class,id);
        List<SanBay> sanBays = Arrays.asList(restTemplate.getForObject("http://localhost:8080/sanbay/all",SanBay[].class));

        model.addAttribute("sanbays",sanBays);
        model.addAttribute("chuyenbay",chuyenBay);
        return "chuyenbay/suachuyenbay";
    }

//  sửa chuyến bay
    @PostMapping("/sua/{id}")
    public String update(@RequestParam(required=false,name = "noidi") Long idNoidi, @PathVariable Long id,
                         @RequestParam(required=false,name = "noiden") Long idNoiden,
                         @RequestParam(name = "giodi") String Giodi, @RequestParam(name = "gioden") String Gioden,
                         @RequestParam(name = "hanghangkhong") String HHK, @RequestParam(name = "giave") Long GV,
                         @RequestParam(name="ngaybay") String ngayBay,@RequestParam(name = "maCB") String maCB, Model model) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ChuyenBay chuyenBay = new ChuyenBay();

        chuyenBay.setGioDi(Giodi);
        chuyenBay.setGioDen(Gioden);
        chuyenBay.setHangHangKhong(HHK);
        chuyenBay.setGiaVe(GV);
        chuyenBay.setMaCB(maCB);
        try {
            chuyenBay.setNgayBay(simpleDateFormat.parse(ngayBay));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SanBay sanBay = restTemplate.getForObject("http://localhost:8080/sanbay/{id}", SanBay.class, idNoidi);
        SanBay sanBay1 = restTemplate.getForObject("http://localhost:8080/sanbay/{id}", SanBay.class, idNoiden);

        chuyenBay.setNoiDi(sanBay);
        chuyenBay.setNoiDen(sanBay1);
        chuyenBay.setId(id);
        restTemplate.put("http://localhost:8080/chuyenbay/{id}",chuyenBay,id);
        model.addAttribute("chuyenbay", chuyenBay);
        return "chuyenbay/thanhcong";
    }
//  XÓa chuyến bay
    @GetMapping("/xoa/{id}")
    public String delete(@PathVariable Long id){
        restTemplate.delete("http://localhost:8080/chuyenbay/{id}",id);
        return "index";
    }
}
