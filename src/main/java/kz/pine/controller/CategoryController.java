package kz.pine.controller;

import kz.pine.domain.Category;
import kz.pine.form.CategoryForm;
import kz.pine.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
@CrossOrigin(origins="*")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> getCategories() {
        return categoryService.findAll();
    }

    @GetMapping("/categories/{id}")
    public Category get(
            @PathVariable("id") Category category){
        return category;
    }

    @PostMapping("/categories")
    public Category create(
            @RequestBody CategoryForm category
    ) {
        return categoryService.create(category);
    }
//adsadsad
    @PutMapping("/categories/{id}")
    public Category update(
            @PathVariable("id") Category oldCategory,
            @RequestBody CategoryForm category
    ){
        return categoryService.update(oldCategory, category);
    }

    @DeleteMapping("/categories/{id}")
    public void delete(@PathVariable("id") Category category){
        categoryService.delete(category);
    }
}
