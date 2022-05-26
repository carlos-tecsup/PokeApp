package com.avatar.pokeapp.proxy.service;

import com.avatar.pokeapp.model.thirdparty.PokemonListResponse;
import com.avatar.pokeapp.model.thirdparty.PokemonResponse;


import java.io.IOException;

public interface PokeService {
    PokemonListResponse getAllPokemon(int page) throws IOException;
    PokemonResponse getPokemonById(String id) throws IOException;
}
