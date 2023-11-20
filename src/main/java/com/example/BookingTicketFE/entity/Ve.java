package com.example.BookingTicketFE.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class Ve implements Serializable {
    private Long id;
    private SanBay noidi;
    private SanBay noiden;
    private int sohanhkhach;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngaydi;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngaykhuhoi;
    private HangGhe hangghe;
}
