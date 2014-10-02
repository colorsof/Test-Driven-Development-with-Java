package com.gitau.tdd;

public class StockMarketYear{
	int startingBalance;
	int capitalGainsAmount;
	int interestRate;
	int totalWithdrawals;
	int startingPrincipal;
	
	
	
	public StockMarketYear(int startingBalance,int startingPrincipal, int interestRate) {
		this.startingBalance=startingBalance;
		this.startingPrincipal = startingPrincipal;
		this.capitalGainsAmount=startingBalance -startingPrincipal;
		this.interestRate =interestRate;
		this.totalWithdrawals = 0;
	}

	public int startingBalance() {
		return startingBalance;
	}

	public int interestRate() {
		return interestRate;
	}
	public int interestEarned(int capitalGainsTaxRate) {
		return (startingBalance-totalWithdrawn(capitalGainsTaxRate)) * interestRate/100;
	}
	public void withdraw(int amount) {
		this.totalWithdrawals += amount;			
		
	}
	public int capitalGainsWithdrawn() {
		int result = -1 *(startingPrincipal()-totalWithdrawals);
		return Math.max(0, result);
	}


	public int capitalGainsTaxIncurred(int capitalGainsTaxRate) {
		double dblTaxRate = capitalGainsTaxRate/100.0;
		double dblCapGains = capitalGainsWithdrawn();
		return (int) ((dblCapGains / (1-dblTaxRate))-dblCapGains);
		
	}
	public int totalWithdrawn(int capitalGainsTaxRate) {
		return totalWithdrawals + capitalGainsTaxIncurred(capitalGainsTaxRate);
	}
	public int startingPrincipal() {
		return startingPrincipal;
	}
	
	public int endingPrincipal() {
		int result = startingPrincipal() - totalWithdrawals;
		return Math.max(0, result);
	}
	
	public int startingCapitalGains() {
		return startingBalance() - startingPrincipal();
	}

	/*public int endingCapitalGains(int capitalGainsTaxRate) {
		return endingBalance(capitalGainsTaxRate)- endingPrincipal();
		//return startingCapitalGains()-capitalGainsWithdrawn()-capitalGainsTaxIncurred(capitalGainsTaxRate)+interestEarned(capitalGainsTaxRate);
	}*/
	public int endingBalance(int capitalGainsTaxRate) {
		int modifiedStart = startingBalance-totalWithdrawn(capitalGainsTaxRate);
		return modifiedStart + interestEarned(capitalGainsTaxRate);
	}
	public StockMarketYear nextYear(int capitalGainsTaxRate){
		return new StockMarketYear(this.endingBalance(capitalGainsTaxRate), this.endingPrincipal(),this.interestRate
				());		
	}
	
	}

	
	

 
