package com.thunder.light.database.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(schema = "light_db", name = "city")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set", toBuilder = true, builderMethodName = "newInstance")
public class CityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @ManyToOne
    @JoinColumn(name = "stateId")
    private StateEntity state;

    private String name;
}
