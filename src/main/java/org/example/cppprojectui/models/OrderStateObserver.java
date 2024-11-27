package org.example.cppprojectui.models;

public interface OrderStateObserver {
    void onStateChanged(Order order, OrderState newState);
}
