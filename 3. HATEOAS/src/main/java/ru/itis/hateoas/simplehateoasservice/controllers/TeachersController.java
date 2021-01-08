package ru.itis.hateoas.simplehateoasservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.hateoas.simplehateoasservice.services.TeachersService;

@RepositoryRestController
public class TeachersController {
    @Autowired
    private TeachersService teachersService;

    @RequestMapping(value = "/teachers/{teacher-id}/suggest", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<?> suggest(@PathVariable("teacher-id") Long teacherId) {
        return ResponseEntity.ok(
                EntityModel.of(teachersService.suggest(teacherId))
        );
    }

    @RequestMapping(value = "/teachers/{teacher-id}/approve", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<?> approve(@PathVariable("teacher-id") Long teacherId) {
        return ResponseEntity.ok(
                EntityModel.of(teachersService.approve(teacherId))
        );
    }

    @RequestMapping(value = "/teachers/{teacher-id}/decline", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<?> decline(@PathVariable("teacher-id") Long teacherId) {
        return ResponseEntity.ok(
                EntityModel.of(teachersService.decline(teacherId))
        );
    }

}
