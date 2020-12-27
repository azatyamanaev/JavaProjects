package ru.itis.mongohateoas.services;


import ru.itis.mongohateoas.models.Student;

public interface StudentsService {
    Student enroll(String studentId);
    Student expell(String studentId);
}
