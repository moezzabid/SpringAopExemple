package com.example.aop.service.impl;

import com.example.aop.entity.Passenger;
import com.example.aop.repository.PassengersRepository;
import com.example.aop.service.interfaces.IPassangerService;
import exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;


@Service
@Transactional
public class PassangerImpl implements IPassangerService {

    private final PassengersRepository passengersRepository ;

    public PassangerImpl(PassengersRepository passengersRepository) {
        this.passengersRepository = passengersRepository;
    }

    HashMap<Long,Passenger>passangersMap= new HashMap<>();

    @Override
    public Passenger getPassengerById(Long id) {
        Passenger passenger = passengersRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("id n'existe pas")
        );
        return passenger;
    }

    @Override
    public Passenger createPassenger(Passenger passenger) {
        if(passenger.getId() !=null){
            throw new ResourceNotFoundException("id deja exist");
        }
        return passengersRepository.save(passenger);
    }

    public Passenger getPassenger(Long id){
        if(passangersMap.get(id)!=null){
            return passangersMap.get(id);
        }
        Passenger passenger=getPassengerById(id);
        return passenger ;
    }

    public HashMap<Long, Passenger> getPassangersMap() {
        return passangersMap;
    }
}
