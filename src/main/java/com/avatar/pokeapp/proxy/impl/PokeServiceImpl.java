package com.avatar.pokeapp.proxy.impl;

import com.avatar.pokeapp.model.response.EvolutionChainResponse;
import com.avatar.pokeapp.model.response.PokemonListResponse;
import com.avatar.pokeapp.model.response.PokemonResponse;
import com.avatar.pokeapp.model.response.PokemonSpecieResponse;
import com.avatar.pokeapp.proxy.api.PokeApi;
import com.avatar.pokeapp.proxy.service.PokeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;

import static com.avatar.pokeapp.utils.Constant.LIMIT_PER_PAGE;

@Service
@RequiredArgsConstructor
@Slf4j
public class PokeServiceImpl implements PokeService {

    private final PokeApi pokeApi;

    @Override
    public PokemonListResponse getAllPokemon(int pageNumber) throws IOException {
        int limitPerPage = LIMIT_PER_PAGE;

        int offset = limitPerPage * (pageNumber - 1);

        Response<PokemonListResponse> response = pokeApi.pokemonList(offset, limitPerPage).execute();

        if (!response.isSuccessful()) {
            throw new IOException(response.errorBody() != null ? response.errorBody().string() : "Unknown error");
        }
        return response.body();
    }

    @Override
    public PokemonResponse getPokemonById(String id) throws IOException {
        Response<PokemonResponse> response = pokeApi.getPokemonById(id).execute();

        if (!response.isSuccessful()) {
            throw new IOException(response.errorBody() != null ? response.errorBody().string() : "Unknown error");
        }
        return response.body();
    }
    @Override
    public PokemonResponse getPokemonByName(String name) throws IOException {
        Response<PokemonResponse> response = pokeApi.getPokemonByName(name).execute();

        if (!response.isSuccessful()) {
            throw new IOException(response.errorBody() != null ? response.errorBody().string() : "Unknown error");
        }
        return response.body();
    }

    @Override
    public EvolutionChainResponse getEvolutionChain(String idEvolutionChain) throws IOException {
        Response<EvolutionChainResponse> response = pokeApi.getEvolutionChain(idEvolutionChain).execute();

        if (!response.isSuccessful()) {
            throw new IOException(response.errorBody() != null ? response.errorBody().string() : "Unknown error");
        }
        return response.body();
    }

    @Override
    public PokemonSpecieResponse getSpecieByPokemonId(String pokemonId) throws IOException {
        Response<PokemonSpecieResponse> response = pokeApi.getPokemonSpecieByPokemonId(pokemonId).execute();

        if (!response.isSuccessful()) {
            throw new IOException(response.errorBody() != null ? response.errorBody().string() : "Unknown error");
        }
        return response.body();
    }
}
