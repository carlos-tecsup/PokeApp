package com.avatar.pokeapp.model.api;

import lombok.Data;

@Data
public class Pokemon {
    private String id;
    private String name;
    private String urlDetail;
    public Pokemon(String name, String urlDetail) {
        this.name = name;
        this.urlDetail = urlDetail;
    }

}
