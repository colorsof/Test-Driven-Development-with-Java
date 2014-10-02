package com.gitau.tdd;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

public class _StockMarketYearTest{
	
	
	@Test
	public void startingValues() {
		StockMarketYear year = new StockMarketYear(10000,3000,10);
		assertEquals("The starting balance: ",10000, year.startingBalance());	
		assertEquals("The starting principal: ",3000, year.startingPrincipal());	
		assertEquals("The default interest rate: ",10, year.interestRate);
		assertEquals("The default withdrawal: ",0, year.totalWithdrawals);
	}
	/*@Test
	public void startingPrincipalMatchesConstructor(){
		StockMarketYear year = new StockMarketYear(10000,3000,10);
		assertEquals(3000, year.startingPrincipal());
	}
	@Test
	public void interestRateMatchesConstructor(){
		StockMarketYear year = new StockMarketYear(10000,3000,10);
		assertEquals(10, year.interestRate);
	}*/
	
	@Test
	public void endingPrincipal(){
		StockMarketYear year = new StockMarketYear(10000,3000,10);
		year.withdraw(1000);
		assertEquals("Ending principal considers withdrawals", 2000, year.endingPrincipal());
		year.withdraw(1000);
		assertEquals("Ending principal considers multiple withdrawals", 1000, year.endingPrincipal());
		year.withdraw(4000);
		assertEquals("Ending principal never goes below zero", 0, year.endingPrincipal());
	}
	@Test
	public void startingCapitalGainsIsStartingBalanceMinusStartingPrincipal(){
		StockMarketYear year= new StockMarketYear(10000,3000,10);
		assertEquals(7000, year.startingCapitalGains());
	}
	@Test
	public void interestEarned(){
		StockMarketYear year = new StockMarketYear(10000,3000,10);
		assertEquals(" basic interest ",1000, year.interestEarned(25));
		year.withdraw(2000);
		assertEquals("Withdrawals don't earn interest ",800, year.interestEarned(25));
	}
	/*
	@Test
	public void endingPrincipalNeverGoesBelowZero(){
		StockMarketYear year = new StockMarketYear(10000,3000,10);
		year.withdraw(4000);
		assertEquals(0, year.endingPrincipal());
	}
	
	@Test
	public void withdrawnFundsDoNotEarnInterest(){
		StockMarketYear year = new StockMarketYear(10000,3000,10);
	year.withdraw(1000);
	assertEquals(900, year.interestEarned(25));
	}
	*/	
	
	@Test
	public void totalWithdrawnIncludingCapitalGains(){
		StockMarketYear year = new StockMarketYear(10000,0,10);
		year.withdraw(1000);
		assertEquals("Total withdrawn:", 1333, year.totalWithdrawn(25));
	}

	@Test
	public void capitalGainswithdrawnDoNotEarnInterest(){
		StockMarketYear year = new StockMarketYear(10000,0,10);
		year.withdraw(1000);
		assertEquals("Capital Gains withdrawn:", 1000, year.capitalGainsWithdrawn());
		assertEquals("Capital Gains tax:", 333, year.capitalGainsTaxIncurred(25));
		assertEquals("Capital Gains tax:", 1333, year.totalWithdrawn(25));
		assertEquals("Interest Earned:", 866, year.interestEarned(25));
	}
	/*@Test
	public void endingCapitalGainsIncludesInterestEarned(){
		StockMarketYear account = new StockMarketYear(10000,3000,10);
		assertEquals(7000, account.startingCapitalGains());
		assertEquals(8000, account.endingCapitalGains(25));
	
}	*/
	@Test
	public void multipleWithdrawalsInAYearAreTotalled(){
		StockMarketYear year = new StockMarketYear(10000,3000,10);
		year.withdraw(1000);
		year.withdraw(3000);
		assertEquals(4333, year.totalWithdrawn(25));		
	}
	
	
	@Test
	public void withdrawingMoreThanPrincipalTakesFromCapitalGains(){
		StockMarketYear year = new StockMarketYear(10000,3000,10);
		assertEquals(3000, year.startingPrincipal());	
		year.withdraw(1000);
		assertEquals(0, year.capitalGainsWithdrawn());
		year.withdraw(3000);
		assertEquals(1000, year.capitalGainsWithdrawn());
	}	
	@Test
	public void capitalGainsTaxIncurred_NeedsToCoverCapitalGainsWithdrawn_And_AdditiionalWithdrwanToCoverTaxes(){
		StockMarketYear year = new StockMarketYear(10000,3000,10);
		year.withdraw(5000);
		assertEquals(2000, year.capitalGainsWithdrawn());
		assertEquals(666, year.capitalGainsTaxIncurred(25));				
	}
	
	
	@Test
	public void capitalGainsTaxIsIncludedInEndingBalance(){
		StockMarketYear year = new StockMarketYear(10000,3000,10);
		int amountWithdrawn = 5000;
		year.withdraw(amountWithdrawn);
		int expectedCapitalGainsTax = 666;
		assertEquals(expectedCapitalGainsTax, year.capitalGainsTaxIncurred(25));
		int expectedStartingBalanceAfterWithdrawals = 10000 - amountWithdrawn - expectedCapitalGainsTax;
		assertEquals((int)(expectedStartingBalanceAfterWithdrawals * 1.10), year.endingBalance(25));
	}
	
	
	
	@Test
	public void endingBalanceAppliesInterestRate(){
		StockMarketYear year = new StockMarketYear(10000,3000,10);
		assertEquals(11000, year.endingBalance(25));
	}
	@Test
	public void nextYearStartingBalanceEqualsThisYearsEndingBalance() {
		StockMarketYear thisYear= new StockMarketYear(10000,3000,10);
		assertEquals(thisYear.endingBalance(25), thisYear.nextYear(25).startingBalance());
		
	}
	@Test
	public void nextYear(){
		StockMarketYear thisYear= new StockMarketYear(10000,3000,10);
		assertEquals(thisYear.endingPrincipal(), thisYear.nextYear(25).startingPrincipal());
		assertEquals(thisYear.interestRate(), thisYear.nextYear(25).interestRate());
		assertEquals(thisYear.endingBalance(25), thisYear.nextYear(25).startingBalance());
	}	
	
	

}
	



