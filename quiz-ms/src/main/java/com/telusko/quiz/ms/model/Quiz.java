package com.telusko.quiz.ms.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;

    /*@ManyToMany
    private List<Question> question;*/

    //When you have only numbers we cannot have ManytoMany, we use Element Collection
    @ElementCollection
    private List<Integer> questionIds;

}
