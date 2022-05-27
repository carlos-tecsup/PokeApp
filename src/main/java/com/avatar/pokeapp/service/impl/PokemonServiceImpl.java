package com.avatar.pokeapp.service.impl;

import com.avatar.pokeapp.model.api.Pokemon;
import com.avatar.pokeapp.model.api.PokemonDetail;
import com.avatar.pokeapp.model.response.PokemonResponse;
import com.avatar.pokeapp.proxy.service.PokeService;
import com.avatar.pokeapp.service.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PokemonServiceImpl implements PokemonService {

    @Value("${http-client.pokeapi.base-url}")
    private String pokeApiBaseUrl;

    private final PokeService pokeService;

    @Override
    public List<Pokemon> getPokemonList(int page) throws IOException {

        return pokeService.getAllPokemon(page).getResults()
            .stream().map(result -> new Pokemon(result.getName(), result.getUrl()))
            .collect(Collectors.toList());
    }

    @Override
    public List<PokemonDetail> getPokemonDetailList(int page) throws IOException {

        List<Pokemon> pokemonList = pokeService.getAllPokemon(page).getResults()
                .stream().map(result -> new Pokemon(result.getName(), result.getUrl()))
                .collect(Collectors.toList());

        List<PokemonDetail> pokemonDetailList = new ArrayList<>();

        for (Pokemon pokemon : pokemonList) {
            String urlImage = getPokemonDetail(pokemon).getSprites().getOther().getDream_world().getFront_default();
            String id = getPokemonDetail(pokemon).getId();
            PokemonDetail pokemonDetail = new PokemonDetail(id,pokemon.getName(), urlImage);
            pokemonDetailList.add(pokemonDetail);
        }

        return pokemonDetailList;
    }

    @Override
    public PokemonResponse getPokemonInformation(String id) throws IOException {
        return pokeService.getPokemonById(id);
    }

    private PokemonResponse getPokemonDetail(Pokemon pokemon) throws IOException {
        return pokeService.getPokemonById(getIdPokemon(pokemon));
    }

    private String getIdPokemon(Pokemon pokemon) {
        String urlWithOutBaseUrl = pokemon.getUrlDetail().replaceAll(pokeApiBaseUrl, "");
        return urlWithOutBaseUrl.replaceAll("[^0-9]","");
    }

}
