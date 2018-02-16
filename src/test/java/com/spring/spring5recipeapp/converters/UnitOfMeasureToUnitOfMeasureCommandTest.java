package com.spring.spring5recipeapp.converters;

import com.spring.spring5recipeapp.commands.UnitOfMeasureCommand;
import com.spring.spring5recipeapp.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureToUnitOfMeasureCommandTest {

    public static final String DESCRIPTION = "description";
    private static final Long ID_VALUE = new Long( 1L );

    UnitOfMeasureToUnitOfMeasureCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull( converter.convert( null ) );
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull( converter.convert( new UnitOfMeasure() ) );
    }

    @Test
    public void convert() throws Exception {
        //given
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId( ID_VALUE );
        uom.setDescription( DESCRIPTION );

        //when
        UnitOfMeasureCommand uomCommand = converter.convert( uom );

        //then
        assertNotNull(uomCommand);
        assertEquals(ID_VALUE, uomCommand.getId());
        assertEquals( DESCRIPTION, uomCommand.getDescription() );

    }

}