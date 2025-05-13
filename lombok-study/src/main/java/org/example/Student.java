package org.example;

import lombok.*;

import java.util.Objects;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@RequiredArgsConstructor
//@ToString
//@EqualsAndHashCode
//class Student {
//    @NonNull
//    private String studentId;
//    @NonNull
//    private String name;
//    @ToString.Exclude
//    private int age;
//
////    @Override
////    public boolean equals(Object o) {
////        if (o == null || getClass() != o.getClass()) return false;
////        Student student = (Student) o;
////        return age == student.age && Objects.equals(studentId, student.studentId) && Objects.equals(name, student.name);
////    }
////
////    @Override
////    public int hashCode() {
////        return Objects.hash(studentId, name, age);
////    }
//}

@Data // toString, equalsAndHashCode, Getter, SEtter, RequiredArgsConstructorTJFWJD
@NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
public class Student {
    @NonNull
    private String studentId;
    @NonNull
    private String name;

    @ToString.Exclude
    private int age;
}