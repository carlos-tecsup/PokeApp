package com.avatar.pokeapp.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pokemon {
    private String name;
    private String urlDetail;
}
