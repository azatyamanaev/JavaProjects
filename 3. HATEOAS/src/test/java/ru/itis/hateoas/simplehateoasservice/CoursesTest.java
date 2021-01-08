package ru.itis.hateoas.simplehateoasservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.hateoas.simplehateoasservice.models.Course;
import ru.itis.hateoas.simplehateoasservice.services.CoursesService;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class CoursesTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CoursesService coursesService;

    @BeforeEach
    public void setUp() {
        when(coursesService.publish(1L)).thenReturn(publishedCourse());
        when(coursesService.increaseCount(1L)).thenReturn(publishedCourse());
        when(coursesService.decreaseCount(2L)).thenReturn(decreasedCourse());
    }

    @Test
    public void coursePublishTest() throws Exception {
        mockMvc.perform(put("/courses/1/publish")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(publishedCourse().getTitle()))
                .andExpect(jsonPath("$.description").value(publishedCourse().getDescription()))
                .andExpect(jsonPath("$.state").value(publishedCourse().getState()))
                .andExpect(jsonPath("$.count").value(publishedCourse().getCount()))
                .andDo(document("publish_course", responseFields(
                        fieldWithPath("title").description("Название курса"),
                        fieldWithPath("description").description("Описание курса"),
                        fieldWithPath("state").description("Состояние курса"),
                        fieldWithPath("count").description("Количество")
                )));
    }

    @Test
    public void courseIncreaseCountTest() throws Exception {
        mockMvc.perform(put("/courses/1/increase_count")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(publishedCourse().getTitle()))
                .andExpect(jsonPath("$.description").value(publishedCourse().getDescription()))
                .andExpect(jsonPath("$.count").value(publishedCourse().getCount()))
                .andDo(document("course_increase_count", responseFields(
                        fieldWithPath("title").description("Название курса"),
                        fieldWithPath("description").description("Описание курса"),
                        fieldWithPath("count").description("Количество")
                )));
    }

    @Test
    public void courseDecreaseCountTest() throws Exception {
        mockMvc.perform(put("/courses/2/decrease_count")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(decreasedCourse().getTitle()))
                .andExpect(jsonPath("$.description").value(decreasedCourse().getDescription()))
                .andExpect(jsonPath("$.count").value(decreasedCourse().getCount()))
                .andDo(document("course_decrease_count", responseFields(
                        fieldWithPath("title").description("Название курса"),
                        fieldWithPath("description").description("Описание курса"),
                        fieldWithPath("count").description("Количество")
                )));
    }



    private Course publishedCourse() {
        return Course.builder()
                .id(1L)
                .count(2)
                .description("Deep in Java")
                .state("PUBLISHED")
                .title("JAVA")
                .build();
    }

    private Course decreasedCourse() {
        return Course.builder()
                .id(2L)
                .count(0)
                .description("Data processing")
                .state("DRAFT")
                .title("DataLab")
                .build();
    }
}
