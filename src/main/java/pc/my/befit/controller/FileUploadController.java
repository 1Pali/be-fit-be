package pc.my.befit.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import pc.my.befit.api.dto.FileDto;
import pc.my.befit.api.dto.AddFileDto;
import pc.my.befit.api.dto.RemovedItemDto;
import pc.my.befit.api.dto.UpdateFileDto;
import pc.my.befit.api.enumeration.DeletionStatus;
import pc.my.befit.api.service.FileService;
import pc.my.befit.exception.ElementDoesNotExistException;
import pc.my.befit.model.entity.File;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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