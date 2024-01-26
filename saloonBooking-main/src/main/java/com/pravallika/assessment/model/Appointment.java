package com.pravallika.assessment.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "appointmentdate")
    private LocalDate appointmentdate;

    @ManyToOne(targetEntity=SaloonItem.class)
    @JoinColumn(name = "item_id")
    private SaloonItem saloon_item;

}
