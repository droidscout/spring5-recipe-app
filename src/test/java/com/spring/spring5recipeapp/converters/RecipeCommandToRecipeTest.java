package com.spring.spring5recipeapp.converters;

import com.spring.spring5recipeapp.commands.CategoryCommand;
import com.spring.spring5recipeapp.commands.IngredientCommand;
import com.spring.spring5recipeapp.commands.NotesCommand;
import com.spring.spring5recipeapp.commands.RecipeCommand;
import com.spring.spring5recipeapp.domain.Difficulty;
import com.spring.spring5recipeapp.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {

    private static final Long ID_VALUE = new Long( 1L );
    private static final NotesCommand RECIPE_NOTES = new NotesCommand();
    private static final Long RECIPE_NOTES_ID = new Long(3L);
    private static final String RECIPE_NOTES_DESCR = "Recipe Notes Description";
    private static final Difficulty DIFFICULTY = Difficulty.MEDIUM;
    private static final CategoryCommand CAT_COMMAND = new CategoryCommand();

    private static final IngredientCommand INGR_COMMAND = new IngredientCommand();

    private static final String DESCRIPTION = "Recipe Description";
    private static final String DIRECTIONS = "This is a direction";
    private static final String INGRE_DESCRIPTION = "This is a ingredient description";
    private static final String SOURCE = "This is the source of the recipe";
    private static final String URL = "http://www.google.de";
    private static final Integer COOK_TIME = 1;
    private static final Integer PREP_TIME = 4;
    private static final Integer SERVINGS = 6;

    RecipeCommandToRecipe converter;
    UnitOfMeasureCommandToUnitOfMeasure uomConverter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeCommandToRecipe( new CategoryCommandToCategory(),
                new IngredientCommandToIngredient( new UnitOfMeasureCommandToUnitOfMeasure() ),
                new NotesCommandToNotes() );
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull( converter.convert( null ) );
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull( converter.convert( new RecipeCommand() ) );
    }

    @Test
    public void convert() throws Exception {
        //given
        RecipeCommand command = new RecipeCommand();

        RECIPE_NOTES.setId( RECIPE_NOTES_ID );
        RECIPE_NOTES.setRecipeNotes( RECIPE_NOTES_DESCR );
        command.setNotes( RECIPE_NOTES );

        command.setId( ID_VALUE );
        command.setSource( SOURCE );
        command.setServings( SERVINGS );
        command.setUrl( URL );
        command.setDescription( DESCRIPTION );
        command.setDirections( DIRECTIONS );
        command.setCookTime( COOK_TIME );
        command.setPrepTime( PREP_TIME );
        command.setDifficulty( DIFFICULTY );

        INGR_COMMAND.setDescription( INGRE_DESCRIPTION );
        INGR_COMMAND.setId( 4L );
        INGR_COMMAND.setAmount( new BigDecimal( 23 ) );

        command.getIngredients().add( INGR_COMMAND );

        CAT_COMMAND.setId( 33L );
        CAT_COMMAND.setDescription( "This is a categories description" );
        command.getCategories().add( CAT_COMMAND );

        //when
        Recipe recipe = converter.convert( command );

        //then
        assertNotNull( recipe );
        assertNotNull( recipe.getCategories() );
        assertNotNull( recipe.getIngredients() );
        assertEquals( RECIPE_NOTES_ID, recipe.getNotes().getId() );
        assertEquals( RECIPE_NOTES_DESCR, recipe.getNotes().getRecipeNotes() );
        assertEquals( ID_VALUE, recipe.getId() );
        assertEquals( SOURCE, recipe.getSource() );
        assertEquals( SERVINGS, recipe.getServings() );
        assertEquals( URL, recipe.getUrl() );
        assertEquals( DESCRIPTION, recipe.getDescription() );
        assertEquals( DIRECTIONS, recipe.getDirections() );
        assertEquals( COOK_TIME, recipe.getCookTime() );
        assertEquals( PREP_TIME, recipe.getPrepTime() );
        assertEquals( DIFFICULTY, recipe.getDifficulty() );
    }

    @Test
    public void testWithNullProperties() throws Exception {

        //given
        RecipeCommand command = new RecipeCommand();
        RECIPE_NOTES.setId( RECIPE_NOTES_ID );
        RECIPE_NOTES.setRecipeNotes( RECIPE_NOTES_DESCR );
        command.setNotes( RECIPE_NOTES );
        command.setId( ID_VALUE );
        command.setSource( SOURCE );
        command.setServings( SERVINGS );
        command.setUrl( URL );
        command.setDescription( DESCRIPTION );
        command.setDirections( DIRECTIONS );
        command.setCookTime( COOK_TIME );
        command.setPrepTime( PREP_TIME );
        command.setDifficulty( DIFFICULTY );

        //when
        Recipe recipe = converter.convert( command );

        //then
        assertNotNull( recipe );
        assertNotNull( recipe.getCategories() );
        assertNotNull( recipe.getIngredients() );
        assertNotNull( recipe.getNotes() );
        assertEquals( ID_VALUE, recipe.getId() );
        assertEquals( SOURCE, recipe.getSource() );
        assertEquals( SERVINGS, recipe.getServings() );
        assertEquals( URL, recipe.getUrl() );
        assertEquals( DESCRIPTION, recipe.getDescription() );
        assertEquals( DIRECTIONS, recipe.getDirections() );
        assertEquals( COOK_TIME, recipe.getCookTime() );
        assertEquals( PREP_TIME, recipe.getPrepTime() );
        assertEquals( DIFFICULTY, recipe.getDifficulty() );
    }

}