package com.resilience.demo.moviecatalogservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CatalogItem {

    String name;
    String desc;
    int rating;
}
