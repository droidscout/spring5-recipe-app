package com.spring.spring5recipeapp.converters;

import com.spring.spring5recipeapp.commands.IngredientCommand;
import com.spring.spring5recipeapp.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;

    public IngredientCommandToIngredient( UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure ) {
        this.uomConverter = unitOfMeasureCommandToUnitOfMeasure;
    }

    @Synchronized
    @Nullable
    @Override
    public Ingredient convert( IngredientCommand ingredientCommand ) {
        if ( ingredientCommand == null ) {
            return null;
        }

        final Ingredient ingredient = new Ingredient();
        ingredient.setId( ingredientCommand.getId() );
        ingredient.setDescription( ingredientCommand.getDescription() );
        ingredient.setAmount( ingredientCommand.getAmount() );
        ingredient.setUnitOfMeasure( uomConverter.convert( ingredientCommand.getUom() ) );

        return ingredient;
    }
}
