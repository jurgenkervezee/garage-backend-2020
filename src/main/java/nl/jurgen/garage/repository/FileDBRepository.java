package nl.jurgen.garage.repository;

import nl.jurgen.garage.model.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileDBRepository extends JpaRepository<FileDB, String> {
}
