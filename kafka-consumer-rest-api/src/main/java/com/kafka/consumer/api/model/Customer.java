package com.kafka.consumer.api.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

@Schema(title = "Customer", description = "The model for customer")
@Entity
@Table(name = "customer")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Customer.class)
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="seq")
    @GenericGenerator(name = "seq", strategy="increment")
    private Integer id;

    @Schema(title = "Name of the customer",name="name",required=true)
    @Column(name = "name", nullable = false)
    private String name;

    @Schema(title = "Age of the customer",name="age",required=true)
    @Column(name = "age", nullable = false)
    private Integer age;

    @JsonManagedReference
    @JoinColumn(name = "fk_shipping_address_id")
    @OneToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private ShippingAddress shippingAddress;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (!id.equals(customer.id)) return false;
        if (!name.equals(customer.name)) return false;
        if (!age.equals(customer.age)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + age.hashCode();
        return result;
    }
}
