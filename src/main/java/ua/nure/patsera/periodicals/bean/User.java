package ua.nure.patsera.periodicals.bean;

import ua.nure.patsera.periodicals.dao.BaseEntity;

import java.io.Serializable;

/**
 * Created by Дарина on 05.09.2017.
 */
public class User extends BaseEntity<Integer> implements Serializable {
    private String firstName;
    private String middleName;
    private String lastName;
    private String phone;
    private String street;
    private int flatNumber;
    private String houseNumber;
    private String password;
    private int idDistrict;
    private int idRole;
    private double account;
    private boolean blocked;

    public User() {
    }

    public User(String firstName, String middleName, String lastName, String phone, int idRole,
                int flatNumber, String houseNumber, String password, String email, String street, int idDistrict, boolean blocked) {
        this.street = street;
        this.firstName = firstName;
        this.middleName = middleName;
        this.idRole = idRole;
        this.lastName = lastName;
        this.phone = phone;
        this.flatNumber = flatNumber;
        this.houseNumber = houseNumber;
        this.password = password;
        this.email = email;
        this.idDistrict = idDistrict;
        this.blocked = blocked;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public int getIdDistrict() {
        return idDistrict;
    }

    public void setIdDistrict(int idDistrict) {
        this.idDistrict = idDistrict;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    private String email;

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

    public double getAccount() { return account; }

    public void setAccount(double account) {
        this.account = account;
    }
}
