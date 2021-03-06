package com.expertsoft.web.controller.admin;

import com.expertsoft.core.model.entity.OrderState;
import com.expertsoft.core.service.OrderService;
import com.expertsoft.web.facade.OrderFacade;
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
    private OrderFacade orderFacade;

    @Autowired
    public AdminHomeController(OrderService orderService, OrderFacade orderFacade) {
        this.orderService = orderService;
        this.orderFacade = orderFacade;
    }

    @GetMapping
    public String adminHome(Model model) {
        model.addAttribute("orders", orderService.findAll());
        return "admin/home";
    }

    @DeleteMapping("delete-order/{orderId}")
    @PreAuthorize("hasPermission(#orderId, 'com.expertsoft.core.model.entity.Order', T(org.springframework.security.acls.domain.BasePermission).WRITE)")
    public String deleteOrder(@PathVariable long orderId) {
        orderFacade.deleteOrderById(orderId);
        return "redirect:/admin";
    }

    @PutMapping("update-state/{orderId}/{state}")
    @PreAuthorize("hasPermission(#orderId, 'com.expertsoft.core.model.entity.Order', T(org.springframework.security.acls.domain.BasePermission).WRITE)")
    public String changeOrderState(@PathVariable long orderId, @PathVariable OrderState state) {
        orderFacade.updateOrderState(orderId, state);
        return "redirect:/admin";
    }
}
