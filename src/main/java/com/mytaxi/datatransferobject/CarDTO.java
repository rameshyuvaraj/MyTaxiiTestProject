package com.mytaxi.datatransferobject;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO {


    @JsonIgnore
    private Long id;

    @NotNull
    private String carName;

    @NotNull
    private ManufatureDTO manufatureDTO;

    @NotNull
    private String licensePlate;

    @NotNull
    private int seat_count;

    @NotNull
    private boolean convertible;

    private CarRating carRating;

    @NotNull
    private EngineType engineType;

    private int airBagCount;

    private boolean gpsEnabled;

    @NotNull
    private GearType gearType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public ManufatureDTO getManufatureDTO() {
        return manufatureDTO;
    }

    public void setManufatureDTO(ManufatureDTO manufatureDTO) {
        this.manufatureDTO = manufatureDTO;
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

    public CarRating getCarRating() {
        return carRating;
    }

    public void setCarRating(CarRating carRating) {
        this.carRating = carRating;
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
}

