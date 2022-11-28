package org.atm.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.atm.main.dao.AtmOperation;


public class App 
{
	static BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in)); 
	
	public static void main(String args[]) throws NumberFormatException, IOException, ClassNotFoundException, SQLException
	{
		
		System.out.println("====================================================================================");
		System.out.println("============================== WELCOME TO ATM INTERFACE=============================");
		System.out.println("====================================================================================");
		
		boolean status=false;
		do
		{
		
			System.out.println("====================================================================================");
			System.out.print("\t\t  CARD_NUMBER : ");
			String CARD_NUMBER=bufferedReader.readLine();
			System.out.println();
			System.out.print("\t\t\t PIN : ");
			String PIN=bufferedReader.readLine();
			System.out.println("====================================================================================");
			status=AtmOperation.login(CARD_NUMBER, PIN);
		
		
				if(status)
				{
				do
				{
					System.out.println("====================================================================================");
					System.out.println("=============================== ATM OPERATIONS =====================================");
					System.out.println("====================================================================================");
					System.out.println("\t\t  Enter 1-> BALANCE CHECK");
					System.out.println("\t\t  Enter 2-> WITHDRAW");
					System.out.println("\t\t  Enter 3-> DEPOSIT");
					System.out.println("\t\t  Enter 4-> CHECK ACCOUNT Info.");
					System.out.println("\t\t  Enter 5-> LOGOUT");
					System.out.println("====================================================================================");
					System.out.println("\t\t  Enter a valid input between 1 to 4:");
					int choice=Integer.parseInt(bufferedReader.readLine());
					
					switch(choice)
					{
					case 1: System.out.println("Enter valid ACCOUNT_NUMBER:");
							long ACCOUNT_NUMBER=Long.parseLong(bufferedReader.readLine());
							System.out.println( "Current available balance is :"+AtmOperation.balanceCheck(ACCOUNT_NUMBER));
							break;
							
					case 2: System.out.println("Enter valid ACCOUNT_NUMBER:");
					        ACCOUNT_NUMBER=Long.parseLong(bufferedReader.readLine());
							System.out.println("Enter withdraw amount:");
							double withdrawalAmount=Double.parseDouble(bufferedReader.readLine());
							double result=AtmOperation.withdraw(ACCOUNT_NUMBER, withdrawalAmount);
							
							if(result==0)
							{
								System.out.println("Insufficient Balance!!");
								System.out.println("Transaction is unsuccessfull!!");
							}
							else
							{
								System.out.println("Transaction successfull!!");
								System.out.println("Remaining balance is:"+result);
							}
							break;
					case 3: System.out.println("Enter valid ACCOUNT_NUMBER:");
					        ACCOUNT_NUMBER=Long.parseLong(bufferedReader.readLine());
							System.out.println("Enter deposit amount:");
							double depositAmount=Double.parseDouble(bufferedReader.readLine());
							result=AtmOperation.deposit(ACCOUNT_NUMBER, depositAmount);
							
							if(result==0)
							{
								
								System.out.println("Transaction is unsuccessfull!!");
							}
							else
							{
								System.out.println("Transaction successfull!!");
								System.out.println("New balance is:"+result);
							}
							break;	
					case 4: System.out.println("Enter valid ACCOUNT_NUMBER:");
					        ACCOUNT_NUMBER=Long.parseLong(bufferedReader.readLine());
							System.out.println("=====================================================================================");
							System.out.println("============================== ACCOUNT DETAIL =======================================");
 
							System.out.println("=====================================================================================");

							ResultSet accountInfo=AtmOperation.checkAccountInfo(ACCOUNT_NUMBER); 
			        		System.out.println("\t\t  ACCOUNT_NUMBER   :"+accountInfo.getLong("ACCOUNT_NUMBER"));
			        		System.out.println("\t\t  NAME             :"+accountInfo.getString("ACCOUNT_NAME"));
			        		System.out.println("\t\t  IFSE_CODE        :"+accountInfo.getString("IFSE_CODE"));
			        		System.out.println("\t\t  PAN_NUMBER       :"+accountInfo.getString("PAN_NUMBER"));
			        		System.out.println("\t\t  MOBILE_NUMBER    :"+accountInfo.getLong("MOBILE_NUMBER"));
			        		System.out.println("\t\t  CARD_VALID_DATE  :"+accountInfo.getString("CARD_VALID_DATE"));
			        		System.out.println("\t\t  ADDRESS          :"+accountInfo.getString("ADDRESS"));
			        		System.out.println("\t\t  EMAIL            :"+accountInfo.getString("EMAIL"));
			        		System.out.println("\t\t  BRANCHH          :"+accountInfo.getString("BRANCHH"));
			        		System.out.println("\t\t  AVAILABLE BALANCE:"+accountInfo.getDouble("BALANCE"));
			        		
							System.out.println("=====================================================================================");
							break;	
					
					case 5: status=false;
					        System.out.println("=====================================================================================");
							System.out.println("You have successfully logged out!!");
							System.out.println("Bye");
							System.out.println("=====================================================================================");
							System.out.println("========================================THANK YOU====================================");
							break;
							
					default: System.out.println("====================================================================================");
							 System.out.println("Wrong Choice!!");		
							 System.out.println("====================================================================================");

					
					}
					
					
				}
				while(status);
				}
				
				else
				{
					         System.out.println("====================================================================================");
				           	 System.out.println("Incorrect CARD_NUMBER or PIN!!");
					         System.out.println("====================================================================================");
				}
		}
		while(status);
	}
		
}
