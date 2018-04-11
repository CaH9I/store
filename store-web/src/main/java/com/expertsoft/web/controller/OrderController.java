package com.expertsoft.web.controller;

import com.expertsoft.core.model.entity.Order;
import com.expertsoft.core.service.OrderService;
import com.expertsoft.core.service.ShoppingCartService;
import com.expertsoft.web.form.OrderForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static com.expertsoft.web.util.SecurityUtils.username;

@Controller
@RequestMapping("/order")
@PreAuthorize("isAuthenticated()")
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
        Order order = orderService.createOrder(cartService.getShoppingCart());
        model.addAttribute("order", order);
        model.addAttribute("orderForm", new OrderForm());
        return "order";
    }

    @PostMapping
    public String placeOrder(@Valid OrderForm form, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("order", orderService.createOrder(cartService.getShoppingCart()));
            return "order";
        }
        Order order = orderService.createOrder(cartService.getShoppingCart(), username());
        orderService.populateOrder(order, form.getFirstName(), form.getLastName(), form.getAddress(),
                form.getPhoneNumber(), form.getAdditionalInfo());
        long orderId = orderService.save(order);
        cartService.clearCart();
        return "redirect:/order/" + orderId;
    }
}
