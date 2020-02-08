package edu.microservices.springboot.car.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import edu.microservices.springboot.car.exception.NotFoundException;
import edu.microservices.springboot.car.model.Car;
import edu.microservices.springboot.car.repository.CarRepository;

@Service
public class CarServiceImpl implements CarService {
	private static final Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);

	@Autowired
	private CarRepository carRepository;

	@Override
	public List<Car> findCarByColor(String color) throws NotFoundException {
		List<Car> cars = carRepository.findByColorIgnoreCase(StringUtils.trimWhitespace(color));
		if (CollectionUtils.isEmpty(cars)) {
			throw new NotFoundException("No cars found with this color");
		}
		return cars;
	}

	@Override
	public Car saveCar(Car car) {
		return carRepository.save(car);
	}

	@Override
	public List<Car> findAllCars() {
		return carRepository.findAll();
	}

	@Override
	public void deleteCar(Long carId) {
		if (carRepository.existsById(carId)) {
			carRepository.deleteById(carId);
		} else {
			logger.error("cannot delete because ID does not exist");
			// TODO: throw appropriate exception
		}
	}

	@Override
	public Car findCarById(Long carId) throws NotFoundException {
		Optional<Car> car = carRepository.findById(carId);
		if (car.isPresent()) {
			return car.get();
		}

		throw new NotFoundException("Car not found with this id");
	}
}
