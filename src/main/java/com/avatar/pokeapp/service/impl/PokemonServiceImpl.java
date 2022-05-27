package com.avatar.pokeapp.service.impl;

import com.avatar.pokeapp.mapper.ChainMapper;
import com.avatar.pokeapp.mapper.PokemonSpecieMapper;
import com.avatar.pokeapp.model.api.Chain;
import com.avatar.pokeapp.model.api.Pokemon;
import com.avatar.pokeapp.model.api.PokemonDetail;
import com.avatar.pokeapp.model.api.PokemonSpecie;
import com.avatar.pokeapp.model.response.PokemonResponse;
import com.avatar.pokeapp.proxy.service.PokeService;
import com.avatar.pokeapp.service.PokemonService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
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
    ChainMapper chainMapper = Mappers.getMapper(ChainMapper.class);
    PokemonSpecieMapper pokemonSpecieMapper = Mappers.getMapper(PokemonSpecieMapper.class);
    @Override
    public List<PokemonDetail> getPokemonDetailList(int page) throws IOException {

        List<Pokemon> pokemonList = pokeService.getAllPokemon(page).getResults()
                .stream().map(result -> new Pokemon(result.getName(), result.getUrl()))
                .collect(Collectors.toList());

        List<PokemonDetail> pokemonDetailList = new ArrayList<>();

        for (Pokemon pokemon : pokemonList) {
            String urlImage = getPokemonDetail(pokemon).getSprites().getOther().getDream_world().getFront_default();
            String id = getPokemonDetail(pokemon).getId();
            PokemonDetail pokemonDetail = new PokemonDetail(id, pokemon.getName(), urlImage);
            pokemonDetailList.add(pokemonDetail);
        }

        return pokemonDetailList;
    }

    @Override
    public PokemonResponse getPokemonInformation(String id) throws IOException {
        return pokeService.getPokemonById(id);
    }

    @Override
    public PokemonDetail getEvolutionByPokeId(String pokemonId) throws IOException {

        PokemonSpecie pokemonSpecie = pokemonSpecieMapper.toModel(pokeService.getSpecieByPokemonId(pokemonId));

        String idEvolutionChain = getIdEvolution(pokemonSpecie.getEvolution_chain().getUrl());
        String pokemonName = pokemonSpecie.getName();

        Chain chain = chainMapper.toModel(pokeService.getEvolutionChain(idEvolutionChain).getChain());

        String pokemonEvolutionName;

        if (pokemonName.equals(chain.getSpecies().getName())) {
            pokemonEvolutionName = chain.getEvolves_to().get(0).getSpecies().getName();
            return getPokemonDetailByName(pokemonEvolutionName);
        }

        if (pokemonName.equals(chain.getEvolves_to().get(0).getSpecies().getName())) {
            pokemonEvolutionName = chain.getEvolves_to().get(0).getEvolves_to()
                    .get(0).getSpecies().getName();
            return getPokemonDetailByName(pokemonEvolutionName);
        }

        if (pokemonName.equals(chain.getEvolves_to().get(0).getEvolves_to().get(0).getSpecies().getName())) {

            if (!chain.getEvolves_to().get(0).getEvolves_to()
                    .get(0).getEvolves_to().isEmpty()) {
                pokemonEvolutionName = chain.getEvolves_to().get(0).getEvolves_to()
                        .get(0).getEvolves_to().get(0).getSpecies().getName();
                return getPokemonDetailByName(pokemonEvolutionName);
            }

            throw new IOException("Pokemon without evolutions");
        }

        return null;
    }

    @Override
    public List<PokemonDetail> getEvolutionListByPokeId(String pokemonId) throws IOException {
        PokemonSpecie pokemonSpecie = pokemonSpecieMapper.toModel(pokeService.getSpecieByPokemonId(pokemonId));

        String idEvolutionChain = getIdEvolution(pokemonSpecie.getEvolution_chain().getUrl());
        Chain chain = chainMapper.toModel(pokeService.getEvolutionChain(idEvolutionChain).getChain());
        PokemonDetail pokemonDetail = getPokemonDetailByName(chain.getSpecies().getName());

        List<PokemonDetail> pokemonDetailList = new ArrayList<>();
        pokemonDetailList.add(pokemonDetail);
        String pokemonEvolutionName;
        String pokemonEvolutionName2;
        String pokemonEvolutionName3;

        if (havePokemonOneEvolutions(chain)) {
            pokemonEvolutionName = chain.getEvolves_to().get(0).getSpecies().getName();
            pokemonDetailList.add(getPokemonDetailByName(pokemonEvolutionName));
            return pokemonDetailList;
        }

        if (havePokemonTwoEvolutions(chain)) {
            pokemonEvolutionName = chain.getEvolves_to().get(0).getSpecies().getName();
            pokemonEvolutionName2 = chain.getEvolves_to().get(0).getEvolves_to()
                    .get(0).getSpecies().getName();
            pokemonDetailList.add(getPokemonDetailByName(pokemonEvolutionName));
            pokemonDetailList.add(getPokemonDetailByName(pokemonEvolutionName2));
            return pokemonDetailList;
        }

        if (havePokemonThreeEvolutions(chain)) {
            pokemonEvolutionName = chain.getEvolves_to().get(0).getSpecies().getName();
            pokemonEvolutionName2 = chain.getEvolves_to().get(0).getEvolves_to()
                    .get(0).getSpecies().getName();
            pokemonEvolutionName3 = chain.getEvolves_to().get(0).getEvolves_to()
                    .get(0).getEvolves_to().get(0).getSpecies().getName();
            pokemonDetailList.add(getPokemonDetailByName(pokemonEvolutionName));
            pokemonDetailList.add(getPokemonDetailByName(pokemonEvolutionName2));
            pokemonDetailList.add(getPokemonDetailByName(pokemonEvolutionName3));
            return pokemonDetailList;
        }

        return pokemonDetailList;
    }

    private boolean havePokemonOneEvolutions(Chain chain) {
        return chain.getEvolves_to().get(0).getEvolves_to().isEmpty();
    }

    private boolean havePokemonTwoEvolutions(Chain chain) {
        return chain.getEvolves_to().get(0).getEvolves_to().get(0).getEvolves_to().isEmpty();
    }

    private boolean havePokemonThreeEvolutions(Chain chain) {
        return chain.getEvolves_to().get(0).getEvolves_to().get(0).getEvolves_to().get(0)
                .getEvolves_to().isEmpty();
    }

    private PokemonDetail getPokemonDetailByName(String pokemonName) throws IOException {
        String urlImgEvolutionName = pokeService.getPokemonByName(pokemonName).getSprites()
                .getOther().getDream_world().getFront_default();
        return PokemonDetail.builder()
                .name(pokemonName)
                .imgUrl(urlImgEvolutionName)
                .build();
    }

    private PokemonResponse getPokemonDetail(Pokemon pokemon) throws IOException {
        return pokeService.getPokemonById(getIdPokemon(pokemon));
    }

    private String getIdPokemon(Pokemon pokemon) {
        String urlWithOutBaseUrl = pokemon.getUrlDetail().replaceAll(pokeApiBaseUrl, "");
        return urlWithOutBaseUrl.replaceAll("[^0-9]", "");
    }

    private String getIdEvolution(String urlEvolutionChain) {
        String urlWithOutBaseUrl = urlEvolutionChain.replaceAll(pokeApiBaseUrl, "");
        return urlWithOutBaseUrl.replaceAll("[^0-9]","");
    }
}
