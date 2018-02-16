package com.spring.spring5recipeapp.converters;

import com.spring.spring5recipeapp.commands.CategoryCommand;
import com.spring.spring5recipeapp.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryCommandToCategoryTest {

    private static final Long ID_VALUE = new Long( 1L );
    private static final String DESCRIPTION = "This is a description";

    CategoryCommandToCategory converter;

    @Before
    public void setUp() throws Exception {
        converter = new CategoryCommandToCategory();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull( converter.convert( null ) );
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull( converter.convert( new CategoryCommand() ) );
    }

    @Test
    public void convert() throws Exception {

        //given
        CategoryCommand command = new CategoryCommand();
        command.setDescription( DESCRIPTION );
        command.setId( ID_VALUE );

        //when
        Category category = converter.convert( command );

        //then
        assertNotNull( category );
        assertEquals( ID_VALUE, category.getId() );
        assertEquals( DESCRIPTION, category.getDescription() );
    }

}