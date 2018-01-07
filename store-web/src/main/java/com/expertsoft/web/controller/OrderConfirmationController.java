package com.expertsoft.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.expertsoft.core.service.OrderService;

@Controller
@RequestMapping("/order/{id}")
public class OrderConfirmationController {

    private OrderService orderService;

    @Autowired
    public OrderConfirmationController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String confirmOrder(@PathVariable long id, Model model) {
        model.addAttribute("order", orderService.getOrderByIdWithItemsAndProducts(id));
        return "orderConfirm";
    }
}
