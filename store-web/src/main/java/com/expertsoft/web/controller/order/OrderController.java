package com.expertsoft.web.controller.order;

import com.expertsoft.web.facade.OrderFacade;
import com.expertsoft.web.dto.form.OrderForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/order")
@PreAuthorize("isAuthenticated()")
public class OrderController {

    private final OrderFacade orderFacade;

    @Autowired
    public OrderController(OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
    }

    @GetMapping
    public String order(Model model) {
        model.addAttribute("order", orderFacade.createOrderFromCart());
        model.addAttribute("orderForm", new OrderForm());
        return "order";
    }

    @PostMapping
    public String placeOrder(@Valid OrderForm form, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("order", orderFacade.createOrderFromCart());
            return "order";
        }
        return "redirect:/order/" + orderFacade.placeOrder(form);
    }
}
