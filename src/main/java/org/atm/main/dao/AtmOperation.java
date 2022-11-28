package org.atm.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AtmOperation {
	
	public static boolean login(String CARD_NUMBER, String PIN) throws ClassNotFoundException, SQLException
	{
		Connection connection= MysqlDBConnection.dbconnect();
		PreparedStatement statement=connection.prepareStatement("select * from atm where CARD_NUMBER=?");
		statement.setString(1, CARD_NUMBER);
		ResultSet result=statement.executeQuery();
		if(result.next())
		{
		
			if(result.getString("PIN").equals(PIN))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
		
		
	}
	
	
	
	
	public static double balanceCheck(long ACCOUNT_NUMBER) throws ClassNotFoundException, SQLException
	{
		Connection connection= MysqlDBConnection.dbconnect();
		PreparedStatement statement=connection.prepareStatement("select * from atm where ACCOUNT_NUMBER=?");
		statement.setLong(1, ACCOUNT_NUMBER);
		
		ResultSet result=statement.executeQuery();
		result.next();
		double balance=result.getDouble("balance");
		return balance;
		
	}
	
	public static double withdraw(long ACCOUNT_NUMBER, double withdrawalAmount) throws ClassNotFoundException, SQLException
	{
		Connection connection= MysqlDBConnection.dbconnect();
		PreparedStatement statement=connection.prepareStatement("select * from atm where ACCOUNT_NUMBER=?");
		statement.setLong(1, ACCOUNT_NUMBER);
		
		ResultSet result=statement.executeQuery();
		result.next();
		double avilableBalance=result.getDouble("balance");
		
		if(withdrawalAmount<avilableBalance) //7000<8000
		{
		   double remainingBalance=avilableBalance-withdrawalAmount;
		   statement=connection.prepareStatement("update atm set balance=? where ACCOUNT_NUMBER=?");
		   statement.setDouble(1, new Double(remainingBalance));
		   statement.setLong(2, ACCOUNT_NUMBER);
		   
		   if(statement.executeUpdate()>0)
		   {
			   return remainingBalance;  
		   }
		   else
		   {
			   return 0;
		   }
		}
		else
		{
			   return 0;
		}
	}
	
	
	
	public static double deposit(long ACCOUNT_NUMBER, double depositAmount) throws ClassNotFoundException, SQLException
	{
		Connection connection= MysqlDBConnection.dbconnect();
		PreparedStatement statement=connection.prepareStatement("select * from atm where ACCOUNT_NUMBER=?");
		statement.setLong(1, ACCOUNT_NUMBER);
		
		ResultSet result=statement.executeQuery();
		result.next();
		double avilableBalance=result.getDouble("balance");
		double newBalance=avilableBalance+depositAmount;
		
		  statement=connection.prepareStatement("update atm set balance=? where ACCOUNT_NUMBER=?");
		   statement.setDouble(1, new Double(newBalance));
		   statement.setLong(2, ACCOUNT_NUMBER);
		   
		   if(statement.executeUpdate()>0)
		   {
			   return newBalance;  
		   }
		   else
		   {
			   return 0;
		   }
		
	}
	
	
	public static ResultSet checkAccountInfo(long ACCOUNT_NUMBER) throws ClassNotFoundException, SQLException
	{
		Connection connection= MysqlDBConnection.dbconnect();
		PreparedStatement statement=connection.prepareStatement("select * from atm where ACCOUNT_NUMBER=?");
		statement.setLong(1, ACCOUNT_NUMBER);
		
		ResultSet result=statement.executeQuery();
		if(result.next())
		{
			return result;
		}
		else
		{
			return null;
		}
	}
	


}
