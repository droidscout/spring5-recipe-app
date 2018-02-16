package com.spring.spring5recipeapp.services;

import com.spring.spring5recipeapp.commands.RecipeCommand;
import com.spring.spring5recipeapp.converters.RecipeCommandToRecipe;
import com.spring.spring5recipeapp.converters.RecipeToRecipeCommand;
import com.spring.spring5recipeapp.domain.Recipe;
import com.spring.spring5recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl( RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand ) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug( "I'm a service" );
        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining( recipeSet::add );
        return recipeSet;
    }

    @Override
    public Recipe findById( Long id ) {

        Optional<Recipe> recipeOptional = recipeRepository.findById( id );

        if ( !recipeOptional.isPresent() ) {
            throw new RuntimeException( "Recipe Not found" );
        }

        return recipeOptional.get();
    }

    @Transactional
    @Override
    public RecipeCommand saveRecipeCommand( RecipeCommand command ) {
        Recipe detachedRcipe = recipeCommandToRecipe.convert( command );

        Recipe savedRecipe = recipeRepository.save( detachedRcipe );
        log.debug( "Saved RecipeId: " + savedRecipe.getId() );
        return recipeToRecipeCommand.convert( savedRecipe );
    }


}
