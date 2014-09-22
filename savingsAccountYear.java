package com.gitau.tdd;

public class savingsAccountYear {
	int startingBalance = 0;
	int capitalGainsAmount=0;
	int interestRate =0;
	int totalWithdrawn=0;
	int startingPrincipal;
	
	
	
	public savingsAccountYear(int startingBalance,int startingPrincipal, int interestRate) {
		this.startingBalance=startingBalance;
		this.startingPrincipal = startingPrincipal;
		this.capitalGainsAmount=startingBalance -startingPrincipal;
		this.interestRate =interestRate;
	}

	public int startingBalance() {
		return startingBalance;
	}

	public int interestRate() {
		return interestRate;
	}
	public int interestEarned(int capitalGainsTaxRate) {
		return (startingBalance-totalwithdrawals()- capitalGainsTaxIncurred(capitalGainsTaxRate)) * interestRate/100;
	}
	public void withdraw(int amount) {
		this.totalWithdrawn += amount;			
	}
	public int totalwithdrawals() {
		return totalWithdrawn;		
	}
	public int endingBalance(int capitalGainsTaxRate) {
		int modifiedStart = startingBalance-totalwithdrawals() - capitalGainsTaxIncurred(capitalGainsTaxRate);
		return modifiedStart + interestEarned(capitalGainsTaxRate);
	}
	public savingsAccountYear nextYear(int capitalGainsTaxRate){
		return new savingsAccountYear(this.endingBalance(capitalGainsTaxRate), 0,interestRate);		
	}
	public int startingPrincipal() {
		return startingPrincipal;
	}
	public int endingPrincipal() {
		int result = startingPrincipal() - totalwithdrawals();
		return Math.max(0, result);
	}
	public int capitalGainsWithdrawn() {
		int result = -1 *(startingPrincipal()-totalwithdrawals());
		return Math.max(0, result);
	}


	public int capitalGainsTaxIncurred(int capitalGainsTaxRate) {
		double dblTaxRate = capitalGainsTaxRate/100.0;
		double dblCapGains = capitalGainsWithdrawn();
		return (int) ((dblCapGains / (1-dblTaxRate))-dblCapGains);
		
	}
	public int startingCapitalGains() {
		return startingBalance() - startingPrincipal();
	}

	public int endingCapitalGains() {
		return (startingBalance * (interestRate/100))+startingCapitalGains();
	}

	public int totalWithdrawnIncludingCapitalGainsTaxWithdrawn(int capitalGainsTaxRate) {
		return totalwithdrawals() + capitalGainsTaxIncurred(capitalGainsTaxRate);
	}

	
	}

	
	


