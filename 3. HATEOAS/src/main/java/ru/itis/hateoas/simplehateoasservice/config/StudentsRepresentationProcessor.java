package ru.itis.hateoas.simplehateoasservice.config;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.hateoas.simplehateoasservice.controllers.StudentsController;
import ru.itis.hateoas.simplehateoasservice.models.Student;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class StudentsRepresentationProcessor implements RepresentationModelProcessor<EntityModel<Student>>  {

    @Override
    public EntityModel<Student> process(EntityModel<Student> model) {
        Student student = model.getContent();
        if (student != null && (student.getState().equals("Enrollee") || student.getState().equals("Expelled"))) {
            model.add(linkTo(methodOn(StudentsController.class)
                    .enroll(student.getId())).withRel("enroll"));
        }

        if (student!= null && student.getState().equals("Undergraduate")) {
            model.add(linkTo(methodOn(StudentsController.class)
                    .expell(student.getId())).withRel("expell"));
        }

        if (student != null && student.getScholarship().equals("Normal")) {
            model.add(linkTo(methodOn(StudentsController.class)
            .increase(student.getId())).withRel("increase"));
            model.add(linkTo(methodOn(StudentsController.class)
                    .deprive(student.getId())).withRel("deprive"));
        }

        if (student != null && student.getScholarship().equals("Absent")) {
            model.add(linkTo(methodOn(StudentsController.class)
                    .give(student.getId())).withRel("give"));
        }

        if (student != null && student.getScholarship().equals("Increased")) {
            model.add(linkTo(methodOn(StudentsController.class)
                    .reduce(student.getId())).withRel("reduce"));
        }

        return model;
    }
}
