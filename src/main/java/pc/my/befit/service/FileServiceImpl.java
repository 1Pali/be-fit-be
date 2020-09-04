package pc.my.befit.service;

import com.googlecode.jmapper.JMapper;
import org.springframework.stereotype.Service;
import pc.my.befit.Repository.FileRepository;

import pc.my.befit.api.dto.*;
import pc.my.befit.api.service.FileService;

import pc.my.befit.exception.ElementDoesNotExistException;
import pc.my.befit.model.entity.File;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService {
    private static final String FILE_EXCEPTION_MESSAGE = "File with id: %s does not exist.";
    private static final JMapper<FileDto, File> FILE_DTO_MAPPER = new JMapper<>(FileDto.class, File.class);

    private final FileRepository fileRepository;

    public FileServiceImpl(final FileRepository fileRepository) {

        this.fileRepository = fileRepository;
    }

    @Override
    @Transactional
    public FileDto getById(Long id) {
        return fileRepository.findById(id)
                .map(FILE_DTO_MAPPER::getDestination)
                .orElseThrow(() -> new ElementDoesNotExistException(
                        String.format(FILE_EXCEPTION_MESSAGE, id)
                ));
    }

    @Override
    @Transactional
    public List<FileDto> getList() {
        return fileRepository.findAll()
                .stream().map(FILE_DTO_MAPPER::getDestination)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        fileRepository.findById(id)
                .orElseThrow(() -> new ElementDoesNotExistException(
                        String.format(FILE_EXCEPTION_MESSAGE, id)));

        fileRepository.deleteById(id);
    }

    @Override
    @Transactional
    public FileDto update(Long fileId, UpdateFileDto dto) {
        File existingFile = fileRepository.findById(fileId)
                .orElseThrow(() -> new ElementDoesNotExistException(
                        String.format(FILE_EXCEPTION_MESSAGE, fileId)));

        existingFile.setName(dto.getName());
        existingFile.setFile(dto.getFile());

        return FILE_DTO_MAPPER.getDestination(fileRepository.save(existingFile));
    }

    @Override
    @Transactional
    public FileDto create(AddFileDto dto) {
        File file = new File();
        file.setName(dto.getName());
        file.setFile(dto.getFile());

        return FILE_DTO_MAPPER.getDestination(fileRepository.save(file));
    }
}
