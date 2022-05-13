package kz.pine.controller;

import kz.pine.domain.Cart;
import kz.pine.domain.CartItem;
import kz.pine.domain.Category;
import kz.pine.form.ItemDetails;
import kz.pine.form.ItemForm;
import kz.pine.services.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
@CrossOrigin(origins="*")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;

    @GetMapping("items")
    public List<ItemDetails> getItems(@RequestParam(required = false, value = "cartId", defaultValue = "0") Cart cart) {
        return cartItemService.findAllDetails(cart);
    }

    @GetMapping("items/{id}")
    public ItemDetails get(
            @PathVariable("id") CartItem cartItem){
        return cartItemService.getDetails(cartItem);
    }

    @PostMapping("items")
    public ItemDetails create(
            @RequestBody ItemForm item
    ) {
        return cartItemService.createDetails(item);
    }

    @PutMapping("items/{id}")
    public ItemDetails update(
            @PathVariable("id") CartItem oldItem,
            @RequestBody ItemForm item
    ){
        return cartItemService.updateDetails(oldItem, item);
    }

    @DeleteMapping("items/{id}")
    public void delete(
            @PathVariable("id") CartItem cartItem){
        cartItemService.delete(cartItem);
    }


}
