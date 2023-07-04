package com.thunder.light.mapper;


import com.thunder.light.api.location.city.CityResponseDto;
import com.thunder.light.database.entity.CityEntity;
import com.thunder.light.model.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public abstract class CityMapper {

    @Mapping(target = "stateId", source = "entity.state.id")
    public abstract City toModel(CityEntity entity);

    public abstract List<City> toModelList(List<CityEntity> postEntityList);

    public abstract List<CityResponseDto> toDtoResponseList(List<City> postList);

}
