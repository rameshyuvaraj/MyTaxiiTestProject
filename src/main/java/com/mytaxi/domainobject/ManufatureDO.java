package com.mytaxi.domainobject;

import javax.persistence.*;

@Entity
@Table(name = "manufacture")
public class ManufatureDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_manufatureId")
    private Long id;

    @Column(name = "ManufactureName")
    private String name;

    @Column
    private String model;

    @Column
    private String modelYear;

    public ManufatureDO() {
    }

    public ManufatureDO(Long id, String name, String model, String modelYear) {
        this.id = id;
        this.name = name;
        this.model = model;
        this.modelYear = modelYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModelYear() {
        return modelYear;
    }

    public void setModelYear(String modelYear) {
        this.modelYear = modelYear;
    }
}
