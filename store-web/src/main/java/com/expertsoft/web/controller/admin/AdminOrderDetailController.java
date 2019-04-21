package com.expertsoft.web.controller.admin;

import com.expertsoft.core.model.entity.OrderState;
import com.expertsoft.core.service.OrderService;
import com.expertsoft.web.facade.OrderFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/order-detail/{id}")
public class AdminOrderDetailController {

    private OrderService orderService;
    private OrderFacade orderFacade;

    @Autowired
    public AdminOrderDetailController(OrderService orderService, OrderFacade orderFacade) {
        this.orderService = orderService;
        this.orderFacade = orderFacade;
    }

    @GetMapping
    @PreAuthorize("hasPermission(#id, 'com.expertsoft.core.model.entity.Order', T(org.springframework.security.acls.domain.BasePermission).READ)")
    public String orderDetail(@PathVariable long id, Model model) {
        model.addAttribute("order", orderService.findById(id));
        return "admin/orderDetail";
    }

    @PutMapping("{state}")
    @PreAuthorize("hasPermission(#id, 'com.expertsoft.core.model.entity.Order', T(org.springframework.security.acls.domain.BasePermission).WRITE)")
    public String changeOrderState(@PathVariable long id, @PathVariable OrderState state) {
        orderFacade.updateOrderState(id, state);
        return "redirect:/admin/order-detail/{id}";
    }
}
