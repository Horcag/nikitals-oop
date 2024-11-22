//package ru.ssau.tk.nikitals.oop.model;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//@Entity
//@Table(name = "math_functions")
//@Getter
//@Setter
//@ToString
//@RequiredArgsConstructor
//@NoArgsConstructor
//public class MathFunctionEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "math_functions_id_seq")
//    @SequenceGenerator(name = "math_functions_id_seq", sequenceName = "math_functions_id_seq", allocationSize = 1)
//    @Column(name = "id")
//    private int id;
//
//    @Column(name = "name")
//    private @NonNull String name;
//
//    @Column(name = "expression")
//    private @NonNull String expression;
//
//}
