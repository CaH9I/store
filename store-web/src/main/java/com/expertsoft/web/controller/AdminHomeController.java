package com.expertsoft.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.expertsoft.core.service.OrderService;

@Controller
@RequestMapping("/admin")
public class AdminHomeController {

    private OrderService orderService;

    @Autowired
    public AdminHomeController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String admin(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "admin/home";
    }

    @PostMapping(params = "orderToRemoveId")
    public String deleteOrder(@RequestParam long orderToRemoveId) {
        orderService.deleteOrderById(orderToRemoveId);
        return "redirect:/admin";
    }

    @PostMapping(params = "orderToDeliverId")
    public String changeOrderToDelivered(@RequestParam long orderToDeliverId) {
        orderService.changeOrderToDelivered(orderToDeliverId);
        return "redirect:/admin";
    }
}
