package com.thefivebros.FinalExam.model;

import java.util.Arrays;

public class Doctor {
    private int id;
    private String firstName;
    private String lastName;
    private String[] specialties;


    public Doctor(int id, String firstName, String lastName, String[] specialties) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialties = specialties;
    }

    public Doctor() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String[] getSpecialties() {
        return specialties;
    }
    public String getSpecialties2() {
        return Arrays.toString(specialties);
    }

    public void setSpecialties(String[] specialties) {
        this.specialties = specialties;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", specialties=" + Arrays.toString(specialties) +
                '}';
    }
}
