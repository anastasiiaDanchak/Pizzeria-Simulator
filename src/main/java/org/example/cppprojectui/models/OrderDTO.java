package org.example.cppprojectui.models;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class OrderDTO implements Serializable {
    private List<Pizza> pizzas;
    private String clientName;
    private String orderState;
    private int orderId;
    private String  orderUUID;

    public OrderDTO() {}

    public String getOrderUUID() {
        return orderUUID;
    }

    public void setOrderUUID(String orderUUID) {
        this.orderUUID = orderUUID;
    }

    public OrderDTO(Order order) {
        this.pizzas = order.getPizzas();
        this.clientName = order.getClient() != null ? order.getClient().getName() : null;
        this.orderState = order.getState().toString();
        this.orderId = order.getOrderNumber();
    }

    // Getters and setters
    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
