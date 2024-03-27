package baranov.viacheslav.graduateworkcloudstorage.service;

import baranov.viacheslav.graduateworkcloudstorage.model.entity.StorageFile;
import baranov.viacheslav.graduateworkcloudstorage.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {
    private static final String DESC = "Description";
    private final FileRepository fileRepository;

    private StorageFile convertToStorageFile(MultipartFile multipartFile) {
        StorageFile storageFile;
        try {
            storageFile = StorageFile.builder()
                    .filename(multipartFile.getOriginalFilename())
                    .size(multipartFile.getSize())
                    .bytes(multipartFile.getBytes())
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return storageFile;
    }

    @Transactional
    public ResponseEntity<?> uploadFile(MultipartFile multipartFile) {
        StorageFile newStorageFile = convertToStorageFile(multipartFile);
        String filename = newStorageFile.getFilename();
        if (fileRepository.existsByFilename(filename)) {
            return ResponseEntity.badRequest().header(DESC, "This filename already exists").build();
        }
        fileRepository.save(newStorageFile);
        return ResponseEntity.ok().header(DESC, "Success upload").build();
    }

    @Transactional
    public ResponseEntity<?> deleteFile(String filename) {
        if (!fileRepository.existsByFilename(filename)) {
            return ResponseEntity.badRequest().header(DESC, "File does not exists").build();
        }
        fileRepository.deleteFileByFilename(filename);
        return ResponseEntity.ok().header(DESC, "Success delete").build();
    }

    @Transactional
    public ResponseEntity<?> downloadFile(String filename) {
        StorageFile storageFile = fileRepository.getFileByFilename(filename).orElse(null);
        if (storageFile == null) {
            return ResponseEntity.badRequest().header(DESC, "File does not exists").build();
        }
        return ResponseEntity.ok().header(DESC, "Success download").body(storageFile.getBytes());
    }

    @Transactional
    public ResponseEntity<?> updateFile(String filename, StorageFile newStorageFile) {
        StorageFile storageFile = fileRepository.getFileByFilename(filename).orElse(null);
        if (storageFile == null) {
            return ResponseEntity.badRequest().header(DESC, "File does not exists").build();
        }
        storageFile.setFilename(newStorageFile.getFilename());
        fileRepository.save(storageFile);
        return ResponseEntity.ok().header(DESC, "Success update").build();
    }

    @Transactional
    public ResponseEntity<List<StorageFile>> getAllFiles(int limit) {
        PageRequest pageRequest = PageRequest.of(0, limit);
        List<StorageFile> listOfFiles = fileRepository.findAll(pageRequest).getContent();
        return ResponseEntity.ok().header(DESC, "Success get list").body(listOfFiles);
    }
}