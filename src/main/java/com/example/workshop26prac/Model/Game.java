package com.example.workshop26prac.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    private Integer gid;
    private String name;
    private Integer ranking;
    private Integer year;
    private Integer users_rated;
    private String url;
    private String image;
    
}
