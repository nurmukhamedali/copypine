package kz.pine.controller;

import kz.pine.domain.Category;
import kz.pine.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
@CrossOrigin(origins="*")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public List<Category> getAll() {
        return categoryService.findAll();
    }

    @GetMapping("/categories/{id}")
    public Category get(@PathVariable("id") Category category){
        return category;
    }

    @PostMapping("/categories")
    public Category create(@RequestBody Category category) {
        return categoryService.create(category);
    }

    @PutMapping("/categories/{id}")
    public Category update(
            @PathVariable("id") Category old,
            @RequestBody Category category
    ){
        return categoryService.update(old, category);
    }

    @DeleteMapping("/categories/{id}")
    public void delete(@PathVariable("id") Category category){
        categoryService.delete(category);
    }

//fsdsfd
}
