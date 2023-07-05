package com.thunder.light.service.city;


import com.thunder.light.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Validated
public interface CityService {

    City getById(@Min(1) long id);

    Page<City> getAllByPaging(@Valid @NotNull Pageable pageable);

}
