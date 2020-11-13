package ru.itis.hateoas.simplehateoasservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.hateoas.simplehateoasservice.models.Course;
import ru.itis.hateoas.simplehateoasservice.repositories.CoursesRepository;

@Service
public class CoursesServiceImpl implements CoursesService{
    @Autowired
    private CoursesRepository coursesRepository;

    @Override
    public Course publish(Long courseId) {
        Course course = coursesRepository.findById(courseId).orElseThrow(IllegalArgumentException::new);
        course.publish();
        coursesRepository.save(course);
        return course;
    }

    @Override
    public Course increaseCount(Long courseId) {
        Course course = coursesRepository.findById(courseId).orElseThrow(IllegalArgumentException::new);
        course.increaseCount();
        coursesRepository.save(course);
        return course;
    }

    @Override
    public Course decreaseCount(Long courseId) {
        Course course = coursesRepository.findById(courseId).orElseThrow(IllegalArgumentException::new);
        course.decreaseCount();
        coursesRepository.save(course);
        return course;
    }
}
