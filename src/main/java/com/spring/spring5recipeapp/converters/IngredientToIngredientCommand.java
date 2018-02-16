package com.spring.spring5recipeapp.converters;

import com.spring.spring5recipeapp.commands.IngredientCommand;
import com.spring.spring5recipeapp.domain.Ingredient;
import com.spring.spring5recipeapp.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private final UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    public IngredientToIngredientCommand( UnitOfMeasureToUnitOfMeasureCommand uomConverter ) {
        this.uomConverter = uomConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert( Ingredient ingredient ) {
        if(ingredient == null ){
            return null;
        }

        final IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId( ingredient.getId() );
        ingredientCommand.setDescription( ingredient.getDescription() );
        ingredientCommand.setAmount( ingredient.getAmount() );
        UnitOfMeasure uom = ingredient.getUnitOfMeasure();
        ingredientCommand.setUom( uomConverter.convert( uom ) );
        return ingredientCommand;
    }
}
