package org.example.cppprojectui.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.cppprojectui.models.Order;
import org.example.cppprojectui.models.OrderState;
import org.example.cppprojectui.models.OrderStateObserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrdersController implements OrderStateObserver {

    @FXML
    private Pane cashRegistersPane;

    private List<CashRegisterController> cashRegisterControllers = new ArrayList<>();
    private Map<Order, Integer> orderToCashRegisterMap = new HashMap<>();

    public void setCashRegistersAmount(int cashRegistersAmount) {
        addCashRegisters(cashRegistersAmount);
    }

    private void addCashRegisters(int cashRegistersAmount) {
        try {
            int layoutX = 0;
            for (int i = 0; i < cashRegistersAmount; i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/cppprojectui/views/cash-register.fxml"));
                Node cashRegisterNode = loader.load();
                CashRegisterController cashRegisterController = loader.getController();

                // Зберігаємо контролери кас
                cashRegisterControllers.add(cashRegisterController);

                cashRegisterController.setRegisterLayoutX(layoutX);
                cashRegisterController.setRegisterText(i + 1);

                cashRegistersPane.getChildren().add(cashRegisterNode);
                layoutX += 382;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addOrderToCashRegister(int cashRegisterIndex, Order order) {
        if (cashRegisterIndex < cashRegisterControllers.size()) {
            cashRegisterControllers.get(cashRegisterIndex).addOrder(order);
            order.setStateObserver(this);
            orderToCashRegisterMap.put(order, cashRegisterIndex);
        } else {
            System.out.println("Invalid cash register index");
        }
    }

    public void deleteOrderfromCashRegister(int cashRegisterIndex, Order order) {
        if (cashRegisterIndex < cashRegisterControllers.size()) {
            cashRegisterControllers.get(cashRegisterIndex).removeOrder(order.getOrderNumber());
        } else {
            System.out.println("Invalid cash register index");
        }
    }

    @Override
    public void onStateChanged(Order order, OrderState newState) {
        if (newState == OrderState.Done) {
                Integer cashRegisterIndex = orderToCashRegisterMap.get(order);
                if (cashRegisterIndex != null) {
                    deleteOrderfromCashRegister(cashRegisterIndex, order);
                    orderToCashRegisterMap.remove(order);
                    System.out.println(orderToCashRegisterMap);
                    order.removeStateObserver();
                } else {
                    System.out.println("---------------------------------------------------------------Invalid cash register index");
                }
        }
    }
}

