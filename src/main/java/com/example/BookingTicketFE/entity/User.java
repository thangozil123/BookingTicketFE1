package com.example.BookingTicketFE.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class User implements Serializable {
    private Long id;
    private String hoten;
    private String username;
    private String password;
    private String email;
    private String sdt;
    private String diachi;
}
