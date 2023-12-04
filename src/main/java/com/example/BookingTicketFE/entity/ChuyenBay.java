package com.example.BookingTicketFE.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class ChuyenBay implements Serializable {
    private Long id;
    private SanBay noiDi;
    private SanBay noiDen;
    private String gioDi;
    private String gioDen;
    private String hangHangKhong;
    private Long giaVe;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayBay;
    private String maCB;
}
