package com.spring.spring5recipeapp.converters;

import com.spring.spring5recipeapp.commands.CategoryCommand;
import com.spring.spring5recipeapp.domain.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {
    @Override
    public Category convert( CategoryCommand categoryCommand ) {
        if ( categoryCommand == null ) {
            return null;
        }

        final Category category = new Category();
        category.setId( categoryCommand.getId() );
        category.setDescription( categoryCommand.getDescription() );
        return category;
    }
}
