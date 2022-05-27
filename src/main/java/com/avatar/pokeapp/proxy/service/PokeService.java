package com.avatar.pokeapp.proxy.service;

import com.avatar.pokeapp.model.response.PokemonListResponse;
import com.avatar.pokeapp.model.response.PokemonResponse;

import java.io.IOException;

public interface PokeService {
    PokemonListResponse getAllPokemon(int page) throws IOException;

    PokemonResponse getPokemonById(String id) throws IOException;

}
