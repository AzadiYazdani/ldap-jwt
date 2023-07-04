package com.thunder.light.mapper;


import com.thunder.light.model.ThunderUploadedMultipartFile;
import com.thunder.light.model.dto.UploadedMultipartFileDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public abstract class UploadedMultipartFileMapper {
    public abstract ThunderUploadedMultipartFile toModel(UploadedMultipartFileDto dto);
}
