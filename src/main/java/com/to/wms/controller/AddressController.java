package com.to.wms.controller;

import com.to.wms.model.Address;
import com.to.wms.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<List<?>> getAllAdresses() {
        List<?> adresses = addressService.getAll();
        return ResponseEntity.ok(adresses);
    }

    @GetMapping("/by-city")
    public ResponseEntity<Address> getAddressByCity(@RequestParam String city) {
        Address address = addressService.getAddressByCity(city);
        return ResponseEntity.ok(address);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addAddress(@Valid @RequestBody Address address) {
        addressService.addAddress(address);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAddress(@Valid @RequestBody Address address, @PathVariable String id) {
        addressService.editAddress(address, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteAddress(@PathVariable String id) {
        addressService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
