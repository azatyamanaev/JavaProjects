package ru.itis.hateoas.simplehateoasservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.hateoas.simplehateoasservice.models.Student;
import ru.itis.hateoas.simplehateoasservice.repositories.StudentsRepository;

@Service
public class StudentsServiceImpl implements StudentsService{

    @Autowired
    private StudentsRepository studentsRepository;

    @Override
    public Student enroll(Long studentId) {
        Student student = studentsRepository.findById(studentId).orElseThrow(IllegalArgumentException::new);
        student.enroll();
        studentsRepository.save(student);
        return student;
    }

    @Override
    public Student expell(Long studentId) {
        Student student = studentsRepository.findById(studentId).orElseThrow(IllegalArgumentException::new);
        student.expell();
        studentsRepository.save(student);
        return student;
    }

    @Override
    public Student increase(Long studentId) {
        Student student = studentsRepository.findById(studentId).orElseThrow(IllegalArgumentException::new);
        student.increase();
        studentsRepository.save(student);
        return student;
    }

    @Override
    public Student reduce(Long studentId) {
        Student student = studentsRepository.findById(studentId).orElseThrow(IllegalArgumentException::new);
        student.reduce();
        studentsRepository.save(student);
        return student;
    }

    @Override
    public Student give(Long studentId) {
        Student student = studentsRepository.findById(studentId).orElseThrow(IllegalArgumentException::new);
        student.give();
        studentsRepository.save(student);
        return student;
    }

    @Override
    public Student deprive(Long studentId) {
        Student student = studentsRepository.findById(studentId).orElseThrow(IllegalArgumentException::new);
        student.deprive();
        studentsRepository.save(student);
        return student;
    }

}
