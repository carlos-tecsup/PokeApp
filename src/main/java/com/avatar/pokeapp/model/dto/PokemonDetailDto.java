package com.avatar.pokeapp.model.dto;

import com.avatar.pokeapp.model.api.Info;
import lombok.Data;

import java.util.List;
@Data
public class PokemonDetailDto {
    private String name;
    private String mainType;
    private String urlImage;
    private int baseExperience;
    private int weight;
    private double height;
    private List<Info> types;
    private List<Info> abilities;

}
