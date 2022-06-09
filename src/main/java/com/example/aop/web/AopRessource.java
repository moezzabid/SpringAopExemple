package com.example.aop.web;


import com.example.aop.entity.Passenger;
import com.example.aop.service.interfaces.IPassangerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/aop")
public class AopRessource {

    private final IPassangerService iPassangerService;

    public AopRessource(IPassangerService iPassangerService) {
        this.iPassangerService = iPassangerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passenger> getPassengerById(@PathVariable Long id) {
        return new ResponseEntity<>(iPassangerService.getPassengerById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Passenger> createPassenger(@RequestBody Passenger passenger) throws Exception {
        Passenger savePassenger = iPassangerService.createPassenger(passenger);
        return ResponseEntity.created(new URI("/aop/" + passenger.getId())).body(savePassenger);
    }


}
