package kz.pine.services;

import kz.pine.domain.Cart;
import kz.pine.domain.CartItem;
import kz.pine.domain.Category;
import kz.pine.domain.Product;
import kz.pine.form.ItemDetails;
import kz.pine.form.ItemForm;
import kz.pine.form.ProductForm;
import kz.pine.repositories.CartItemRepository;
import kz.pine.repositories.CartRepository;
import kz.pine.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartItemService {
    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    ProductService productService;

    @Autowired
    CartRepository cartRepository;

    public List<CartItem> findAll(Cart cart){
        if (cart != null && cartRepository.existsById(cart.getId()))
            return cart.getItems();
        return cartItemRepository.findAll();
    }

    public CartItem create(ItemForm itemForm){
        try{
            CartItem item = new CartItem();
            item.setQuantity(itemForm.getQuantity());
            if (itemForm.getProduct() != null)
                item.setProduct(productService.get(itemForm.getProduct()));
            if (itemForm.getCart() != null)
                item.setCart(cartRepository.getById(itemForm.getCart()));
            return cartItemRepository.save(item);
        } catch (DataIntegrityViolationException e){
            CartItem item = cartItemRepository.findByCartAndProduct(
                    cartRepository.getById(itemForm.getCart()),
                    productService.get(itemForm.getProduct()));
            return update(item, itemForm);
        }
    }

    public CartItem update(CartItem oldItem, ItemForm item){
        oldItem.setQuantity(item.getQuantity());
        if (item.getProduct() != null)
            oldItem.setProduct(productService.get(item.getProduct()));
        if (item.getCart() != null)
            oldItem.setCart(cartRepository.getById(item.getCart()));
        return cartItemRepository.save(oldItem);
    }

    public void delete(CartItem cartItem){
        cartItemRepository.delete(cartItem);
    }

    public ItemDetails getDetails(CartItem item){
        ItemDetails itemForm = new ItemDetails();
        itemForm.setId(item.getId());
        itemForm.setQuantity(item.getQuantity());
        itemForm.setProduct(productService.getDetails(item.getProduct()));
        itemForm.setCart(item.getCart().getId());
        return itemForm;
    }

    public ItemDetails createDetails(ItemForm item){
        return getDetails(create(item));
    }

    public ItemDetails updateDetails(CartItem oldItem, ItemForm item){
        return getDetails(update(oldItem, item));
    }

    public List<ItemDetails> findAllDetails(Cart cart){
        List<ItemDetails> forms = new ArrayList<>();
        for (CartItem item: findAll(cart)) {
            forms.add(getDetails(item));
        }
        return forms;
    }
}
