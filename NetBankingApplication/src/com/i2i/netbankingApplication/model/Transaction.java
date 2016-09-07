package com.i2i.netbankingApplication.model;

public class Transaction {
    private int id;
    private String accountNumber;
	private String debitAccountId;
    private String criditAccountId;
    private double amount;
    private String timeStamp;
    private String userId;
    private String status;
    
    public Transaction(int id, String debitAccountId, String criditAccountId,
        double amount, String timeStamp, String status) {
		this.id = id;
		this.debitAccountId = debitAccountId;
		this.criditAccountId = criditAccountId;
		this.amount = amount;
		this.timeStamp = timeStamp;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getDebitAccountId() {
		return debitAccountId;
	}

	public void setDebitAccountId(String debitAccountId) {
		this.debitAccountId = debitAccountId;
	}

	public String getCriditAccountId() {
		return criditAccountId;
	}

	public void setCriditAccountId(String criditAccountId) {
		this.criditAccountId = criditAccountId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
    
    
}
