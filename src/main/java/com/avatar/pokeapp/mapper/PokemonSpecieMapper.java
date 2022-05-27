package com.avatar.pokeapp.mapper;

import com.avatar.pokeapp.model.api.PokemonSpecie;
import com.avatar.pokeapp.model.response.PokemonSpecieResponse;
import org.mapstruct.Mapper;

@Mapper
public interface PokemonSpecieMapper {
    PokemonSpecie toModel(PokemonSpecieResponse pokemonSpecieResponse);

}
