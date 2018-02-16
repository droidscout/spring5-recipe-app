package com.spring.spring5recipeapp.converters;

import com.spring.spring5recipeapp.commands.RecipeCommand;
import com.spring.spring5recipeapp.domain.Category;
import com.spring.spring5recipeapp.domain.Ingredient;
import com.spring.spring5recipeapp.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final IngredientToIngredientCommand ingredientConverter;
    private final NotesToNotesCommand notesConverter;
    private final CategoryToCategoryCommand categoryConverter;

    public RecipeToRecipeCommand( IngredientToIngredientCommand ingredientConverter,
                                  NotesToNotesCommand notesConverter,
                                  CategoryToCategoryCommand categoryConverter ) {
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert( Recipe recipe ) {
        if ( recipe == null ) {
            return null;
        }

        final RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setDescription( recipe.getDescription() );
        recipeCommand.setCookTime( recipe.getCookTime() );
        recipeCommand.setDirections( recipe.getDirections() );
        recipeCommand.setId( recipe.getId() );
        recipeCommand.setServings( recipe.getServings() );
        recipeCommand.setSource( recipe.getSource() );
        recipeCommand.setUrl( recipe.getUrl() );
        recipeCommand.setPrepTime( recipe.getPrepTime() );
        recipeCommand.setDifficulty( recipe.getDifficulty() );
        recipeCommand.setNotes( notesConverter.convert( recipe.getNotes() ) );

        if ( recipe.getCategories() != null && recipe.getCategories().size() > 0 ) {
            recipe.getCategories().forEach( ( Category category ) -> recipeCommand.getCategories().add( categoryConverter.convert( category ) ) );
        }

        if ( recipe.getIngredients() != null && recipe.getIngredients().size() > 0 ) {
            recipe.getIngredients().forEach( ( Ingredient ingredient ) -> recipeCommand.getIngredients().add( ingredientConverter.convert( ingredient ) ) );
        }

        return recipeCommand;
    }
}
