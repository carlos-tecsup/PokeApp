package com.avatar.pokeapp.mapper;

import com.avatar.pokeapp.model.api.Pokemon;
import com.avatar.pokeapp.model.api.PokemonDetail;
import com.avatar.pokeapp.model.dto.PokemonDetailDto;
import com.avatar.pokeapp.model.dto.PokemonDto;
import org.mapstruct.Mapper;

@Mapper
public interface PokemonMapper {
    PokemonDetailDto pokemonDetailToDto(PokemonDetail pokemonDetail);
    PokemonDto toDto(Pokemon pokemon);

}
