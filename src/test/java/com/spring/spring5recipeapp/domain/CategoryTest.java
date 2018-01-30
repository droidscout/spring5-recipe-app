package com.spring.spring5recipeapp.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CategoryTest {

    Category category;

    @Before
    public void setup() {
        category= new Category();

    }
    @Test
    public void getId() throws Exception {

        Long idValue = 4L;

        category.setId(idValue );
        assertEquals( idValue, category.getId() );
    }

    @Test
    public void getDescription() throws Exception {
    }

    @Test
    public void getRecipe() throws Exception {
    }

}