package com.gitau.tdd;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

public class _savingsAccountYearTest {
	
	@Test
	public void startingBalanceMatchesConstructor() {
		savingsAccountYear year = new savingsAccountYear(10000,3000,10);
		assertEquals(3000, year.startingPrincipal());	
	}
	@Test
	public void startingPrincipalMatchesConstructor(){
		savingsAccountYear year = new savingsAccountYear(10000,3000,10);
		assertEquals(3000, year.startingPrincipal());
	}
	@Test
	public void interestRateMatchesConstructor(){
		savingsAccountYear year = new savingsAccountYear(10000,3000,10);
		assertEquals(10, year.interestRate);
	}
	@Test
	public void startingCapitalGainsIsStartingBalanceMinusStartingPrincipal(){
		savingsAccountYear year= new savingsAccountYear(10000,3000,10);
		assertEquals(7000, year.startingCapitalGains());
	}
	
	@Test
	public void endingBalanceAppliesInterestRate(){
		savingsAccountYear year = new savingsAccountYear(10000,3000,10);
		assertEquals(11000, year.endingBalance(25));
	}
	
	
	@Test
	public void nextYearStartingBalanceEqualsThisYearsEndingBalance() {
		savingsAccountYear thisYear= new savingsAccountYear(10000,3000,10);
		assertEquals(thisYear.endingBalance(25), thisYear.nextYear(25).startingBalance());
		
	}
	@Test
	public void nextYearsInterestRateEqualsThisYearsInterestRate(){
		savingsAccountYear thisYear= new savingsAccountYear(10000,3000,10);
		assertEquals(thisYear.interestRate(), thisYear.nextYear(25).interestRate());
	}
		
	@Test
	public void withdrawnFundsDoNotEarnInterest(){
	savingsAccountYear year = new savingsAccountYear(10000,3000,10);
	year.withdraw(1000);
	assertEquals(900, year.interestEarned(25));
	}
	@Test
	public void totalWithdrawnIncludingCapitalGains(){
		savingsAccountYear year = new savingsAccountYear(10000,0,10);
		year.withdraw(1000);
		assertEquals("Total withdrawn:", 1333, year.totalWithdrawnIncludingCapitalGainsTaxWithdrawn(25));
	}
	
	@Test
	public void capitalGainswithdrawnDoNotEarnInterest(){
		savingsAccountYear year = new savingsAccountYear(10000,0,10);
		year.withdraw(1000);
		assertEquals("Capital Gains withdrawn:", 1000, year.capitalGainsWithdrawn());
		assertEquals("Capital Gains tax:", 333, year.capitalGainsTaxIncurred(25));
		assertEquals("Interest Earned:", 866, year.interestEarned(25));
	}
	@Test
	public void multipleWithdrawalsInAYearAreTotalled(){
		savingsAccountYear year = new savingsAccountYear(10000,3000,10);
		year.withdraw(1000);
		year.withdraw(3000);
		assertEquals(4000, year.totalwithdrawals());		
	}
	@Test
	public void endingPrincipal(){
		savingsAccountYear year = new savingsAccountYear(10000,3000,10);
		year.withdraw(2000);
		assertEquals(1000, year.endingPrincipal());
	}
	
	@Test
	public void endingPrincipalNeverGoesBelowZero(){
		savingsAccountYear year = new savingsAccountYear(10000,3000,10);
		year.withdraw(4000);
		assertEquals(0, year.endingPrincipal());
	}

	@Test
	public void withdrawingMoreThanPrincipalTakesFromCapitalGains(){
		savingsAccountYear year = new savingsAccountYear(10000,3000,10);
		assertEquals(3000, year.startingPrincipal());	
		year.withdraw(1000);
		assertEquals(0, year.capitalGainsWithdrawn());
		year.withdraw(3000);
		assertEquals(1000, year.capitalGainsWithdrawn());
	}	
	@Test
	public void capitalGainsTaxIncurred_NeedsToCoverCapitalGainsWithdrawn_And_AdditiionalWithdrwanToCoverTaxes(){
		savingsAccountYear year = new savingsAccountYear(10000,3000,10);
		year.withdraw(5000);
		assertEquals(2000, year.capitalGainsWithdrawn());
		assertEquals(666, year.capitalGainsTaxIncurred(25));				
	}
	@Test
	public void capitalGainsTaxIsIncludedInEndingBalance(){
		savingsAccountYear year = new savingsAccountYear(10000,3000,10);
		int amountWithdrawn = 5000;
		year.withdraw(amountWithdrawn);
		int expectedCapitalGainsTax = 666;
		assertEquals(expectedCapitalGainsTax, year.capitalGainsTaxIncurred(25));
		int expectedStartingBalanceAfterWithdrawals = 10000 - amountWithdrawn - expectedCapitalGainsTax;
		assertEquals((int)(expectedStartingBalanceAfterWithdrawals * 1.10), year.endingBalance(25));
	}
	@Test
	public void interestEarnedIsAProductofStartingBalanceAndInterest(){
		savingsAccountYear year = new savingsAccountYear(10000,3000,10);
		assertEquals(1000, year.interestEarned(25));
	}
	@Test
	@Ignore
	public void endingCapitalGainsIncludesInterestEarned(){
		savingsAccountYear account = new savingsAccountYear(10000,3000,10);
		assertEquals(7000, account.startingCapitalGains());
		assertEquals(4000, account.endingCapitalGains());
		
	}
	private savingsAccountYear newAccount(){
		savingsAccountYear account= newAccount();
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
	



