package com.example.aop.repository;

import com.example.aop.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengersRepository extends JpaRepository<Passenger,Long> {
}
