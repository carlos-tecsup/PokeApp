package com.avatar.pokeapp.model.api;

import lombok.Data;

import java.util.List;

@Data
public class EvolvesTo {
    private List<EvolvesTo> evolves_to;
    private Specie species;
}
