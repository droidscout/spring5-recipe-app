package com.spring.spring5recipeapp.converters;

import com.spring.spring5recipeapp.commands.UnitOfMeasureCommand;
import com.spring.spring5recipeapp.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasure convert( UnitOfMeasureCommand unitOfMeasureCommand ) {
        if ( unitOfMeasureCommand == null ) {
            return null;
        }

        final UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId( unitOfMeasureCommand.getId() );
        unitOfMeasure.setDescription( unitOfMeasureCommand.getDescription() );
        return unitOfMeasure;
    }
}
