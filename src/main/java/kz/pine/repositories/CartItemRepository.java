package kz.pine.repositories;

import kz.pine.domain.Cart;
import kz.pine.domain.CartItem;
import kz.pine.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCartAndProduct(Cart cart, Product product);
}
