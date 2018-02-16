package com.spring.spring5recipeapp.converters;

import com.spring.spring5recipeapp.commands.CategoryCommand;
import com.spring.spring5recipeapp.domain.Category;
import org.junit.Before;
import org.junit.Test;
import org.yaml.snakeyaml.events.Event;

import static org.junit.Assert.*;


public class CategoryToCategoryCommandTest {

    private static final Long ID_VALUE = new Long(1L);
    private static final String DESCRIPTION = "Category 1";

    private CategoryToCategoryCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new CategoryToCategoryCommand();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull( converter.convert( null ));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull( converter.convert( new Category() ) );
    }

    @Test
    public void convert() throws Exception {
        //given
        Category category = new Category();
        category.setId( ID_VALUE );
        category.setDescription( DESCRIPTION );

        //when
        CategoryCommand command = converter.convert( category );

        //then
        assertNotNull( command );
        assertEquals( ID_VALUE, command.getId() );
        assertEquals( DESCRIPTION, command.getDescription() );

    }

}