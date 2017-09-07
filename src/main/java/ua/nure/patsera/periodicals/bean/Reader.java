package ua.nure.patsera.periodicals.bean;

import ua.nure.patsera.periodicals.model.BaseEntity;

import java.io.Serializable;

/**
 * Created by Дарина on 05.09.2017.
 */
public class Reader extends BaseEntity<Integer> implements Serializable {
    private String firstName;
    private String middleName;
    private String lastName;
    private String phone;
    private int flatNumber;
    private String houseNumber;
    private int idStreet;
    private String password;
    private String email;

    public Reader() {
    }

    public Reader(String firstName, String middleName, String lastName, String phone, int flatNumber, String houseNumber, int idStreet, String password, String email) {

        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phone = phone;
        this.flatNumber = flatNumber;
        this.houseNumber = houseNumber;
        this.idStreet = idStreet;
        this.password = password;
        this.email = email;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(int flatNumber) {
        this.flatNumber = flatNumber;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getIdStreet() {
        return idStreet;
    }

    public void setIdStreet(int idStreet) {
        this.idStreet = idStreet;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
