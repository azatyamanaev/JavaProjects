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
import ru.itis.hateoas.simplehateoasservice.models.Teacher;
import ru.itis.hateoas.simplehateoasservice.services.TeachersService;

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
public class TeachersTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeachersService teachersService;

    @BeforeEach
    public void setUp() {
        when(teachersService.approve(1L)).thenReturn(normalTeacher());
        when(teachersService.suggest(2L)).thenReturn(suggestedTeacher());
        when(teachersService.decline(3L)).thenReturn(declinedTeacher());
    }

    @Test
    public void teacherApproveTest() throws Exception {
        mockMvc.perform(put("/teachers/1/approve")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(normalTeacher().getFirstName()))
                .andExpect(jsonPath("$.lastName").value(normalTeacher().getLastName()))
                .andExpect(jsonPath("$.current").value(normalTeacher().getCurrent()))
                .andExpect(jsonPath("$.bonus").value(normalTeacher().getBonus()))
                .andDo(document("approve_teacher", responseFields(
                        fieldWithPath("firstName").description("Имя преподавателя"),
                        fieldWithPath("lastName").description("Фамилия преподавателя"),
                        fieldWithPath("current").description("Состояние преподавателя"),
                        fieldWithPath("bonus").description("Надбавка преподавателя")
                )));
    }

    @Test
    public void teacherSuggestTest() throws Exception {
        mockMvc.perform(put("/teachers/2/suggest")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(suggestedTeacher().getFirstName()))
                .andExpect(jsonPath("$.lastName").value(suggestedTeacher().getLastName()))
                .andExpect(jsonPath("$.current").value(suggestedTeacher().getCurrent()))
                .andExpect(jsonPath("$.bonus").value(suggestedTeacher().getBonus()))
                .andDo(document("suggest_teacher", responseFields(
                        fieldWithPath("firstName").description("Имя преподавателя"),
                        fieldWithPath("lastName").description("Фамилия преподавателя"),
                        fieldWithPath("current").description("Состояние преподавателя"),
                        fieldWithPath("bonus").description("Надбавка преподавателя")
                )));
    }

    @Test
    public void teacherDeclineTest() throws Exception {
        mockMvc.perform(put("/teachers/3/decline")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(suggestedTeacher().getFirstName()))
                .andExpect(jsonPath("$.lastName").value(suggestedTeacher().getLastName()))
                .andExpect(jsonPath("$.current").value(suggestedTeacher().getCurrent()))
                .andExpect(jsonPath("$.bonus").value(suggestedTeacher().getBonus()))
                .andDo(document("decline_teacher", responseFields(
                        fieldWithPath("firstName").description("Имя преподавателя"),
                        fieldWithPath("lastName").description("Фамилия преподавателя"),
                        fieldWithPath("current").description("Состояние преподавателя"),
                        fieldWithPath("bonus").description("Надбавка преподавателя")
                )));
    }


    private Teacher normalTeacher() {
        return Teacher.builder()
                .id(1L)
                .firstName("Марсель")
                .lastName("Сидиков")
                .current("NORMAL")
                .bonus(1)
                .build();
    }

    private Teacher suggestedTeacher() {
        return Teacher.builder()
                .id(2L)
                .firstName("Евгений")
                .lastName("Зубков")
                .current("SUGGESTED")
                .bonus(0)
                .build();
    }

    private Teacher declinedTeacher() {
        return Teacher.builder()
                .id(3L)
                .firstName("Иван")
                .lastName("Елкин")
                .current("SUGGESTED")
                .bonus(0)
                .build();
    }
}
