package ru.itis.mongohateoas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.mongohateoas.models.Student;
import ru.itis.mongohateoas.repositories.StudentsRepository;

@Service
public class StudentsServiceImpl implements StudentsService {

    @Autowired
    private StudentsRepository studentsRepository;

    @Override
    public Student enroll(String studentId) {
        Student student = studentsRepository.findById(studentId).orElseThrow(IllegalArgumentException::new);
        student.enroll();
        studentsRepository.save(student);
        return student;
    }

    @Override
    public Student expell(String studentId) {
        Student student = studentsRepository.findById(studentId).orElseThrow(IllegalArgumentException::new);
        student.expell();
        studentsRepository.save(student);
        return student;
    }

}
