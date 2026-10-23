package com.example.demo.services;

import com.example.demo.entities.Address;
import com.example.demo.entities.User;
import com.example.demo.repositories.AddressRepository;
import com.example.demo.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AddressService implements IAddressService {
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public AddressService(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Address createAddress(Address address) {
        if (address.getUser() == null || address.getUser().getId() == null) {
            throw new RuntimeException("User ID is required");
        }
        User user = userRepository.findById(address.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        address.setUser(user);
        return addressRepository.save(address);
    }

    @Override
    public Address getAddressById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found"));
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public List<Address> getAddressesByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found");
        }
        return addressRepository.findByUserId(userId);
    }

    @Override
    public Address updateAddress(Long id, Address address) {
        Address existing = getAddressById(id);
        existing.setStreet(address.getStreet());
        existing.setCity(address.getCity());
        existing.setPostalCode(address.getPostalCode());
        existing.setCountry(address.getCountry());
        existing.setPhone(address.getPhone());

        if (address.getUser() != null && address.getUser().getId() != null) {
            User user = userRepository.findById(address.getUser().getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            existing.setUser(user);
        }

        return addressRepository.save(existing);
    }

    @Override
    public void deleteAddress(Long id) {
        Address address = getAddressById(id);
        addressRepository.delete(address);
    }
}