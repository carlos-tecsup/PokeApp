package com.avatar.pokeapp.model.response;

import com.avatar.pokeapp.model.api.Abiliti;
import com.avatar.pokeapp.model.api.Type;
import lombok.Data;

import java.util.List;
@Data
public class PokemonResponse {
    private String name;
    private SpritesResponse sprites;
    private String id;
    private int base_experience;
    private List<Type> types;
    private List<Abiliti> abilities;
    private int weight;
    private double height;
}
