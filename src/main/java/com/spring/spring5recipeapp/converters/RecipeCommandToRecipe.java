package com.spring.spring5recipeapp.converters;

import com.spring.spring5recipeapp.commands.CategoryCommand;
import com.spring.spring5recipeapp.commands.IngredientCommand;
import com.spring.spring5recipeapp.commands.RecipeCommand;
import com.spring.spring5recipeapp.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final CategoryCommandToCategory categoryConverter;
    private final IngredientCommandToIngredient ingredientConverter;
    private final NotesCommandToNotes notesConverter;

    public RecipeCommandToRecipe( CategoryCommandToCategory categoryConverter,
                                  IngredientCommandToIngredient ingredientConverter,
                                  NotesCommandToNotes notesConverter ) {
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert( RecipeCommand recipeCommand ) {
        if ( recipeCommand == null ) {
            return null;
        }

        final Recipe recipe = new Recipe();
        recipe.setId( recipeCommand.getId() );
        recipe.setCookTime( recipeCommand.getCookTime() );
        recipe.setPrepTime( recipeCommand.getPrepTime() );
        recipe.setDescription( recipeCommand.getDescription() );
        recipe.setDifficulty( recipeCommand.getDifficulty() );
        recipe.setDirections( recipeCommand.getDirections() );
        recipe.setServings( recipeCommand.getServings() );
        recipe.setSource( recipeCommand.getSource() );
        recipe.setUrl( recipeCommand.getUrl() );
        recipe.setNotes( notesConverter.convert( recipeCommand.getNotes() ) );

        if ( recipeCommand.getIngredients() != null && recipeCommand.getIngredients().size() > 0 ) {
            recipeCommand.getIngredients()
                    .forEach( ( IngredientCommand ingredient ) -> recipe.getIngredients().add( ingredientConverter.convert( ingredient ) ) );
        }

        if ( recipeCommand.getCategories() != null && recipeCommand.getCategories().size() > 0 ) {
            recipeCommand.getCategories()
                    .forEach( ( CategoryCommand catCommand ) -> recipe.getCategories().add( categoryConverter.convert( catCommand ) ) );
        }
        return recipe;
    }
}
