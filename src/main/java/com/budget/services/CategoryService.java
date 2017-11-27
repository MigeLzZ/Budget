package com.budget.services;

import com.budget.dao.entities.Category;
import com.budget.dao.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryService implements ICategoryService {

    private final ICategoryRepository categoryRepository;

    @Autowired
    public CategoryService(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public Category getCategoryByid(long id) {
        return categoryRepository.findOne(id);
    }

    @Override
    @Transactional
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    //get category for user by type
    @Override
    @Transactional
    public Category getCategoryByType(String type, long userId) {
        if(categoryRepository.findByTypeAndUser(type,userId) == null){
            return categoryRepository.findByTypeAndUser(type);
        }
        return categoryRepository.findByTypeAndUser(type, userId);
    }

    @Override
    @Transactional
    public void saveCategory(Category category) {
        categoryRepository.saveAndFlush(category);
    }

    @Override
    @Transactional
    public List<Category> getStandartCategories() {
        return categoryRepository.getStandartCategories();
    }

    @Override
    @Transactional
    public void deleteCategoryById(long id) {
        categoryRepository.delete(id);
    }
}
