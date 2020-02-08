package edu.microservices.springboot.car.service;

import java.util.List;

import edu.microservices.springboot.car.exception.NotFoundException;
import edu.microservices.springboot.car.model.Car;

public interface CarService {

	List<Car> findAllCars();

	List<Car> findCarByColor(String color) throws NotFoundException;

	Car saveCar(Car car);

	void deleteCar(Long carId);

	Car findCarById(Long carId) throws NotFoundException;

}
