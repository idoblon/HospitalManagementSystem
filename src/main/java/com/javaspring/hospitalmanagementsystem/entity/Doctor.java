package com.javaspring.hospitalmanagementsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

        private Long id;
        private String name;
        private String specialty;

}
