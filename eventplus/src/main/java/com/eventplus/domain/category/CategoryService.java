package com.eventplus.domain.category;

import com.eventplus.domain.category.dto.CategoryDeletionData;
import com.eventplus.domain.category.dto.CategoryDetailData;
import com.eventplus.domain.category.dto.CategoryRegistrationData;
import jakarta.validation.ValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {

    CategoryRepository categoryRepository;

    // REGISTRAR CATEGORIA
    @Transactional
    public CategoryDetailData registerCategory(CategoryRegistrationData data){
        if (categoryRepository.existsByNameIgnoreCase(data.name())){
            throw new ValidationException("The category '" + data.name() + "' already exists!");
        }
        var category = new Category(data.name());
        categoryRepository.save(category);
        return new CategoryDetailData(category.getId(), data.name());
    }

    //LISTAR TODAS AS CATEGORIAS
    public Page<CategoryDetailData> listAllCategories(Pageable pagination){
        return categoryRepository.findAll(pagination)
                .map(category -> {
                    return new CategoryDetailData(category.getId(), category.getName());
                });
    }

    //DELETAR CATEGORIA
    @Transactional
    public CategoryDetailData deleteCategory(Long id){
        var category = categoryRepository.findById(id)
                .orElseThrow(() -> new ValidationException(
                        "Category with ID " + id + " doesn't exist!"
                ));

        categoryRepository.delete(category);
        return new CategoryDetailData(category.getId(), category.getName());
    }

}
