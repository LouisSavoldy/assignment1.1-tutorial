package edu.microservices.springboot.car.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.microservices.springboot.car.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

	List<Car> findByColorIgnoreCase(String color);

}