package com.expertsoft.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.expertsoft.core.service.OrderService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private OrderService orderService;

    @Autowired
    public AdminController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String admin(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "admin";
    }

    @PostMapping(value = {"", "/{id}"}, params = "orderToRemoveId")
    public String deleteOrder(@RequestParam long orderToRemoveId) {
        orderService.deleteOrderById(orderToRemoveId);
        return "redirect:/admin";
    }

    @GetMapping("/{id}")
    public String orderDetail(@PathVariable long id, Model model) {
        model.addAttribute("order", orderService.getById(id));
        return "orderDetail";
    }
}
