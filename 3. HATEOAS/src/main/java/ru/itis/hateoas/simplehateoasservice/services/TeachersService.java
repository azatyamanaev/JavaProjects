package ru.itis.hateoas.simplehateoasservice.services;

import ru.itis.hateoas.simplehateoasservice.models.Teacher;

public interface TeachersService {
    Teacher suggest(Long teacherId);
    Teacher approve(Long teacherId);
    Teacher decline(Long teacherId);
}
