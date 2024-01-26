package com.pravallika.assessment.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "saloon_item")
public class SaloonItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "category")
    private String category;
    @Column(name = "price")
    private float price;

    @OneToMany(mappedBy = "saloon_item", cascade = { CascadeType.ALL },fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Appointment> appointments =new ArrayList<Appointment>();

}
