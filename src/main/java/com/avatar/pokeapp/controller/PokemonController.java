package com.avatar.pokeapp.controller;

import com.avatar.pokeapp.model.dto.PokemonDto;
import com.avatar.pokeapp.service.PokemonService;
import lombok.RequiredArgsConstructor;
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

    @RequestMapping(value = "/pokemons/{page}", method = RequestMethod.GET)
    public String getAllPokemonDetail(Model model, @PathVariable(value="page") int page) throws IOException {

        List<PokemonDto> pokemons = pokemonService.getPokemonDetailList(page).stream()
                .map(p -> new PokemonDto(p.getName(), p.getImgUrl() != null ? p.getImgUrl() : null))
                .collect(Collectors.toList());

        model.addAttribute("pokemons", pokemons);
        model.addAttribute("current", page);
        model.addAttribute("next", page + 1);
        model.addAttribute("prev", page - 1);
        return "list";
    }

}
