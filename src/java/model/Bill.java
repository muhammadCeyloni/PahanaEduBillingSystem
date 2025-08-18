package model;

public class Bill {
    private int id;
    private Customer customer;
    private int units;
    private double amount;

    public Bill() {}

    public Bill(Customer customer, int units, double amount) {
        this.customer = customer;
        this.units = units;
        this.amount = amount;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public int getUnits() { return units; }
    public void setUnits(int units) { this.units = units; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}
