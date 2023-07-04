package com.thunder.light.service.city;


import com.thunder.light.database.entity.CityEntity;
import com.thunder.light.database.repository.CityRepository;
import com.thunder.light.error.exception.CityNotFoundException;
import com.thunder.light.mapper.CityMapper;
import com.thunder.light.model.City;
import lombok.extern.slf4j.Slf4j;
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
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    public CityServiceImpl(CityRepository cityRepository, CityMapper cityMapper) {
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
    }


    @Override
    public City getById(@Min(1) long id) {
        try {
            CityEntity cityEntity = cityRepository.findById(id)
                    .orElseThrow(() -> new CityNotFoundException(id));
            return cityMapper.toModel(cityEntity);
        } catch (Exception e) {
            log.info("\nThe exception '{}' was thrown for fetching city with id {}", e.getMessage(), id);
            throw new CityNotFoundException(id);
        }
    }

    @Override
    public Page<City> getAllByPaging(@Valid @NotNull Pageable pageable) {
        try {
            Page<CityEntity> cityEntityPage = cityRepository.findAll(pageable);

            if (cityEntityPage.isEmpty()) {
                throw new CityNotFoundException();
            }
            List<City> cityList = new ArrayList<>();
            cityEntityPage.forEach(cityEntity -> cityList.add(cityMapper.toModel(cityEntity)));
            return new PageImpl<>(cityList);
        } catch (Exception e) {
            log.info("\nThe exception '{}' was thrown for fetching all cities ", e.getMessage());
            throw new CityNotFoundException();
        }
    }
}
