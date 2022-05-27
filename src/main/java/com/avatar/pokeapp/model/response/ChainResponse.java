package com.avatar.pokeapp.model.response;

import lombok.Data;

import java.util.List;

@Data
public class ChainResponse {
    private List<EvolvesToResponse> evolves_to;

}
