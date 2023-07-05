package com.thunder.light.api.location;


import com.thunder.light.api.ResponseDto;
import com.thunder.light.api.common.PageRequestDto;
import com.thunder.light.api.location.city.CityResponseDto;
import com.thunder.light.api.location.state.StateResponseDto;
import com.thunder.light.mapper.CityMapper;
import com.thunder.light.mapper.StateMapper;
import com.thunder.light.model.City;
import com.thunder.light.model.State;
import com.thunder.light.service.state.StateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Api(value = "Post operations")
@Slf4j
@Validated
public class LocationController {

    private final StateService stateService;
    private final StateMapper stateMapper;
    private final CityMapper cityMapper;

    public LocationController(StateService stateService, StateMapper stateMapper, CityMapper cityMapper) {
        this.stateService = stateService;
        this.stateMapper = stateMapper;
        this.cityMapper = cityMapper;
    }

    @GetMapping("/states")
    @ApiOperation(value = "Get all states with paging")
    public ResponseEntity<ResponseDto<Page<StateResponseDto>>> getAllByPaging(@RequestBody @Valid @NotNull PageRequestDto pageRequestDto) {
        log.debug("received pageRequestDto for retrieving all state is {}", pageRequestDto);
        Pageable pageable = PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getSize());
        Page<State> statePage = stateService.getAllByPaging(pageable);
        log.info("the statePage for sending is {}", statePage);
        return new ResponseEntity<ResponseDto<Page<StateResponseDto>>>(ResponseDto.success(statePage), HttpStatus.OK);
    }

    @GetMapping(value = "/states/{stateId}")
    @ApiOperation(value = "Find a state with given id")
    public ResponseEntity<ResponseDto<StateResponseDto>> getById(@PathVariable("stateId") @Valid @Min(1) long stateId) {
        log.debug("received stateId for retrieving Post is {}", stateId);
        State state = stateService.getById(stateId);
        StateResponseDto dtoResponse = stateMapper.toDtoResponse(state);
        log.info("the PostDto for sending is {}", dtoResponse);
        return new ResponseEntity<ResponseDto<StateResponseDto>>(ResponseDto.success(dtoResponse), HttpStatus.OK);
    }


    @GetMapping("/states/{stateId}/cities")
    @ApiOperation(value = "Find all cities of a state with given id of state")
    public ResponseEntity<ResponseDto<StateResponseDto>> getAllComments(@PathVariable("stateId") @Valid @Min(1) long stateId) {
        log.debug("received stateId for retrieving cities is {}", stateId);
        List<City> cities = stateService.getAllCommentsById(stateId);
        List<CityResponseDto> cityResponseDtoList = cityMapper.toDtoResponseList(cities);
        log.info("the list of cities for sending is {}", cityResponseDtoList);
        return new ResponseEntity<ResponseDto<StateResponseDto>>(ResponseDto.success(cityResponseDtoList), HttpStatus.OK);
    }

    @GetMapping("/states?")
    @ApiOperation(value = "Find all states with given part of name")
    public ResponseEntity<ResponseDto<List<StateResponseDto>>> search(@RequestParam("name") @Valid @NotNull String value) {
        log.debug("received value for searching name is {}", value);
        List<State> stateList = stateService.searchTitle(value);
        List<StateResponseDto> stateResponseDtoList = stateMapper.toDtoResponseList(stateList);
        log.info("the list of state for sending is {}", stateResponseDtoList);
        return new ResponseEntity<ResponseDto<List<StateResponseDto>>>(ResponseDto.success(stateResponseDtoList), HttpStatus.OK);
    }



}
