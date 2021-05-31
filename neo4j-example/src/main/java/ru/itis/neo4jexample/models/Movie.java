package ru.itis.neo4jexample.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.*;

import java.util.HashSet;
import java.util.Set;

@Node("Movie")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id @GeneratedValue
    private Long id;
    private String title;
    @Property("tagline")
    private String description;
    @Relationship(type = "ACTED_IN", direction = Relationship.Direction.INCOMING)
    private Set<Person> actors = new HashSet<>();
    @Relationship(type = "DIRECTED", direction = Relationship.Direction.INCOMING)
    private Set<Person> directors = new HashSet<>();

}
