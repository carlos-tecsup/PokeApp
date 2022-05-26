package com.avatar.pokeapp.service;

import com.avatar.pokeapp.model.api.Pokemon;
import com.avatar.pokeapp.model.api.PokemonDetail;
import java.io.IOException;
import java.util.List;

public interface PokemonService {

    List<Pokemon> getPokemonList(int page) throws IOException;
    List<PokemonDetail> getPokemonDetailList(int page) throws IOException;

}
