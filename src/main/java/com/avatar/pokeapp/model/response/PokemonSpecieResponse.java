package com.avatar.pokeapp.model.response;

import lombok.Data;

@Data
public class PokemonSpecieResponse {
    private EvoluteChainResponse evolution_chain;
    private String name;
}
