package com.spring.spring5recipeapp.converters;

import com.spring.spring5recipeapp.commands.CategoryCommand;
import com.spring.spring5recipeapp.domain.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {
    @Override
    public CategoryCommand convert( Category category ) {
        if ( category == null ) {
            return null;
        }

        final CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId( category.getId() );
        categoryCommand.setDescription( category.getDescription() );

        return categoryCommand;
    }
}
