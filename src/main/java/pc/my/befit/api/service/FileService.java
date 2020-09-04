package pc.my.befit.api.service;

import pc.my.befit.api.dto.AddFileDto;
import pc.my.befit.api.dto.FileDto;
import pc.my.befit.api.dto.UpdateFileDto;

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