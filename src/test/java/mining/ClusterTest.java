package mining;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import data.Data;
import database.EmptySetException;

class ClusterTest {

	@Test
	void testCompareTo() {
		Data d = null;
		try {
			d = new Data("test");
		} catch (EmptySetException e) {
			e.printStackTrace();
			fail("failed");
		}
		Cluster c = new Cluster(d.getItemSet(0));
		c.addData(0);
		c.addData(1);

		Cluster c1 = new Cluster(d.getItemSet(1));
		c.addData(1);

		assertEquals(-1, c1.compareTo(c));

	}

}
