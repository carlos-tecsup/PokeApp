package com.avatar.pokeapp.service;

import com.avatar.pokeapp.model.api.PokemonDetail;
import com.avatar.pokeapp.model.response.PokemonResponse;

import java.io.IOException;
import java.util.List;

public interface PokemonService {

    List<PokemonDetail> getPokemonDetailList(int page) throws IOException;

    PokemonResponse getPokemonInformation(String id) throws IOException;

}
