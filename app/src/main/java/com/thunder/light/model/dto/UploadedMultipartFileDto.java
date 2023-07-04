package com.thunder.light.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UploadedMultipartFileDto implements Serializable {

    private byte[] bytes;
    private String contentType;
    private String formParameterName;
    private String originalFilename;
    private Map<String, String> attributes;

}
