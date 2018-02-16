package com.spring.spring5recipeapp.converters;

import com.spring.spring5recipeapp.commands.NotesCommand;
import com.spring.spring5recipeapp.domain.Notes;
import com.spring.spring5recipeapp.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesCommandToNotesTest {

    public static final String NOTES = "Cheeseburger";
    public static final Long ID_VALUE = new Long( 1L );

    NotesCommandToNotes converter;

    @Before
    public void setUp() throws Exception {
        converter = new NotesCommandToNotes();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull( converter.convert( null ) );
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull( converter.convert( new NotesCommand() ) );
    }

    @Test
    public void convert() throws Exception {
        //given
        NotesCommand command = new NotesCommand();
        command.setId( ID_VALUE );
        command.setRecipeNotes( NOTES );

        //when
         Notes note = converter.convert( command );

        //then
        assertNotNull( note );
        assertEquals( ID_VALUE, note.getId() );
        assertEquals( NOTES, note.getRecipeNotes() );
    }
}