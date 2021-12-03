package com.pailsom.batchprocessing.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "PEOPLE")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_PERSON")
    @SequenceGenerator(sequenceName = "SEQ_PERSON", allocationSize = 1, name = "SEQ_PERSON")
    private long Id;

    private String lastName;
    private String firstName;

}
