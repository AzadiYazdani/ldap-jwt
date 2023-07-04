package com.thunder.light.api;

import com.thunder.light.mapper.UploadedMultipartFileMapper;
import com.thunder.light.model.ThunderUploadedMultipartFile;
import com.thunder.light.model.dto.UploadedMultipartFileDto;
import com.thunder.light.util.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/file")
@Slf4j
public class UploadController {

    private final UploadedMultipartFileMapper uploadedMultipartFileMapper;

    public UploadController(UploadedMultipartFileMapper uploadedMultipartFileMapper) {
        this.uploadedMultipartFileMapper = uploadedMultipartFileMapper;
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<?> handleFileUpload( @RequestBody UploadedMultipartFileDto fileDto ) {
        log.info("upload file");

        try {
            ThunderUploadedMultipartFile file = uploadedMultipartFileMapper.toModel(fileDto);
            FileUploadUtil.saveFile(file, fileDto.getAttributes());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok("File uploaded successfully.");
    }
}
