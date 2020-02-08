package edu.microservices.springboot.car.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.microservices.springboot.car.model.Car;
import edu.microservices.springboot.car.service.CarService;

@Controller
@RequestMapping("/carList")
public class CarWebController {

	private CarService carService;

	@Autowired
	public CarWebController(CarService carService) {
		this.carService = carService;
	}

	@PostMapping
	public String addToCars(Car car) {
		carService.saveCar(car);
		return "redirect:/carList";
	}

	@GetMapping
	public String getCarsByColor(Model model) {
		List<Car> cars = carService.findAllCars();
		if (cars != null) {
			model.addAttribute("cars", cars);
		}
		return "carList";
	}
}
