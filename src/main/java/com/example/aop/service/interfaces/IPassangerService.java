package com.example.aop.service.interfaces;

import com.example.aop.entity.Passenger;

public interface IPassangerService {

    Passenger getPassengerById(Long id);

    Passenger createPassenger(Passenger passenger);


}
