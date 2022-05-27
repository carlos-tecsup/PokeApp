package com.avatar.pokeapp.proxy.service;

import com.avatar.pokeapp.model.response.EvolutionChainResponse;
import com.avatar.pokeapp.model.response.PokemonListResponse;
import com.avatar.pokeapp.model.response.PokemonResponse;
import com.avatar.pokeapp.model.response.PokemonSpecieResponse;

import java.io.IOException;

public interface PokeService {
    PokemonListResponse getAllPokemon(int page) throws IOException;

    PokemonResponse getPokemonById(String id) throws IOException;

    PokemonResponse getPokemonByName(String name) throws IOException;
    EvolutionChainResponse getEvolutionChain(String idEvolutionChain) throws IOException;

    PokemonSpecieResponse getSpecieByPokemonId(String pokemonId) throws IOException;
}
