package com.avatar.pokeapp.proxy.api;

import com.avatar.pokeapp.model.thirdparty.PokemonListResponse;
import com.avatar.pokeapp.model.thirdparty.PokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokeApi {
    @GET("pokemon")
    Call<PokemonListResponse> pokemonList(@Query("offset") int offset, @Query("limit") int limit);

    @GET("pokemon/{id}")
    Call<PokemonResponse> getPokemonById(@Path("id") String id);

}
