package com.spring.spring5recipeapp.converters;


import com.spring.spring5recipeapp.commands.RecipeCommand;
import com.spring.spring5recipeapp.domain.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class RecipeToRecipeCommandTest {

    private static final Long ID_VALUE = new Long( 1L );
    private static final Notes RECIPE_NOTES = new Notes();
    private static final Difficulty DIFFICULTY = Difficulty.MEDIUM;
    private static final Category CAT_COMMAND = new Category();

    private static final Ingredient INGR_COMMAND = new Ingredient();

    private static final String DESCRIPTION = "Recipe Description";
    private static final String DIRECTIONS = "This is a direction";
    private static final String SOURCE = "This is the source of the recipe";
    private static final String URL = "http://www.google.de";
    private static final Integer COOK_TIME = 1;
    private static final Integer PREP_TIME = 4;
    private static final Integer SERVINGS = 6;

    RecipeToRecipeCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeToRecipeCommand( new IngredientToIngredientCommand( new UnitOfMeasureToUnitOfMeasureCommand() ),
                new NotesToNotesCommand(),
                new CategoryToCategoryCommand() );
    }

    @Test
    public void testForNullObject() throws Exception {
        assertNull( converter.convert( null ) );
    }

    @Test
    public void testForEmptyObject() throws Exception {
        assertNotNull( converter.convert( new Recipe() ) );
    }

    @Test
    public void convert() throws Exception {
        //given
        Recipe recipe = new Recipe();
        recipe.setNotes( RECIPE_NOTES );
        recipe.setId( ID_VALUE );
        recipe.setSource( SOURCE );
        recipe.setServings( SERVINGS );
        recipe.setUrl( URL );
        recipe.setDescription( DESCRIPTION );
        recipe.setDirections( DIRECTIONS );
        recipe.setCookTime( COOK_TIME );
        recipe.setPrepTime( PREP_TIME );
        recipe.setDifficulty( DIFFICULTY );

        Set<Ingredient> ingredientsCommandSet = new HashSet<>();
        ingredientsCommandSet.add( INGR_COMMAND );
        recipe.setIngredients( ingredientsCommandSet );

        Set<Category> categoryCommandSet = new HashSet<>();
        categoryCommandSet.add( CAT_COMMAND );
        recipe.setCategories( categoryCommandSet );

        //when
        RecipeCommand recipeCommand = converter.convert( recipe );

        //then
        assertNotNull( recipeCommand );
        assertNotNull( recipeCommand.getCategories() );
        assertNotNull( recipeCommand.getIngredients() );
        assertNotNull( recipeCommand.getNotes() );
        assertEquals( ID_VALUE, recipeCommand.getId() );
        assertEquals( SOURCE, recipeCommand.getSource() );
        assertEquals( SERVINGS, recipeCommand.getServings() );
        assertEquals( URL, recipeCommand.getUrl() );
        assertEquals( DESCRIPTION, recipeCommand.getDescription() );
        assertEquals( DIRECTIONS, recipeCommand.getDirections() );
        assertEquals( COOK_TIME, recipeCommand.getCookTime() );
        assertEquals( PREP_TIME, recipeCommand.getPrepTime() );
        assertEquals( DIFFICULTY, recipeCommand.getDifficulty() );

    }
}