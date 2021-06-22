package com.todoNuraliev.demo.controllers;

import com.todoNuraliev.demo.models.Category;
import com.todoNuraliev.demo.repositories.CategoryRepo;
import com.todoNuraliev.demo.services.CategoryService;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Log4j
@RestController
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryRepo categoryRepo) {
        this.categoryService = new CategoryService(categoryRepo);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Category> getAll() {
        return categoryService.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/categories/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Category getById(@PathVariable Long categoryId) {
        return categoryService.getById(categoryId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
    public Category add(@Valid @RequestBody Category category) {
        return categoryService.add(category);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/categories/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Category update(@PathVariable Long categoryId, @Valid @RequestBody Category categoryRequest) {
        return categoryService.update(categoryId, categoryRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/categories/{categoryId}")
    public void delete(@PathVariable Long categoryId) {
        categoryService.delete(categoryService.getById(categoryId));
    }



}
