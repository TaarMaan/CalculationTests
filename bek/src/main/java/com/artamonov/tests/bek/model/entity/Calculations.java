package com.artamonov.tests.bek.model.entity;

import com.artamonov.tests.bek.model.enums.OperationsEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Entity
@Table(name = "calculations")
public class Calculations {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "number_one")
    private String numberOne;

    @NotNull
    @Column(name = "number_system_one")
    private Integer numberSystemOne;

    @NotNull
    @Column(name = "number_two")
    private String numberTwo;

    @NotNull
    @Column(name = "number_system_two")
    private Integer numberSystemTwo;

    @NotNull
    @Column(name = "date_created")
    private Date dateCreated;

    @Enumerated(EnumType.STRING)
    @Column(name = "operation_name")
    private OperationsEnum operationsEnum;

    public Calculations() {

    }
}
