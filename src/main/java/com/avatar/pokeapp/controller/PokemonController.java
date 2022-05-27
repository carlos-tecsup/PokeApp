package com.avatar.pokeapp.controller;

import com.avatar.pokeapp.mapper.PokemonMapper;
import com.avatar.pokeapp.model.dto.PokemonDetailDto;
import com.avatar.pokeapp.model.dto.PokemonDto;
import com.avatar.pokeapp.model.response.PokemonResponse;
import com.avatar.pokeapp.service.PokemonService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@CrossOrigin("*")
public class PokemonController {

    private final PokemonService pokemonService;
    PokemonMapper mapper = Mappers.getMapper(PokemonMapper.class);

    @RequestMapping(value = "/pokemons/{page}", method = RequestMethod.GET)
    public String getAllPokemon(Model model, @PathVariable(value="page") int page) throws IOException {

        List<PokemonDto> pokemons = pokemonService.getPokemonDetailList(page).stream()
                .map(p -> new PokemonDto(p.getId(), p.getName(), p.getImgUrl() != null ? p.getImgUrl() : null))
                .collect(Collectors.toList());

        model.addAttribute("pokemons", pokemons);
        model.addAttribute("current", page);
        model.addAttribute("next", page + 1);
        model.addAttribute("prev", page - 1);

        return "list";
    }

    @RequestMapping(value = "/pokemon/detail/{id}", method = RequestMethod.GET)
    public String getPokemonDetail(Model model, @PathVariable(value = "id") String id) throws IOException {
        PokemonResponse pokemonResponse = this.pokemonService.getPokemonInformation(id);
        List<PokemonDetailDto> pokemonEvolutions = pokemonService.getEvolutionListByPokeId(id)
                .stream()
                .map(mapper::pokemonDetailToDto)
                .collect(Collectors.toList());

        PokemonDetailDto pokemon = new PokemonDetailDto();
        pokemon.setUrlImage(pokemonResponse.getSprites().getOther().getDream_world().getFront_default());
        pokemon.setName(pokemonResponse.getName());
        pokemon.setTypes(pokemonResponse.getTypes().stream()
                                                   .map(t->t.getType())
                                                   .collect(Collectors.toList()));
        pokemon.setAbilities(pokemonResponse.getAbilities().stream()
                                                            .map(a->a.getAbility())
                                                            .collect(Collectors.toList()));
        pokemon.setMainType(pokemonResponse.getTypes().stream()
                                                       .map(t->t.getType().getName())
                                                       .findFirst().get());

        pokemon.setHeight(pokemonResponse.getHeight()/10);
        pokemon.setWeight(pokemonResponse.getWeight()/10);
        pokemon.setBaseExperience(pokemonResponse.getBase_experience());
        model.addAttribute("pokemon", pokemon);
        model.addAttribute("pokemonEvolutions", pokemonEvolutions);

        return "detail";
    }

    @GetMapping("/evolution/list/{pokemonId}")
    public List<PokemonDetailDto> getEvolutionListByPokeId(@PathVariable("pokemonId") String pokemonId) throws IOException {
        return pokemonService.getEvolutionListByPokeId(pokemonId)
                .stream()
                .map(mapper::pokemonDetailToDto)
                .collect(Collectors.toList());
    }
}
