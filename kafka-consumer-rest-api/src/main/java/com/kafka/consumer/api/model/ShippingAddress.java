package com.kafka.consumer.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name = "shipping_address")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = ShippingAddress.class)
public class ShippingAddress implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="seq")
    @GenericGenerator(name = "seq", strategy="increment")
    private Integer id;

    @JsonBackReference
    @OneToOne(mappedBy = "shippingAddress", cascade = CascadeType.ALL)
    private Customer customer;

    @JsonManagedReference
    @JoinColumn(name = "fk_address_id")
    @OneToOne(cascade=CascadeType.ALL)
    private Address address;

    public Address getAddress() {
        return address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShippingAddress that = (ShippingAddress) o;
        return id.equals(that.id) && customer.equals(that.customer) && address.equals(that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, address);
    }

    @Override
    public String toString() {
        return "ShippingAddress{" +
                "id=" + id +
                ", address=" + address +
                '}';
    }
}
