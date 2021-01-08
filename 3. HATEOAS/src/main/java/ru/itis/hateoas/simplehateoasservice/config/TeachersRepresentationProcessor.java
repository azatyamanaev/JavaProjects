package ru.itis.hateoas.simplehateoasservice.config;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.hateoas.simplehateoasservice.controllers.StudentsController;
import ru.itis.hateoas.simplehateoasservice.controllers.TeachersController;
import ru.itis.hateoas.simplehateoasservice.models.Teacher;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TeachersRepresentationProcessor implements RepresentationModelProcessor<EntityModel<Teacher>> {
    @Override
    public EntityModel<Teacher> process(EntityModel<Teacher> model) {
        Teacher teacher = model.getContent();
        if (teacher != null && teacher.getCurrent().equals("Normal")) {
            model.add(linkTo(methodOn(TeachersController.class)
                    .suggest(teacher.getId())).withRel("suggest"));
        }
        if (teacher != null && teacher.getCurrent().equals("Suggested")) {
            model.add(linkTo(methodOn(TeachersController.class)
                    .approve(teacher.getId())).withRel("approve"));
            model.add(linkTo(methodOn(TeachersController.class)
                    .decline(teacher.getId())).withRel("decline"));
        }

        return model;
    }
}
