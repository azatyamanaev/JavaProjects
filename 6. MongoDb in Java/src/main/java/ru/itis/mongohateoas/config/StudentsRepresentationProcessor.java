package ru.itis.mongohateoas.config;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.mongohateoas.controllers.StudentsController;
import ru.itis.mongohateoas.models.Student;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class StudentsRepresentationProcessor implements RepresentationModelProcessor<EntityModel<Student>>  {

    @Override
    public EntityModel<Student> process(EntityModel<Student> model) {
        Student student = model.getContent();
        if (student != null && (student.getState().equals("Enrollee") || student.getState().equals("Expelled"))) {
            model.add(linkTo(methodOn(StudentsController.class)
                    .enroll(student.get_id())).withRel("enroll"));
        }

        if (student!= null && student.getState().equals("Undergraduate")) {
            model.add(linkTo(methodOn(StudentsController.class)
                    .expell(student.get_id())).withRel("expell"));
        }

        return model;
    }
}
