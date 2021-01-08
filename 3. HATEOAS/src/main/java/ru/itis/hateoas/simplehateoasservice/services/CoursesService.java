package ru.itis.hateoas.simplehateoasservice.services;

import ru.itis.hateoas.simplehateoasservice.models.Course;

public interface CoursesService {
    Course publish(Long courseId);
    Course increaseCount(Long courseId);
    Course decreaseCount(Long courseId);
}
