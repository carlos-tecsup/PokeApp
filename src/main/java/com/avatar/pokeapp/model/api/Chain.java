package com.avatar.pokeapp.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class Chain {
    private Specie species;
    private List<EvolvesTo> evolves_to;
}
