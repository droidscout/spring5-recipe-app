package com.spring.spring5recipeapp.services;

import com.spring.spring5recipeapp.converters.RecipeCommandToRecipe;
import com.spring.spring5recipeapp.converters.RecipeToRecipeCommand;
import com.spring.spring5recipeapp.domain.Recipe;
import com.spring.spring5recipeapp.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;
    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks( this );

        recipeService = new RecipeServiceImpl( recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand );
    }

    @Test
    public void getRecipes() throws Exception {

        Recipe recipe = new Recipe();
        HashSet recipeData = new HashSet<>();
        recipeData.add( recipe );
        when( recipeService.getRecipes() ).thenReturn( recipeData );

        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals( recipes.size(), 1 );
        verify( recipeRepository, times( 1 ) ).findAll();
    }

    @Test
    public void getRecipeByIdTest() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId( 1L );
        Optional<Recipe> recipeOptional = Optional.of( recipe );

        when( recipeRepository.findById( anyLong() ) ).thenReturn( recipeOptional );

        Recipe recipeReturned = recipeService.findById( 1L );

        assertNotNull( "Null recipe returned", recipeReturned );
        verify(recipeRepository, times(1)).findById( anyLong() );
        verify(recipeRepository, Mockito.never()).findAll();

    }

}