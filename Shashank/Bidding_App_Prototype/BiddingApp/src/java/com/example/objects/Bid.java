/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.objects;

/**
 *
 * @author shanky
 */
public class Bid {
    private int id;
    private int value;
    private int userId;

    public Bid() {
    }
    
    public int getId() {
        return id;
    }
    
    public int getValue() {
        return value;
    }

    public int getUserId() {
        return userId;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setValue(int value) {
        this.value = value;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
