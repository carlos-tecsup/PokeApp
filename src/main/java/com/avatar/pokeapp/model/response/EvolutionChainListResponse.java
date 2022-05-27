package com.avatar.pokeapp.model.response;

import lombok.Data;

import java.util.List;

@Data
public class EvolutionChainListResponse {
    private List<ResultResponse> results;
}
