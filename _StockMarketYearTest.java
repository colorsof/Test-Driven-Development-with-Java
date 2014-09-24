package com.gitau.tdd;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

public class _StockMarketYearTest{
	
	
	@Test
	public void startingBalanceMatchesConstructor() {
		StockMarketYear year = new StockMarketYear(1000,3000,10);
		assertEquals(3000, year.startingPrincipal());	
	}
	@Test
	public void startingPrincipalMatchesConstructor(){
		StockMarketYear year = new StockMarketYear(10000,3000,10);
		assertEquals(3000, year.startingPrincipal());
	}
	@Test
	public void interestRateMatchesConstructor(){
		StockMarketYear year = new StockMarketYear(10000,3000,10);
		assertEquals(10, year.interestRate);
	}
	@Test
	public void startingCapitalGainsIsStartingBalanceMinusStartingPrincipal(){
		StockMarketYear year= new StockMarketYear(10000,3000,10);
		assertEquals(7000, year.startingCapitalGains());
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
	public void nextYearsInterestRateEqualsThisYearsInterestRate(){
		StockMarketYear thisYear= new StockMarketYear(10000,3000,10);
		assertEquals(thisYear.interestRate(), thisYear.nextYear(25).interestRate());
	}
	@Test
	public void nextYearsStartingPrincipalEqualsThisYearsEndingPrincipal(){
		StockMarketYear thisYear= new StockMarketYear(10000,3000,10);
		assertEquals(thisYear.endingPrincipal(), thisYear.nextYear(25).startingPrincipal());
	}
		
	@Test
	public void withdrawnFundsDoNotEarnInterest(){
		StockMarketYear year = new StockMarketYear(10000,3000,10);
	year.withdraw(1000);
	assertEquals(900, year.interestEarned(25));
	}
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
	@Test
	public void multipleWithdrawalsInAYearAreTotalled(){
		StockMarketYear year = new StockMarketYear(10000,3000,10);
		year.withdraw(1000);
		year.withdraw(3000);
		assertEquals(4333, year.totalWithdrawn(25));		
	}
	@Test
	public void endingPrincipal(){
		StockMarketYear year = new StockMarketYear(10000,3000,10);
		year.withdraw(2000);
		assertEquals(1000, year.endingPrincipal());
	}
	
	@Test
	public void endingPrincipalNeverGoesBelowZero(){
		StockMarketYear year = new StockMarketYear(10000,3000,10);
		year.withdraw(4000);
		assertEquals(0, year.endingPrincipal());
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
	public void interestEarnedIsAProductofStartingBalanceAndInterest(){
		StockMarketYear year = new StockMarketYear(10000,3000,10);
		assertEquals(1000, year.interestEarned(25));
	}
	@Test
	//@Ignore
	public void endingCapitalGainsIncludesInterestEarned(){
		StockMarketYear account = new StockMarketYear(10000,3000,10);
		assertEquals(7000, account.startingCapitalGains());
		assertEquals(8000, account.endingCapitalGains(25));
		
	}
	@Test
	public void capitalGainsWithdrawn(){
		
	}
	private StockMarketYear newAccount(){
		StockMarketYear account= newAccount();
		return account;
	}
//	@Test
	//public void withdrawingMoreThanPrincipalIncursCapitalGainsTax(){
		//savingsAccountYear year = new savingsAccountYear(10000,3000,10);
		//year.withdraw(3000);
		//assertEquals(7700, year.endingBalance());
		//year.withdraw(5000);
		//assertEquals(2200-1250, year.endingBalance());
		
	//}
}
	



