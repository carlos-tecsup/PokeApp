package com.avatar.pokeapp.model.dto;

import com.avatar.pokeapp.model.api.Info;
import com.avatar.pokeapp.model.api.Type;
import com.avatar.pokeapp.model.thirdparty.SpritesResponse;
import lombok.Data;

import java.util.List;
@Data
public class PokemonDetailDto {
    private String name;
    private String urlImage;
    private List<Info> types;
    private String weight;
    private String height;
}
