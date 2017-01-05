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
@RequestMapping("/admin/{id}")
public class AdminOrderDetailController {

    private OrderService orderService;

    @Autowired
    public AdminOrderDetailController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String orderDetail(@PathVariable long id, Model model) {
        model.addAttribute("order", orderService.getById(id));
        return "admin/orderDetail";
    }

    @PostMapping(params = "orderToDeliverId")
    public String changeOrderToDelivered(@RequestParam long orderToDeliverId) {
        orderService.changeOrderToDelivered(orderToDeliverId);
        return "redirect:/admin/{id}";
    }

    @PostMapping(params = "orderToRemoveId")
    public String deleteOrder(@RequestParam long orderToRemoveId) {
        orderService.deleteOrderById(orderToRemoveId);
        return "redirect:/admin";
    }
}
