package ru.itis.hateoas.simplehateoasservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hateoas.simplehateoasservice.models.StudentGroup;

public interface GroupsRepository extends JpaRepository<StudentGroup, Long> {
}
