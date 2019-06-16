package data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DicreteAttributeTest {


	@Test
	void testGetNumberOfDistinctValues() {
		DiscreteAttribute att = new DiscreteAttribute("ciao", 1, new String[] {"uno", "due" });
		assertEquals(2, att.getNumberOfDistinctValues());
	}


}
