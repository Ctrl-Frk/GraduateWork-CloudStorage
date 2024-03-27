package baranov.viacheslav.graduateworkcloudstorage.repository;


import baranov.viacheslav.graduateworkcloudstorage.model.entity.StorageFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<StorageFile, Long> {
    Optional<StorageFile> getFileByFilename(String filename);

    Boolean existsByFilename(String filename);

    void deleteFileByFilename(String filename);
}