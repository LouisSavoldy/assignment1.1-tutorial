package edu.microservices.springboot.car.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.microservices.springboot.car.exception.NotFoundException;
import edu.microservices.springboot.car.model.Car;
import edu.microservices.springboot.car.service.CarService;

/**
 * API interface
 * 
 * TODO: Replace entities being returned with DTOs
 */
@RestController
@RequestMapping("/cars")
public class CarRestController {

	@Autowired
	private CarService carService;

	private static final Logger logger = LoggerFactory.getLogger(CarRestController.class);

	/**
	 * Using @RequestParam
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCarsByColor(@RequestParam(value = "color", required = true) String color) {
		List<Car> cars;
		try {
			cars = carService.findCarByColor(color);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(cars, HttpStatus.OK);
	}

	/**
	 * Using @PathVariable
	 */
	@GetMapping(value = "/{carId}")
	public ResponseEntity<?> getCarById(@PathVariable("carId") Long carId) {

		Car car;
		try {
			car = carService.findCarById(carId);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(car, HttpStatus.OK);
	}

	/**
	 * Example PUT
	 */
	@PutMapping
	public ResponseEntity<?> updateCar(@RequestBody Car car) {
		logger.info("Updating car with: {}", car);
		// STUB service method to update car
		return new ResponseEntity<>(car, HttpStatus.OK);
	}

	/**
	 * Example POST
	 */
	@PostMapping
	public ResponseEntity<?> addNewCar(@RequestBody Car car) {
		return new ResponseEntity<>(carService.saveCar(car), HttpStatus.CREATED);
	}

	/**
	 * Example DELETE
	 */
	@DeleteMapping(value = "/{carId}")
	public ResponseEntity<?> deleteCar(@PathVariable("carId") Long carId) {
		carService.deleteCar(carId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
