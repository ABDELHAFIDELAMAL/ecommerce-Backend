
package com.example.demo.services;

import com.example.demo.entities.Address;
import com.example.demo.repositories.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService implements IAddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public Address getAddressById(Long addressId) {
        return addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException(
                        "Address not found with id: " + addressId));
    }

    @Override
    public Address updateAddress(Long addressId, Address address) {
        Address existingAddress = getAddressById(addressId);

        existingAddress.setStreet(address.getStreet());
        existingAddress.setCity(address.getCity());
        existingAddress.setPostalCode(address.getPostalCode());
        existingAddress.setCountry(address.getCountry());

        return addressRepository.save(existingAddress);
    }

    @Override
    public void deleteAddress(Long addressId) {
        Address address = getAddressById(addressId);
        addressRepository.delete(address);
    }
}
