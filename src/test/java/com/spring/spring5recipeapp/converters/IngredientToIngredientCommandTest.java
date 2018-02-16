package com.spring.spring5recipeapp.converters;

import com.spring.spring5recipeapp.commands.IngredientCommand;
import com.spring.spring5recipeapp.domain.Ingredient;
import com.spring.spring5recipeapp.domain.Recipe;
import com.spring.spring5recipeapp.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.yaml.snakeyaml.events.Event;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientToIngredientCommandTest {

    private static final Long ID_VALUE = new Long( 1L );
    private static final String DESCRIPTION = "Ingredient 1";
    private static final BigDecimal AMOUNT = new BigDecimal( 5 );
    private static final Recipe RECIPE = new Recipe();
    private static final Long UOM_ID = new Long( 4L );

    IngredientToIngredientCommand converter;

    @Before
    public void setup() throws Exception {
        converter = new IngredientToIngredientCommand( new UnitOfMeasureToUnitOfMeasureCommand() );
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull( converter.convert( null ) );
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull( converter.convert( new Ingredient() ) );
    }

    @Test
    public void convert() throws Exception {
        //given
        Ingredient ingred = new Ingredient();
        ingred.setId( ID_VALUE );
        ingred.setDescription( DESCRIPTION );
        ingred.setRecipe( RECIPE );
        ingred.setAmount( AMOUNT );
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId( UOM_ID );
        ingred.setUnitOfMeasure( uom );

        //when
        IngredientCommand command = converter.convert( ingred );

        //then
        assertNotNull( command );
        assertNotNull( command.getUom() );
        assertNotNull( command.getRecipe() );
        assertEquals( ID_VALUE, command.getId() );
        assertEquals( DESCRIPTION, command.getDescription() );
        assertEquals( AMOUNT, command.getAmount() );
    }

    @Test
    public void testWithNullUOM() throws Exception {

        //given
        Ingredient ingred = new Ingredient();
        ingred.setId( ID_VALUE );
        ingred.setDescription( DESCRIPTION );
        ingred.setRecipe( RECIPE );
        ingred.setAmount( AMOUNT );

        //when
        IngredientCommand command = converter.convert( ingred );

        //then
        assertNotNull( command );
        assertNull( command.getUom() );
        assertEquals( ID_VALUE, command.getId() );
        assertEquals( DESCRIPTION, command.getDescription() );
        assertEquals( AMOUNT, command.getAmount() );
    }


}