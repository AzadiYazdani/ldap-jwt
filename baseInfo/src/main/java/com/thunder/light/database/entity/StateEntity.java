package com.thunder.light.database.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "light_db",name = "state")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set", toBuilder = true, builderMethodName = "newInstance")
public class StateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JoinColumn(name = "name")
    private String name;

    @OneToMany(targetEntity = CityEntity.class, mappedBy = "state", fetch = FetchType.LAZY)
    private List<CityEntity> cities;

}
