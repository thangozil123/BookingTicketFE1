package com.example.BookingTicketFE.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ChuyenBay implements Serializable {
    private Long id;
    private SanBay noidi;
    private SanBay noiden;
    private String giodi;
    private String gioden;
    private String hanghangkhong;
    private Long giave;
}
