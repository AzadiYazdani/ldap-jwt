package com.thunder.light.service.state;


import com.thunder.light.model.City;
import com.thunder.light.model.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
public interface StateService {

    State getById(@Min(1) long id);

    Page<State> getAllByPaging(@Valid @NotNull Pageable pageable);

    List<City> getAllCommentsById(@Min(1) long postId);

    List<State> searchTitle(@NotNull String title);

}
