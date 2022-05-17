package kz.pine.services;

import kz.pine.domain.Category;
import kz.pine.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Category create(Category category) {
        Category savedCategory = this.categoryRepository.save(category);
        return savedCategory;
    }

    public Category update(Category old, Category category) {
        Category updatedCategory = this.categoryRepository.save(copyProperties(old, category));
        return updatedCategory;
    }

    public void delete(Category category){
        this.categoryRepository.delete(category);
    }

    private Category copyProperties(Category old, Category category){
        old.setName(category.getName());
        old.setImage(category.getImage());
        return old;
    }
}
