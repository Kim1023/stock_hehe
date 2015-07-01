package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import urldemo.StocksPrice;

public class StocksPriceTest {
	 private static StocksPrice sp= new StocksPrice();
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddStock() {
		sp.AddStock("sh600052");
		assertEquals(1,sp.num);
		sp.AddStock("sh600052");
	    assertEquals(1,sp.num);
	    sp.AddStock("sh600051");
	    assertEquals(2,sp.num);
	}

	@Test
	public void testUpdateStock() {
		sp.updateStock("sh600196");
		 assertEquals("sh600196",sp.singleNum);
	}

}
