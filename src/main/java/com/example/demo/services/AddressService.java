
package com.example.demo.services;
import com.example.demo.entities.Address;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AddressService implements IAddressService {

    @Override
    public Address createAddress(Address address) {
        return null;
    }

    @Override
    public Address getAddressById(Long id) {
        return null;
    }

    @Override
    public List<Address> getAllAddresses() {
        return List.of();
    }

    @Override
    public List<Address> getAddressesByUserId(Long userId) {
        return List.of();
    }

    @Override
    public Address updateAddress(Long id, Address address) {
        return null;
    }

    @Override
    public void deleteAddress(Long id) {

    }

    @Override
    public Address getDefaultAddressByUserId(Long userId) {
        return null;
    }

    @Override
    public Address setDefaultAddress(Long addressId) {
        return null;
    }
}
