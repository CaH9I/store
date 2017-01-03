package com.expertsoft.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.expertsoft.core.model.entity.Order;
import com.expertsoft.core.service.OrderService;
import com.expertsoft.core.service.ShoppingCartService;
import com.expertsoft.core.service.component.OrderForm;

@Controller
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;
    private ShoppingCartService cartService;

    @Autowired
    public OrderController(OrderService orderService, ShoppingCartService cartService) {
        this.orderService = orderService;
        this.cartService = cartService;
    }

    @GetMapping
    public String order(Model model) {
        Order order = orderService.createOrder(cartService.getShoppingCart(), true);
        model.addAttribute("order", order);
        model.addAttribute("orderForm", new OrderForm());
        return "order";
    }

    @PostMapping
    public String placeOrder(@ModelAttribute @Valid OrderForm orderForm, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("order", orderService.createOrder(cartService.getShoppingCart()));
            model.addAttribute("orderForm", orderForm);
            return "order";
        }
        Order order = orderService.createOrder(cartService.getShoppingCart());
        orderService.populateOrder(order, orderForm);
        long orderId = orderService.saveOrder(order);
        cartService.clearCart();
        return "redirect:/order/" + orderId;
    }
}
