package org.atm.main;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import org.atm.main.dao.AtmOperation;
import org.atm.main.exception.InvalidAmountException;
import org.junit.jupiter.api.Test;

public class WithdrawModuleTest {
	
	@Test
	public void testCase1() throws ClassNotFoundException, SQLException, InvalidAmountException{
		assertEquals(8000, AtmOperation.withdraw(693582471,8000));
	}

	
	@Test
	public void testCase2() throws ClassNotFoundException, SQLException, InvalidAmountException{
		assertEquals(8000, AtmOperation.withdraw(693582471,15000));
	}
}
