package com.avatar.pokeapp.mapper;

import com.avatar.pokeapp.model.api.Chain;
import com.avatar.pokeapp.model.response.ChainResponse;
import org.mapstruct.Mapper;

@Mapper
public interface ChainMapper {
    Chain toModel(ChainResponse chainResponse);
}
