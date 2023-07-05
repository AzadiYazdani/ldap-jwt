package com.thunder.light.service.state;


import com.thunder.light.database.entity.CityEntity;
import com.thunder.light.database.entity.StateEntity;
import com.thunder.light.database.repository.StateRepository;
import com.thunder.light.error.exception.BadRequestException;
import com.thunder.light.error.exception.CityNotFoundException;
import com.thunder.light.error.exception.StateNotFoundException;
import com.thunder.light.mapper.CityMapper;
import com.thunder.light.mapper.StateMapper;
import com.thunder.light.model.City;
import com.thunder.light.model.State;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Validated
public class StateServiceImpl implements StateService {

    private final StateRepository stateRepository;
    private final StateMapper stateMapper;
    private final CityMapper cityMapper;

    public StateServiceImpl(StateRepository stateRepository, StateMapper stateMapper, CityMapper cityMapper) {
        this.stateRepository = stateRepository;
        this.stateMapper = stateMapper;
        this.cityMapper = cityMapper;
    }


    @Override
    public State getById(@Min(1) long id) {
        try {
            StateEntity stateEntity = stateRepository.findById(id)
                    .orElseThrow(() -> new StateNotFoundException(id));
            return stateMapper.toModel(stateEntity);
        } catch (Exception e) {
            log.info("\nThe exception '{}' was thrown for fetching post with id {}", e.getMessage(), id);
            throw new StateNotFoundException(id);
        }
    }


    @Override
    public Page<State> getAllByPaging(@Valid @NotNull Pageable pageable) {
        try {
            Page<StateEntity> postEntityPage = stateRepository.findAll(pageable);

            if (postEntityPage.isEmpty()) {
                throw new StateNotFoundException();
            }
            List<State> stateList = new ArrayList<>();
            postEntityPage.forEach(p -> stateList.add(stateMapper.toModel(p)));
            return new PageImpl<>(stateList);
        } catch (Exception e) {
            log.info("\nThe exception '{}' was thrown for fetching all posts ", e.getMessage());
            throw new StateNotFoundException();
        }
    }


    @Override
    public List<City> getAllCommentsById(@Min(1) long postId) {
        try {
            StateEntity stateEntity = stateRepository.findById(postId)
                    .orElseThrow(() -> new StateNotFoundException(postId));
            List<CityEntity> commentEntities = stateEntity.getCities();

            return cityMapper.toModelList(commentEntities);
        } catch (Exception e) {
            log.info("\nThe exception '{}' was thrown for fetching comments of the post with id {}", e.getMessage(), postId);
            throw new CityNotFoundException(postId);
        }
    }

    @Override
    public List<State> searchTitle(@NotNull String titleValue) {
        if (StringUtils.isBlank(titleValue))
            throw new BadRequestException();

        List<StateEntity> stateEntityList = stateRepository.findAllByNameContains(titleValue);
        return stateMapper.toModelList(stateEntityList);
    }

}
