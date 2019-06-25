package data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

import database.EmptySetException;

class TupleTest {

	@Test
	void testGetDistance() {
		Tuple t = new Tuple (2);
		t.add(new DiscreteItem(new DiscreteAttribute("discreto", 0, new String[] {"primo","secondo"}), "primo"), 0);
		t.add(new ContinuousItem(new ContinuousAttribute("continuo", 1, 30.3,31.2), 30.3), 1);
		
		Tuple t1 = new Tuple (2);
		t1.add(new DiscreteItem(new DiscreteAttribute("discreto", 0, new String[] {"primo","secondo"}), "primo"), 0);
		t1.add(new ContinuousItem(new ContinuousAttribute("continuo", 1, 30.3,31.2), 30.3), 1);

		assertEquals(t,t1);
		assertTrue(t.getDistance(t1)==0);
		
		Tuple t2 = new Tuple (2);
		t2.add(new DiscreteItem(new DiscreteAttribute("discreto", 0, new String[] {"primo","secondo"}), "secondo"), 0);
		t2.add(new ContinuousItem(new ContinuousAttribute("continuo", 1, 31.3,31.4), 31.3), 1);
		
		assertNotEquals(t,t2);
		assertFalse(t.getDistance(t2)==0);
	}

	@Test
	void testAvgDistance() {
		
		Data d=null;
		try {
			d=new Data ("test");
		} catch (EmptySetException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
		Set<Integer> clusteredData = new TreeSet<Integer> ();
		clusteredData.add(0);
		
		if (d!=null) {
		assertEquals(d.getItemSet(1).avgDistance(d, clusteredData), d.getItemSet(0).getDistance(d.getItemSet(1)));
		}else fail("null");
	}

}
