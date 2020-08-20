package com.backbase.assignment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="ARRAYS",
        indexes = {@Index(name = "numbers_string_index",  columnList="numbers_string")})
public class ArrayEntity {
    @Id
    @GeneratedValue
    private Long id;
     
    @ElementCollection
    @Column(name = "numbers")
    private List<Integer> numbers = new ArrayList<>();

    @Column(name = "numbers_string")
    private String numbersString;
}
