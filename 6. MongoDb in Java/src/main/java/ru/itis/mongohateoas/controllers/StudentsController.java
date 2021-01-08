package ru.itis.mongohateoas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.mongohateoas.services.StudentsService;

@RepositoryRestController
public class StudentsController {
    @Autowired
    private StudentsService studentsService;

    @RequestMapping(value = "/students/{student-id}/enroll", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<?> enroll(@PathVariable("student-id") String studentId) {
        return ResponseEntity.ok(
                EntityModel.of(studentsService.enroll(studentId)));
    }

    @RequestMapping(value = "/students/{student-id}/expell", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<?> expell(@PathVariable("student-id") String studentId) {
        return ResponseEntity.ok(
                EntityModel.of(studentsService.expell(studentId)));
    }

}
