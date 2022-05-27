package com.avatar.pokeapp.model.response;

import lombok.Data;

import java.util.List;

@Data
public class PokemonListResponse {
    private Integer count;
    private String next;
    private String previous;
    private List<ResultResponse> results;
}
