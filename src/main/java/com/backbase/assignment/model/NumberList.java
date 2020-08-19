package com.backbase.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class NumberList {
    private List<Integer> numList;
    private String stringValue;
}
