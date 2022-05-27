package com.avatar.pokeapp.model.response;

import lombok.Data;

import java.util.List;

@Data
public class EvolvesToResponse {
    private SpecieResponse species;
    private List<EvolvesToResponse> evolves_to;
}
