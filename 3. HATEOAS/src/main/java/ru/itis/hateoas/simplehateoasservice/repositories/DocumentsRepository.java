package ru.itis.hateoas.simplehateoasservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hateoas.simplehateoasservice.models.Document;

public interface DocumentsRepository extends JpaRepository<Document, Long> {
}
