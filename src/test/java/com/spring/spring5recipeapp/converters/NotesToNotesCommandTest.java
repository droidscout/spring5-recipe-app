package com.spring.spring5recipeapp.converters;

import com.spring.spring5recipeapp.commands.NotesCommand;
import com.spring.spring5recipeapp.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesToNotesCommandTest {

    public static final String NOTES = "Cheeseburger";
    public static final Long ID_VALUE = new Long( 1L );

    NotesToNotesCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new NotesToNotesCommand();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull( converter.convert( null ) );
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull( converter.convert( new Notes() ) );
    }

    @Test
    public void convert() throws Exception {
        //given
        Notes note = new Notes();
        note.setId( ID_VALUE );
        note.setRecipeNotes( NOTES );

        //when
        NotesCommand command = converter.convert( note );

        //then
        assertNotNull( command );
        assertEquals( ID_VALUE, command.getId() );
        assertEquals( NOTES, command.getRecipeNotes() );
    }
}