package com.expertsoft.web.controller.order;

import com.expertsoft.core.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order/{id}")
public class OrderConfirmationController {

    private OrderService orderService;

    @Autowired
    public OrderConfirmationController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    @PreAuthorize("hasPermission(#id, 'com.expertsoft.core.model.entity.Order', T(org.springframework.security.acls.domain.BasePermission).READ)")
    public String confirmOrder(@PathVariable long id, Model model) {
        model.addAttribute("order", orderService.findById(id));
        return "order/orderConfirm";
    }
}
