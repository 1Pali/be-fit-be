package com.pepa.befit.be_fit_be.api.service;

import com.pepa.befit.be_fit_be.api.dto.AddFileDto;
import com.pepa.befit.be_fit_be.api.dto.FileDto;
import com.pepa.befit.be_fit_be.api.dto.UpdateFileDto;
import com.pepa.befit.be_fit_be.model.entity.File;

import java.util.List;

public interface FileService {

    /**
     * Get file by id
     *
     * @param id Long of file
     * @return file with given id
     */
    FileDto getById(final Long id);

    /**
     * Get list of all file
     *
     * @return list of all file
     */
    List<FileDto> getList();

    /**
     * delete file by id
     *
     * @param id Long of file
     */
    void delete(final Long id);

    /**
     * Update {@link File} with given values from {@link UpdateFileDto}
     *
     * @param {@link UpdateFileDto}
     * @return updated file
     */
    FileDto update(final Long fileId, final UpdateFileDto dto);

    /**
     * Create {@link File} with given values from {@link AddFileDto}
     *
     * @param {@link UpdateFileDto}
     * @return created file
     */
    FileDto create(final AddFileDto dto);

}