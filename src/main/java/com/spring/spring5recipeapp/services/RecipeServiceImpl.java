package com.spring.spring5recipeapp.services;

import com.spring.spring5recipeapp.domain.Recipe;
import com.spring.spring5recipeapp.repositories.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService{
    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl( RecipeRepository recipeRepository ) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipeSet = new HashSet<>(  );
        recipeRepository.findAll().iterator().forEachRemaining( recipeSet::add );
        return recipeSet;
    }
}
