package com.pepa.befit.be_fit_be.controller;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.pepa.befit.be_fit_be.api.dto.AddFileDto;
import com.pepa.befit.be_fit_be.api.dto.FileDto;
import com.pepa.befit.be_fit_be.api.dto.RemovedItemDto;
import com.pepa.befit.be_fit_be.api.dto.UpdateFileDto;
import com.pepa.befit.be_fit_be.api.enumeration.DeletionStatus;
import com.pepa.befit.be_fit_be.api.service.FileService;
import com.pepa.befit.be_fit_be.exception.ElementDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Validated
@RequestMapping("/api/v1/file")
public class FileUploadController {

    private final FileService fileService;

    @Autowired
    public FileUploadController(final FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FileDto>> getListOfFiles() {
        return ResponseEntity.ok(fileService.getList());
    }

    @GetMapping(value = "/{fileid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FileDto> getFileById(@PathVariable("fileid") @NotNull Long fileId) {
        return ResponseEntity.ok(fileService.getById(fileId));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FileDto> createFile(@NotNull @RequestParam("file") MultipartFile file) {
        FileDto newFile = null;
        try {
            AddFileDto addFile = new AddFileDto();
            addFile.setFile(file.getBytes());
            newFile = fileService.create(addFile);
        } catch (IOException ioe) {

        }
        return ResponseEntity.status(HttpStatus.CREATED).body(newFile);
    }

    @PutMapping(value = "/{fileid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FileDto> updateFile(@PathVariable("fileid") @NotNull Long fileId,
                                                 @RequestBody UpdateFileDto dto) {

        return ResponseEntity.accepted().body(fileService.update(fileId, dto));
    }

    @DeleteMapping(value = "/{fileid}")
    public ResponseEntity<Void> deleteFile(@PathVariable("fileIid") @NotNull Long fileId) {
        fileService.delete(fileId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/deletelist", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteFileList(@RequestBody List<Long> fileIdList) {
        if (fileIdList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Empty list of Files Ids received");
        }

        List<RemovedItemDto> deletedFileList = new ArrayList<>();

        for (Long fileId : fileIdList) {
            try {
                fileService.delete(fileId);
                deletedFileList.add(new RemovedItemDto(fileId, DeletionStatus.SUCCESS, null));
            } catch (ElementDoesNotExistException e) {
//                log.error(e.getMessage(), e);
                deletedFileList.add(new RemovedItemDto(fileId, DeletionStatus.ERROR, e.getMessage()));
            }
        }
        return ResponseEntity.ok(new Gson().toJson(deletedFileList));
    }

}