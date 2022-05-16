package kz.pine.services;

import kz.pine.domain.Category;
import kz.pine.domain.Product;
import kz.pine.form.ProductForm;
import kz.pine.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    private Product copyProperties(Product old, ProductForm product){
        old.setName(product.getName());
        old.setBrand(product.getBrand());
        old.setImage(product.getImage());
        old.setPrice(product.getPrice());
        if (product.getCategory() != null)
            old.setCategory(categoryService.get(product.getCategory()));
        return old;
    }

    private List<Product> findAll(Category category){
        if (category != null && categoryService.exist(category.getId()))
            return category.getProducts();
        return productRepository.findAll();
    }

    private Product create(ProductForm product){
        return productRepository.save(copyProperties(new Product(), product));
    }

    public Product get(Long id){
        return productRepository.getById(id);
    }

    public void delete(Product product){
        productRepository.delete(product);
    }

    private Product update(Product old, ProductForm product){
        return productRepository.save(copyProperties(old, product));
    }

    public ProductForm createForm(ProductForm product){
        return getForm(create(product));
    }

    public ProductForm getForm(Product product){
        ProductForm p = new ProductForm();
        p.setId(product.getId());
        p.setName(product.getName());
        p.setBrand(product.getBrand());
        p.setImage(product.getImage());
        p.setPrice(product.getPrice());
        p.setCategory(product.getCategory().getId());
        return p;
    }

    public ProductForm updateForm(Product oldProduct, ProductForm product){
        return getForm(update(oldProduct, product));
    }

    public List<ProductForm> findAllForm(Category category){
        List<ProductForm> forms = new ArrayList<>();
        for (Product product: findAll(category)) {
            forms.add(getForm(product));
        }
        return forms;
    }

}
