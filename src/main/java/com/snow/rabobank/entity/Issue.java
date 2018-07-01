package com.snow.rabobank.entity;

public class Issue {

	private String accountNumber;

	private int count;

	public Issue(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void countPlusOne() {
		this.count++;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getAccountNumber() == null) ? 0 : getAccountNumber().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Issue other = (Issue) obj;
		if (getAccountNumber() == null) {
			if (other.getAccountNumber() != null)
				return false;
		} else if (!getAccountNumber().equals(other.getAccountNumber()))
			return false;
		return true;
	}

}
