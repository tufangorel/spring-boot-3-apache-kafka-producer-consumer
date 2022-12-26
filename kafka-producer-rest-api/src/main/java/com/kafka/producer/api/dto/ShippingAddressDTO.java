package com.kafka.producer.api.dto;


public class ShippingAddressDTO {

    private Integer id;

    private AddressDTO address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }
}
