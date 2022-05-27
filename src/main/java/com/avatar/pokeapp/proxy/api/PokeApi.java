package com.avatar.pokeapp.proxy.api;

import com.avatar.pokeapp.model.response.EvolutionChainResponse;
import com.avatar.pokeapp.model.response.PokemonListResponse;
import com.avatar.pokeapp.model.response.PokemonResponse;
import com.avatar.pokeapp.model.response.PokemonSpecieResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokeApi {
    @GET("pokemon")
    Call<PokemonListResponse> pokemonList(@Query("offset") int offset, @Query("limit") int limit);

    @GET("pokemon/{id}")
    Call<PokemonResponse> getPokemonById(@Path("id") String id);

    @GET("pokemon/{name}")
    Call<PokemonResponse> getPokemonByName(@Path("name") String name);

    @GET("evolution-chain/{pokemonId}")
    Call<EvolutionChainResponse> getEvolutionChain(@Path("pokemonId") String pokemonId);

    @GET("pokemon-species/{pokemonId}")
    Call<PokemonSpecieResponse> getPokemonSpecieByPokemonId(@Path("pokemonId") String pokemonId);
}
