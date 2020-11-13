package ru.itis.hateoas.simplehateoasservice.services;

import ru.itis.hateoas.simplehateoasservice.models.Student;

public interface StudentsService {
    Student enroll(Long studentId);
    Student expell(Long studentId);
    Student increase(Long studentId);
    Student reduce(Long studentId);
    Student give(Long studentId);
    Student deprive(Long studentId);
}
