package com.pabhinav;

import lombok.Getter;
import lombok.Setter;

/**
 * @author pabhinav
 */
public class Customer {

    @Setter @Getter
    private long id;

    @Setter @Getter
    private String firstName;

    @Setter @Getter
    private String lastName;

    public Customer(Long id, String firstName, String lastName){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
