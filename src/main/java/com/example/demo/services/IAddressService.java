
package com.example.demo.services;

import com.example.demo.entities.Address;
import java.util.List;

public interface IAddressService {

    Address createAddress(Address address);

    List<Address> getAllAddresses();

    Address getAddressById(Long addressId);

    Address updateAddress(Long addressId, Address address);

    void deleteAddress(Long addressId);
}
