package ru.itis.documentsflow.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsersData {
    private String name;
    private String lastName;
    private File file;
}
