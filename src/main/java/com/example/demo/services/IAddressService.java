
package com.example.demo.services;

import com.example.demo.entities.Address;
import java.util.List;

public interface IAddressService {

    Address createAddress(Address address);

    Address getAddressById(Long id);

    List<Address> getAllAddresses();

    List<Address> getAddressesByUserId(Long userId);

    Address updateAddress(Long id, Address address);

    void deleteAddress(Long id);

    Address getDefaultAddressByUserId(Long userId);

    Address setDefaultAddress(Long addressId);
}
