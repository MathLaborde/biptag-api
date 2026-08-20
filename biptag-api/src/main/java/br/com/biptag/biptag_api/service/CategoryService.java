package br.com.biptag.biptag_api.service;

import br.com.biptag.biptag_api.model.Category;
import br.com.biptag.biptag_api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Equivale a police de SELECT do Supabase
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }
}
