package com.avatar.pokeapp.model.thirdparty;

import com.avatar.pokeapp.model.api.Type;
import lombok.Data;

import java.util.List;
@Data
public class PokemonResponse {
    private String name;
    private SpritesResponse sprites;
    private String id;
    private List<Type> types;
    private String weight;
    private String height;
}
