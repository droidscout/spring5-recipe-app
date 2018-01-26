package com.spring.spring5recipeapp.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Category {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToMany (mappedBy = "categories")
    private Set<Recipe> recipe;

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public Set<Recipe> getRecipe() {
        return recipe;
    }

    public void setRecipe( Set<Recipe> recipe ) {
        this.recipe = recipe;
    }
}
