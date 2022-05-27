package com.avatar.pokeapp.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
public class PokemonDetail {
    private String id;
    private String name;
    private String imgUrl;

    public PokemonDetail(String id, String name, String imgUrl) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public PokemonDetail(String name, String imgUrl) {
        this.name = name;
        this.imgUrl = imgUrl;
    }
}
