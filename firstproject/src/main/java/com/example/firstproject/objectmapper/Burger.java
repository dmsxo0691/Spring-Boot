package com.example.firstproject.objectmapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter // json으로 만들기 위해 필요
public class Burger {
    private String name;
    private int price;
    private List<String> ingredients;
}
