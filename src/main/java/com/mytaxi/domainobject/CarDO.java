package com.mytaxi.domainobject;


import com.mytaxi.datatransferobject.CarRating;
import com.mytaxi.datatransferobject.EngineType;
import com.mytaxi.datatransferobject.GearType;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Entity
@Table(
        name = "car",
        uniqueConstraints = @UniqueConstraint(name = "uc_licensePlate", columnNames = {"licensePlate"})
)
public class CarDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Column(nullable = false)
    @NotNull(message = "Car Name cannot be null")
    private String carName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_manufatureId")
    private ManufatureDO manufatureDO;

    @Column(nullable = false)
    @NotNull(message = "License plate number cannot be null.")
    private String licensePlate;

    @Column
    private int seat_count;

    @Column
    private boolean convertible;

    @Enumerated(EnumType.ORDINAL)
    private CarRating rating;

    @Enumerated(EnumType.STRING)
    private EngineType engineType;

    @Column
    @Range(min = 1, max = 8)
    private int airBagCount;

    @Column
    private boolean gpsEnabled;

    @Enumerated(EnumType.STRING)
    private GearType gearType;

    @Column(nullable = false)
    private boolean assignedToDriver;

    public CarDO() {
    }

    public CarDO(Long id, ZonedDateTime dateCreated, @NotNull(message = "Car Name cannot be null") String carName,
                 ManufatureDO manufatureDO, @NotNull(message = "License plate number cannot be nuul.")
                         String licensePlate, int seat_count, boolean convertible, CarRating rating,
                 EngineType engineType, @Range(min = 1, max = 8) int airBagCount, boolean gpsEnabled,
                 GearType gearType, boolean assignedToDriver) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.carName = carName;
        this.manufatureDO = manufatureDO;
        this.licensePlate = licensePlate;
        this.seat_count = seat_count;
        this.convertible = convertible;
        this.rating = rating;
        this.engineType = engineType;
        this.airBagCount = airBagCount;
        this.gpsEnabled = gpsEnabled;
        this.gearType = gearType;
        this.assignedToDriver = assignedToDriver;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public ManufatureDO getManufatureDO() {
        return manufatureDO;
    }

    public void setManufatureDO(ManufatureDO manufatureDO) {
        this.manufatureDO = manufatureDO;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getSeat_count() {
        return seat_count;
    }

    public void setSeat_count(int seat_count) {
        this.seat_count = seat_count;
    }

    public boolean isConvertible() {
        return convertible;
    }

    public void setConvertible(boolean convertible) {
        this.convertible = convertible;
    }

    public CarRating getRating() {
        return rating;
    }

    public void setRating(CarRating rating) {
        this.rating = rating;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    public int getAirBagCount() {
        return airBagCount;
    }

    public void setAirBagCount(int airBagCount) {
        this.airBagCount = airBagCount;
    }

    public boolean isGpsEnabled() {
        return gpsEnabled;
    }

    public void setGpsEnabled(boolean gpsEnabled) {
        this.gpsEnabled = gpsEnabled;
    }

    public GearType getGearType() {
        return gearType;
    }

    public void setGearType(GearType gearType) {
        this.gearType = gearType;
    }

    public boolean isAssignedToDriver() {
        return assignedToDriver;
    }

    public void setAssignedToDriver(boolean assignedToDriver) {
        this.assignedToDriver = assignedToDriver;
    }
}
