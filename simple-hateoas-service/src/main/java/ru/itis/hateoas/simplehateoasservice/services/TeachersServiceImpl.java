package ru.itis.hateoas.simplehateoasservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.hateoas.simplehateoasservice.models.Teacher;
import ru.itis.hateoas.simplehateoasservice.repositories.TeachersRepository;

@Service
public class TeachersServiceImpl implements TeachersService{

    @Autowired
    private TeachersRepository teachersRepository;

    @Override
    public Teacher suggest(Long teacherId) {
        Teacher teacher = teachersRepository.findById(teacherId).orElseThrow(IllegalArgumentException::new);
        teacher.suggest();
        teachersRepository.save(teacher);
        return teacher;
    }

    @Override
    public Teacher approve(Long teacherId) {
        Teacher teacher = teachersRepository.findById(teacherId).orElseThrow(IllegalArgumentException::new);
        teacher.approve();
        teachersRepository.save(teacher);
        return teacher;
    }

    @Override
    public Teacher decline(Long teacherId) {
        Teacher teacher = teachersRepository.findById(teacherId).orElseThrow(IllegalArgumentException::new);
        teacher.decline();
        teachersRepository.save(teacher);
        return teacher;
    }
}
