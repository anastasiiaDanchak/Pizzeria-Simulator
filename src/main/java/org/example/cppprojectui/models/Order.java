package org.example.cppprojectui.models;

import javafx.collections.ObservableList;
import org.example.cppprojectui.controllers.OrderItemController;

import java.util.ArrayList;

public class Order {
    private ObservableList<Pizza> pizzas;
    private Client client;
    private int orderNumber;
    private OrderState currentState;
    private OrderStateObserver observer;

    private OrderItemController itemController;

    public Order(){
        //delete
    }

    public Order(ObservableList<Pizza> pizzas, Client client, int orderNumber) {
        this.pizzas = pizzas;
        this.client = client;
        this.orderNumber = orderNumber;
        this.currentState = OrderState.Waiting;
    }
    public OrderDTO toDTO() {
        return new OrderDTO(this);
    }

    public void setItemController(OrderItemController itemController) {
        this.itemController = itemController;
    }
    public void setStateObserver(OrderStateObserver observer) {
        this.observer = observer;
    }

    public void removeStateObserver() {
        this.observer = null;
    }

    public ObservableList<Pizza> getPizzas() {
        return pizzas;
    }

    public OrderState getState() {
        int states = 0;
        for(Pizza pizza : pizzas) {
            if(pizza.getState() != PizzaState.Awaiting_Preparation && pizza.getState() != PizzaState.Ready){
                states += pizzas.size() + 1; // if one of the pizzas is not awaiting preparation or not ready
                //states always will be bigger that pizzas.size()
            } else if (pizza.getState() == PizzaState.Ready) {
                ++states;
            }
        }
        OrderState newState;
        if (states == pizzas.size()) {
            newState = OrderState.Done;
        } else if (states > pizzas.size()) {
            newState = OrderState.In_Progress;
        } else {
            newState = OrderState.Waiting;
        }

        if (currentState != newState) {
            currentState = newState;
            notifyStateChanged(newState);
        }

        if(itemController != null){
            itemController.setOrderText(client.getName() + ", " + currentState);
        }
        return currentState;
    }

    private void notifyStateChanged(OrderState newState) {
        if (observer != null) {
            observer.onStateChanged(this, newState);
        }
    }

    @Override
    public String toString() {
        return "Order{" + "pizzas=" + pizzas  + "ID=" + orderNumber + '}';
    }

    public Client getClient() {
        return client;
    }
    public int getOrderNumber() {
        return orderNumber;
    }
}