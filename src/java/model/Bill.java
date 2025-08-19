package model;

import java.util.Date;

public class Bill {
    private int billId;
    private int customerId;
    private int units;
    private double amount;
    private Date billDate;

    public Bill() {}

    public Bill(int billId, int customerId, int units, double amount, Date billDate) {
        this.billId = billId;
        this.customerId = customerId;
        this.units = units;
        this.amount = amount;
        this.billDate = billDate;
    }

    public int getBillId() { return billId; }
    public void setBillId(int billId) { this.billId = billId; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public int getUnits() { return units; }
    public void setUnits(int units) { this.units = units; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public Date getBillDate() { return billDate; }
    public void setBillDate(Date billDate) { this.billDate = billDate; }
}
