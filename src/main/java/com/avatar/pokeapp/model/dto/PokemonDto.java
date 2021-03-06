package com.avatar.pokeapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PokemonDto {
    private String id;
    private String name;
    private String urlImage;
}
