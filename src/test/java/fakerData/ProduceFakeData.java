package fakerData;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class ProduceFakeData {

	@Test
	void fake_date() {
		Faker faker = new Faker();

		String name = faker.name().fullName(); // Miss Samanta Schmidt
		String firstName = faker.name().firstName(); // Emory
		String lastName = faker.name().lastName(); // Barton

		String streetAddress = faker.address().streetAddress(); 
		
		//use and store in string 
		String username=faker.name().username();
		String bloodGroup= faker.name().bloodGroup();
		
		//print data
		System.out.println(name);
		
	}
}
