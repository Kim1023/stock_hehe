package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import urldemo.DataAccess;

public class DataAccessTest {
    private static DataAccess da= new DataAccess("sh600052");
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetHistoryData() {
		 assertEquals("12.01",da.GetHistoryData("sh600052", "2015-6-10"));
	}

	@Test
	public void testGetData() {
		assertEquals("浙江广厦",da.GetData()[0]);
	}

}
