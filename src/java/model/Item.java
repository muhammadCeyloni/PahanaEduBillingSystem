package model;

public class Item {
    private int itemId;
    private String title;
    private String author;
    private double price;
    private int quantity;

    public Item() {}

    public Item(int itemId, String title, String author, double price, int quantity) {
        this.itemId = itemId;
        this.title = title;
        this.author = author;
        this.price = price;
        this.quantity = quantity;
    }

    // convenience ctor used by servlet if author not provided
    public Item(int itemId, String title, double price, int quantity) {
        this(itemId, title, null, price, quantity);
    }

    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
