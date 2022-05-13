package kz.pine.controller;

import kz.pine.domain.Product;
import kz.pine.domain.Category;
import kz.pine.form.ProductForm;
import kz.pine.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
@CrossOrigin(origins="*")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<ProductForm> getProducts(@RequestParam(required = false, value = "categoryId", defaultValue = "0") Category category) {
        return productService.findAllDetails(category);
    }

    @GetMapping("/products/{id}")
    public ProductForm get(@PathVariable("id") Product product){
        return productService.getDetails(product);
    }

    @PostMapping("/products")
    public ProductForm create(@RequestBody ProductForm product){
        return productService.createDetails(product);
    }

    @PutMapping("/products/{id}")
    public ProductForm update(
            @PathVariable("id") Product oldProduct,
            @RequestBody ProductForm product
    ){
        return productService.updateDetails(oldProduct, product);
    }

    @DeleteMapping("/products/{id}")
    public void delete(@PathVariable("id") Product product){
        productService.delete(product);
    }
}
