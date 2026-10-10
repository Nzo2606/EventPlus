package com.eventplus.controller;

import com.eventplus.domain.category.CategoryService;
import com.eventplus.domain.category.dto.CategoryDetailData;
import com.eventplus.domain.category.dto.CategoryRegistrationData;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;



    @PostMapping
    public ResponseEntity<CategoryDetailData> register(@RequestBody @Valid CategoryRegistrationData data, UriComponentsBuilder uriBuilder){
        var category = categoryService.registerCategory(data);

        var uri = uriBuilder.path("/categories/{id}").buildAndExpand(category.id()).toUri();

        return ResponseEntity.created(uri).body(category);
    }

    @GetMapping
    public ResponseEntity<Page<CategoryDetailData>> list(@PageableDefault (size = 10, sort = {"name"}) Pageable pagination){
        var page = categoryService.listAllCategories(pagination);

        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/id")
    public ResponseEntity delete(@PathVariable Long id){
        var category = categoryService.deleteCategory(id);

        return ResponseEntity.noContent().build();
    }
}
