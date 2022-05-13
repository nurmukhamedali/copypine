package kz.pine.controller;

import kz.pine.domain.Cart;
import kz.pine.domain.User;
import kz.pine.form.CartForm;
import kz.pine.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
@CrossOrigin(origins="http://localhost:8080")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/carts")
    List<CartForm> getAll() {
        return cartService.findAllDetails();
    }

    @GetMapping("/carts/{id}")
    public CartForm get(
            @PathVariable("id") Cart cart){
        return cartService.getDetails(cart);
    }

    @GetMapping("cart")
    public CartForm getCustomerCart(@RequestParam("customerId") User customer){
        return cartService.getDetails(customer.getCart());
    }
}
