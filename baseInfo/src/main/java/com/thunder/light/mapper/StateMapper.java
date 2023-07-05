package com.thunder.light.mapper;


import com.thunder.light.api.location.state.StateRequestDto;
import com.thunder.light.api.location.state.StateResponseDto;
import com.thunder.light.database.entity.StateEntity;
import com.thunder.light.model.State;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public abstract class StateMapper {

    @Mapping(target = "id", ignore = true)
    public abstract State toModel(StateRequestDto requestDto);
    
    public abstract State toModel(StateEntity entity);

    public abstract List<State> toModelList(List<StateEntity> stateEntityList);

    public abstract StateResponseDto toDtoResponse(State entity);

    public abstract List<StateResponseDto> toDtoResponseList(List<State> stateList);

}
