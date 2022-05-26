package com.avatar.pokeapp.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PokemonDetail {
    private String id;
    private String name;
    private String imgUrl;
}
