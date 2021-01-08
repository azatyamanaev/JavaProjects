package ru.itis.querydslmongodb.controllers;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.querydslmongodb.dto.StudentDto;
import ru.itis.querydslmongodb.models.Course;
import ru.itis.querydslmongodb.models.Student;
import ru.itis.querydslmongodb.repositories.AccountsRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class SearchController {
    @Autowired
    private AccountsRepository accountsRepository;

    @GetMapping("/accounts/search")
    public ResponseEntity<List<StudentDto>> searchByPredicate(@QuerydslPredicate(root = Student.class, bindings = AccountsRepository.class) Predicate predicate) {
        return ResponseEntity.ok(
                StreamSupport.stream(accountsRepository.findAll(predicate).spliterator(), true)
                        .map(user ->
                                StudentDto.builder()
                                        .firstName(user.getFirstName())
                                        .lastName(user.getLastName())
                                        .courseNames(user.getCourses().stream().map(Course::getTitle).collect(Collectors.toList()))
                                        .build()).collect(Collectors.toList()));
    }
}
