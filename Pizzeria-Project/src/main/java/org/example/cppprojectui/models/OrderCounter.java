package org.example.cppprojectui.models;

public class OrderCounter {
    private int orderAmount;

    public int getOrderAmount() {
        return orderAmount;
    }

    public void AddToCount() { orderAmount++; }
}
