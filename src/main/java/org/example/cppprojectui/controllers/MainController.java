package org.example.cppprojectui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import org.example.cppprojectui.data.FirebaseOrderService;
import org.example.cppprojectui.models.CashRegister;
import org.example.cppprojectui.models.ClientGenerator;
import org.example.cppprojectui.models.Menu;
import org.example.cppprojectui.models.Pizza;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainController {

    @FXML
    private AnchorPane contentPane;

    private int registersAmount;
    private int cooksAmount;
    private OrdersController ordersController;

    // Зберігаємо завантажені view
    private Node ordersView;
    private Node cooksView;

    @FXML
    public void handleOrdersButtonClick(ActionEvent event) {
        if (ordersView == null) {
            loadOrdersView();
        } else {
            contentPane.getChildren().clear();
            contentPane.getChildren().add(ordersView);
        }
    }

    @FXML
    public void handleCooksButtonClick(ActionEvent event) {
        if (cooksView == null) {
            loadCooksView();
        } else {
            contentPane.getChildren().clear();
            contentPane.getChildren().add(cooksView);
        }
    }

    private void loadCooksView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/cppprojectui/views/cooks-view.fxml"));
            cooksView = loader.load();

            CooksController cooksController = loader.getController();
            cooksController.setCooksAmount(cooksAmount);

            contentPane.getChildren().clear();
            contentPane.getChildren().add(cooksView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadOrdersView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/cppprojectui/views/orders-view.fxml"));
            ordersView = loader.load();

            ordersController = loader.getController();
            ordersController.setCashRegistersAmount(registersAmount);

            contentPane.getChildren().clear();
            contentPane.getChildren().add(ordersView);

            initCashRegister();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initCashRegister() {
        int numberOfCashRegisters = registersAmount;
        FirebaseOrderService.getInstance().initDB(numberOfCashRegisters);
        List<CashRegister> cashRegisters = new ArrayList<>();

        for (int i = 0; i < numberOfCashRegisters; i++) {
            CashRegister cashRegister = new CashRegister(i);
            cashRegisters.add(cashRegister);
            Thread cashRegisterThread = new Thread(cashRegister);
            cashRegisterThread.start();
        }

        ClientGenerator clientGenerator = new ClientGenerator(cashRegisters, ordersController);
        Thread clientGeneratorThread = new Thread(clientGenerator);
        clientGeneratorThread.start();
    }

    public void setData(int registersAmount, int cooksAmount, ArrayList<Pizza> pizzas) {
        this.registersAmount = registersAmount;
        this.cooksAmount = cooksAmount;

        Menu menu = Menu.getInstance(pizzas);
        if (contentPane != null) {
            loadOrdersView();
        }
    }
}