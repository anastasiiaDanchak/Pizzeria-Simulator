package org.example.cppprojectui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.cppprojectui.models.Order;
import org.example.cppprojectui.models.Pizza;

import java.util.ArrayList;

public class OrderItemController {
    @FXML
    private Text orderText;

    @FXML
    private Button detailsButton;

    private int orderId;
    private String clientName;
    private ObservableList<Pizza> pizzaList; // Зберігаємо список піц

    public void setData(Order order) {
        this.clientName = order.getClient().getName();
        this.orderId = order.getOrderNumber();
        this.pizzaList = order.getPizzas(); // Зберігаємо піци
        order.setItemController(this);

        setOrderText(clientName + ", " + order.getState());

        detailsButton.setOnAction(event -> {
            openOrderDetailsWindow();
            System.out.println("Details button clicked for Order ID: " + orderId);
        });
    }

    public void setOrderText(String orderText) {
        this.orderText.setText(orderText);
    }

    @FXML
    private void openOrderDetailsWindow() {
        try {
            // Завантаження FXML для модального вікна
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/cppprojectui/views/orderdetails-view.fxml"));
            Parent root = loader.load();

            OrderdetailsController controller = loader.getController();

            // Встановлюємо дані
            controller.setOrderText("Order: " + clientName + " Details");
            controller.setOrderDetails(pizzaList); // Передаємо збережений список піц

            // Створення нового вікна
            Stage orderDetailsStage = new Stage();
            orderDetailsStage.initModality(Modality.APPLICATION_MODAL);
            orderDetailsStage.setTitle("Order: " + orderText.getText() + " Details");
            orderDetailsStage.setScene(new Scene(root));

            // Показуємо вікно
            orderDetailsStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getOrderId() {
        return orderId;
    }
}