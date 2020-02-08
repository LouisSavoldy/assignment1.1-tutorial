
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import edu.microservices.springboot.car.exception.NotFoundException;
import edu.microservices.springboot.car.model.Car;
import edu.microservices.springboot.car.repository.CarRepository;
import edu.microservices.springboot.car.service.CarServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceImplTest {
	private static final String blue = "blue";

	@Mock
	private CarRepository carRepository;

	@InjectMocks
	private CarServiceImpl carServiceImpl;

	@Captor
	private ArgumentCaptor<String> captorCarColor;

	private List<Car> cars;

	@Before
	public void init() {
		cars = new ArrayList<>();

		Car car = new Car();
		car.setId(1L);
		car.setColor(blue);
		cars.add(car);
	}

	@Test
	public void testFindCarByColor() throws NotFoundException {

		String testColor = "   blue   ";

		// mock
		when(carRepository.findByColorIgnoreCase(Mockito.any())).thenReturn(cars);

		// run service
		List<Car> cars = carServiceImpl.findCarByColor(testColor);

		// verify mock
		verify(carRepository).findByColorIgnoreCase(captorCarColor.capture());

		// assert
		assertEquals("Verify correct car result count", 1, cars.size());
		assertEquals("Verify correct car was found", blue, captorCarColor.getValue());
	}

}
