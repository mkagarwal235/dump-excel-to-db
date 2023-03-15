package com.exceltodb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    private int customerId;
    private int general;
    private int age;
    private int annual_Income;
    private int spending_Score;
}
