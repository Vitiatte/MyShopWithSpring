package com.myproject.service;

import com.myproject.entity.Order;

public interface MailService {

    void sendCheckCode(Order order);
}
