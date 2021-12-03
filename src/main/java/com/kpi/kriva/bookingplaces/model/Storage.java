package com.kpi.kriva.bookingplaces.model;

import lombok.Data;

import javax.validation.constraints.*;
import java.sql.Date;

@Data
public class Storage {

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 10, max = 50, message = "Name should be between 10 and 50 characters")
    private String name;

    @NotEmpty(message = "Price should not be empty")
    @Positive
    private Double price;

    @NotEmpty(message = "Date should not be empty")
    @FutureOrPresent
    private Date date;

    @NotEmpty(message = "Status should not be empty")
    private String status;

    @NotEmpty(message = "Location should not be empty")
    private Location location;

    @NotEmpty(message = "Room should not be empty")
    private Room room;

}
