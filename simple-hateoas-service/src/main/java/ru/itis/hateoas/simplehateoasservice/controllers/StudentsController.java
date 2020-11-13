package ru.itis.hateoas.simplehateoasservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.hateoas.simplehateoasservice.services.StudentsService;

@RepositoryRestController
public class StudentsController {
    @Autowired
    private StudentsService studentsService;

    @RequestMapping(value = "/students/{student-id}/enroll", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<?> enroll(@PathVariable("student-id") Long studentId) {
        return ResponseEntity.ok(
                EntityModel.of(studentsService.enroll(studentId)));
    }

    @RequestMapping(value = "/students/{student-id}/expell", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<?> expell(@PathVariable("student-id") Long studentId) {
        return ResponseEntity.ok(
                EntityModel.of(studentsService.expell(studentId)));
    }

    @RequestMapping(value = "/students/{student-id}/increase", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<?> increase(@PathVariable("student-id") Long studentId) {
        return ResponseEntity.ok(
                EntityModel.of(studentsService.increase(studentId)));
    }

    @RequestMapping(value = "/students/{student-id}/reduce", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<?> reduce(@PathVariable("student-id") Long studentId) {
        return ResponseEntity.ok(
                EntityModel.of(studentsService.reduce(studentId)));
    }

    @RequestMapping(value = "/students/{student-id}/give", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<?> give(@PathVariable("student-id") Long studentId) {
        return ResponseEntity.ok(
                EntityModel.of(studentsService.give(studentId)));
    }

    @RequestMapping(value = "/students/{student-id}/deprive", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<?> deprive(@PathVariable("student-id") Long studentId) {
        return ResponseEntity.ok(
                EntityModel.of(studentsService.deprive(studentId)));
    }

}
