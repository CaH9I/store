package com.expertsoft.web.controller;

import com.expertsoft.core.model.entity.OrderState;
import com.expertsoft.core.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminHomeController {

    private OrderService orderService;

    @Autowired
    public AdminHomeController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String adminHome(Model model) {
        model.addAttribute("orders", orderService.findAll());
        return "admin/home";
    }

    @DeleteMapping("delete-order/{orderId}")
    @PreAuthorize("hasPermission(#orderId, 'com.expertsoft.core.model.entity.Order', T(org.springframework.security.acls.domain.BasePermission).WRITE)")
    public String deleteOrder(@PathVariable long orderId) {
        orderService.deleteById(orderId);
        return "redirect:/admin";
    }

    @PutMapping("update-state/{orderId}/{state}")
    @PreAuthorize("hasPermission(#orderId, 'com.expertsoft.core.model.entity.Order', T(org.springframework.security.acls.domain.BasePermission).WRITE)")
    public String changeOrderState(@PathVariable long orderId, @PathVariable OrderState state) {
        orderService.changeOrderState(orderId, state);
        return "redirect:/admin";
    }
}
