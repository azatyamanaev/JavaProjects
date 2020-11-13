package ru.itis.hateoas.simplehateoasservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.hateoas.simplehateoasservice.services.CoursesService;

@RepositoryRestController
public class CoursesController {
    @Autowired
    private CoursesService coursesService;

    @RequestMapping(value = "/courses/{course-id}/publish", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<?> publish(@PathVariable("course-id") Long courseId) {
        return ResponseEntity.ok(
                EntityModel.of(coursesService.publish(courseId)));
    }

    @RequestMapping(value = "/courses/{course-id}/increase_count", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<?> increaseCount(@PathVariable("course-id") Long courseId) {
        return ResponseEntity.ok(
                EntityModel.of(coursesService.increaseCount(courseId)));
    }

    @RequestMapping(value = "/courses/{course-id}/decrease_count", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<?> decreaseCount(@PathVariable("course-id") Long courseId) {
        return ResponseEntity.ok(
                EntityModel.of(coursesService.decreaseCount(courseId)));
    }


}
