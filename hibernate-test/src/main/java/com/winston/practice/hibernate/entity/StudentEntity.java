package com.winston.practice.hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentEntity {
    //学号
    private Long sid;
    //姓名
    private String name;
    //课程
    private Set<CourseEntity> courses;
    //必修课id
    private Set<Long> compulsoryCourses;

}
