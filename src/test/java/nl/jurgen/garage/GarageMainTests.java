package nl.jurgen.garage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GarageMainTests {

	@Test
	void contextLoads() {
	}

	@Test
	void exampleUnitTest(){


		//welke klasses unit testen?
		//arrange
		// act
		//assert

		int som;
		som = 34 + 56;
		int expected = 90;
		Assertions.assertEquals(som, expected);
	}


}
