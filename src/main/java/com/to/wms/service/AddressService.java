package com.to.wms.service;

import com.to.wms.model.Address;
import com.to.wms.repository.AddressRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressService extends BasicGenericService<AddressRepository>{

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository, AddressRepository genericAddressRepository) {
        super(genericAddressRepository);
        this.addressRepository = addressRepository;
    }

    public void addAddress(Address address) {
        addressRepository.save(address);
    }

    public void editAddress(Address addressToUpdate, String addressId) {
        Address address = addressRepository.findById(addressId).orElseThrow(() -> new IllegalStateException("No address found"));
        address.setCity(addressToUpdate.getCity());
        address.setCountry(addressToUpdate.getCountry());
        address.setNumber(addressToUpdate.getNumber());
        address.setPostCode(addressToUpdate.getPostCode());
        address.setStreet(addressToUpdate.getStreet());
        addressRepository.save(address);
    }

    @Transactional(readOnly = true)
    public Address getAddressByCity(String city) {
        return addressRepository.findAddressByCity(city);
    }
}
