package com.example.restaurant.domain.valueobject;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by hector on 21/07/16.
 */
public class BookingVO implements Serializable {

    private String id;

    private String name;

    private String restaurantId;

    private String userId;

    private LocalDate date;

    private LocalTime time;

    private String tableId;

    public BookingVO() {
    }

    public BookingVO(String id, String name, String restaurantId, String userId, LocalDate date, LocalTime time, String tableId) {
        this.id = id;
        this.name = name;
        this.restaurantId = restaurantId;
        this.userId = userId;
        this.date = date;
        this.time = time;
        this.tableId = tableId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }
}
