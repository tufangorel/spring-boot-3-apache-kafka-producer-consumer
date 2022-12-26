package com.kafka.consumer.api.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;

@Entity
@Table(name = "supplier")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Supplier.class)
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="seq")
    @GenericGenerator(name = "seq", strategy="increment")
    @Column(name = "supplier_id")
    private int supplierId;

    private String supplierCode;

    private String fullName;

    private String location;

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supplier supplier = (Supplier) o;
        return Objects.equals(supplierCode, supplier.supplierCode) && Objects.equals(fullName, supplier.fullName) && Objects.equals(location, supplier.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(supplierCode, fullName, location);
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "supplierId=" + supplierId +
                ", supplierCode='" + supplierCode + '\'' +
                ", fullName='" + fullName + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
