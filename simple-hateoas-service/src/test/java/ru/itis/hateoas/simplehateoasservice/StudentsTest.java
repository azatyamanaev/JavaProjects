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
import ru.itis.hateoas.simplehateoasservice.models.Student;
import ru.itis.hateoas.simplehateoasservice.services.CoursesService;
import ru.itis.hateoas.simplehateoasservice.services.StudentsService;

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
public class StudentsTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentsService studentsService;

    @BeforeEach
    public void setUp() {
        when(studentsService.enroll(1L)).thenReturn(enrolledStudent());
        when(studentsService.expell(2L)).thenReturn(expelledStudent());
        when(studentsService.give(1L)).thenReturn(enrolledStudent());
        when(studentsService.deprive(2L)).thenReturn(expelledStudent());
        when(studentsService.reduce(1L)).thenReturn(enrolledStudent());
        when(studentsService.increase(3L)).thenReturn(increasedScholarshipStudent());
    }

    @Test
    public void studentEnrollTest() throws Exception {
        mockMvc.perform(put("/students/1/enroll")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(enrolledStudent().getFirstName()))
                .andExpect(jsonPath("$.lastName").value(enrolledStudent().getLastName()))
                .andExpect(jsonPath("$.scholarship").value(enrolledStudent().getScholarship()))
                .andExpect(jsonPath("$.state").value(enrolledStudent().getState()))
                .andExpect(jsonPath("$.year").value(enrolledStudent().getYear()))
                .andExpect(jsonPath("$.age").value(enrolledStudent().getAge()))
                .andDo(document("enroll_student", responseFields(
                        fieldWithPath("firstName").description("Имя студента"),
                        fieldWithPath("lastName").description("Фамилия студента"),
                        fieldWithPath("scholarship").description("Стипендия студента"),
                        fieldWithPath("state").description("Состояние студента"),
                        fieldWithPath("year").description("Год обучения студента"),
                        fieldWithPath("age").description("Возраст студента")
                )));
    }

    @Test
    public void studentExpellTest() throws Exception {
        mockMvc.perform(put("/students/2/expell")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(expelledStudent().getFirstName()))
                .andExpect(jsonPath("$.lastName").value(expelledStudent().getLastName()))
                .andExpect(jsonPath("$.scholarship").value(expelledStudent().getScholarship()))
                .andExpect(jsonPath("$.state").value(expelledStudent().getState()))
                .andExpect(jsonPath("$.year").value(expelledStudent().getYear()))
                .andExpect(jsonPath("$.age").value(expelledStudent().getAge()))
                .andDo(document("expell_student", responseFields(
                        fieldWithPath("firstName").description("Имя студента"),
                        fieldWithPath("lastName").description("Фамилия студента"),
                        fieldWithPath("scholarship").description("Стипендия студента"),
                        fieldWithPath("state").description("Состояние студента"),
                        fieldWithPath("year").description("Год обучения студента"),
                        fieldWithPath("age").description("Возраст студента")
                )));
    }

    @Test
    public void studentGiveScholarshipTest() throws Exception {
        mockMvc.perform(put("/students/1/give")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(enrolledStudent().getFirstName()))
                .andExpect(jsonPath("$.lastName").value(enrolledStudent().getLastName()))
                .andExpect(jsonPath("$.scholarship").value(enrolledStudent().getScholarship()))
                .andExpect(jsonPath("$.state").value(enrolledStudent().getState()))
                .andExpect(jsonPath("$.year").value(enrolledStudent().getYear()))
                .andExpect(jsonPath("$.age").value(enrolledStudent().getAge()))
                .andDo(document("give_student", responseFields(
                        fieldWithPath("firstName").description("Имя студента"),
                        fieldWithPath("lastName").description("Фамилия студента"),
                        fieldWithPath("scholarship").description("Стипендия студента"),
                        fieldWithPath("state").description("Состояние студента"),
                        fieldWithPath("year").description("Год обучения студента"),
                        fieldWithPath("age").description("Возраст студента")
                )));
    }

    @Test
    public void studentDepriveScholarshipTest() throws Exception {
        mockMvc.perform(put("/students/2/deprive")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(expelledStudent().getFirstName()))
                .andExpect(jsonPath("$.lastName").value(expelledStudent().getLastName()))
                .andExpect(jsonPath("$.scholarship").value(expelledStudent().getScholarship()))
                .andExpect(jsonPath("$.state").value(expelledStudent().getState()))
                .andExpect(jsonPath("$.year").value(expelledStudent().getYear()))
                .andExpect(jsonPath("$.age").value(expelledStudent().getAge()))
                .andDo(document("deprive_student", responseFields(
                        fieldWithPath("firstName").description("Имя студента"),
                        fieldWithPath("lastName").description("Фамилия студента"),
                        fieldWithPath("scholarship").description("Стипендия студента"),
                        fieldWithPath("state").description("Состояние студента"),
                        fieldWithPath("year").description("Год обучения студента"),
                        fieldWithPath("age").description("Возраст студента")
                )));
    }

    @Test
    public void studentReduceScholarshipTest() throws Exception {
        mockMvc.perform(put("/students/1/reduce")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(enrolledStudent().getFirstName()))
                .andExpect(jsonPath("$.lastName").value(enrolledStudent().getLastName()))
                .andExpect(jsonPath("$.scholarship").value(enrolledStudent().getScholarship()))
                .andExpect(jsonPath("$.state").value(enrolledStudent().getState()))
                .andExpect(jsonPath("$.year").value(enrolledStudent().getYear()))
                .andExpect(jsonPath("$.age").value(enrolledStudent().getAge()))
                .andDo(document("reduce_student", responseFields(
                        fieldWithPath("firstName").description("Имя студента"),
                        fieldWithPath("lastName").description("Фамилия студента"),
                        fieldWithPath("scholarship").description("Стипендия студента"),
                        fieldWithPath("state").description("Состояние студента"),
                        fieldWithPath("year").description("Год обучения студента"),
                        fieldWithPath("age").description("Возраст студента")
                )));
    }

    @Test
    public void studentIncreaseScholarshipTest() throws Exception {
        mockMvc.perform(put("/students/3/increase")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(increasedScholarshipStudent().getFirstName()))
                .andExpect(jsonPath("$.lastName").value(increasedScholarshipStudent().getLastName()))
                .andExpect(jsonPath("$.scholarship").value(increasedScholarshipStudent().getScholarship()))
                .andExpect(jsonPath("$.state").value(increasedScholarshipStudent().getState()))
                .andExpect(jsonPath("$.year").value(increasedScholarshipStudent().getYear()))
                .andExpect(jsonPath("$.age").value(increasedScholarshipStudent().getAge()))
                .andDo(document("increase_student", responseFields(
                        fieldWithPath("firstName").description("Имя студента"),
                        fieldWithPath("lastName").description("Фамилия студента"),
                        fieldWithPath("scholarship").description("Стипендия студента"),
                        fieldWithPath("state").description("Состояние студента"),
                        fieldWithPath("year").description("Год обучения студента"),
                        fieldWithPath("age").description("Возраст студента")
                )));
    }



    private Student enrolledStudent() {
        return Student.builder()
                .id(1L)
                .firstName("Илья")
                .lastName("Азин")
                .state("UNDERGRADUATE")
                .scholarship("NORMAL")
                .year(3)
                .age(20)
                .build();
    }

    private Student expelledStudent() {
        return Student.builder()
                .id(2L)
                .firstName("Эмин")
                .lastName("Алиев")
                .state("EXPELLED")
                .scholarship("ABSENT")
                .year(3)
                .age(20)
                .build();
    }

    private Student increasedScholarshipStudent() {
        return Student.builder()
                .id(3L)
                .firstName("Камилла")
                .lastName("Хайруллина")
                .state("UNDERGRADUATE")
                .scholarship("INCREASED")
                .year(3)
                .age(20)
                .build();
    }


}
