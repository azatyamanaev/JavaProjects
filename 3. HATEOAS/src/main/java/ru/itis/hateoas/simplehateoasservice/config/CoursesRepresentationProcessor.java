package ru.itis.hateoas.simplehateoasservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.hateoas.simplehateoasservice.controllers.CoursesController;
import ru.itis.hateoas.simplehateoasservice.models.Course;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CoursesRepresentationProcessor implements RepresentationModelProcessor<EntityModel<Course>> {
    @Autowired
    private RepositoryEntityLinks links;

    @Override
    public EntityModel<Course> process(EntityModel<Course> model) {
        Course course = model.getContent();
        if (course != null && course.getState().equals("Draft")) {
            model.add(linkTo(methodOn(CoursesController.class)
                    .publish(course.getId())).withRel("publish"));
        }

        if (course!= null && course.getState().equals("Published")) {
            model.add(links.linkToItemResource(Course.class, course.getId()).
                    withRel("delete"));
        }

        if (course != null && (course.getCount() == 0 || course.getCount() == 1 || course.getCount() == 2)) {
            model.add(linkTo(methodOn(CoursesController.class)
                    .increaseCount(course.getId())).withRel("increase_count"));
        }

        if (course != null && (course.getCount() == 1 || course.getCount() == 2 || course.getCount() == 3)) {
            model.add(linkTo(methodOn(CoursesController.class)
                    .decreaseCount(course.getId())).withRel("decrease_count"));
        }

        return model;
    }
}
