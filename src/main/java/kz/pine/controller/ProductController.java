package kz.pine.controller;

import kz.pine.domain.Category;
import kz.pine.domain.Product;
import kz.pine.dto.EventType;
import kz.pine.dto.ObjectType;
import kz.pine.form.ProductForm;
import kz.pine.services.ProductService;
import kz.pine.util.WsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.BiConsumer;

@RestController
@RequestMapping("admin")
@CrossOrigin(origins="*")
public class ProductController {
    private ProductService productService;
    private final BiConsumer<EventType, ProductForm> wsSender;

    @Autowired
    public ProductController(ProductService productService, WsSender wsSender) {
        this.productService = productService;
        this.wsSender = wsSender.getSender(ObjectType.PRODUCT);
    }

    @GetMapping("/products")
    public List<ProductForm> getAll(@RequestParam(required = false, value = "categoryId", defaultValue = "0") Category category) {
        return productService.findAllForm(category);
    }

    @GetMapping("/products/{id}")
    public ProductForm get(@PathVariable("id") Product product){
        return productService.getForm(product);
    }

    @PostMapping("/products")
    public ProductForm create(@RequestBody ProductForm product){
        ProductForm updatedProduct = productService.createForm(product);
        wsSender.accept(EventType.CREATE, updatedProduct);
        return updatedProduct;
    }

    @PutMapping("/products/{id}")
    public ProductForm update(
            @PathVariable("id") Product oldProduct,
            @RequestBody ProductForm product
    ){
        ProductForm updatedProduct = productService.updateForm(oldProduct, product);
        wsSender.accept(EventType.UPDATE, updatedProduct);

        return updatedProduct;
    }

    @DeleteMapping("/products/{id}")
    public void delete(@PathVariable("id") Product product){
        productService.delete(product);
        wsSender.accept(EventType.REMOVE, get(product));
    }


}
