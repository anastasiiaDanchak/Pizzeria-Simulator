package org.example.cppprojectui.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.cppprojectui.models.Pizza;
import org.example.cppprojectui.models.PizzaState;

public class OrderdetailsController {
    @FXML
    private Text orderText;

    @FXML
    private TableView<Pizza> orderDetailsTable;

    private ObservableList<Pizza> orderDetails = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Pizza,String> namesColumn;

    @FXML
    private TableColumn<Pizza, PizzaState> statesColumn;

    @FXML
    private void initialize(){
        // Очищаємо таблицю
        orderDetailsTable.getItems().clear();

        // Налаштовуємо колонки
        namesColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        statesColumn.setCellValueFactory(new PropertyValueFactory<>("state"));

        // Встановлюємо початковий список
        orderDetailsTable.setItems(orderDetails);

    }

    @FXML
    private void closeOrderDetailsWindow(){
        Stage stage = (Stage) orderDetailsTable.getScene().getWindow();
        stage.close();
    }

    public void setOrderDetails(ObservableList<Pizza> pizzas) {
        // Очищаємо попередні дані
        orderDetails.clear();
        // Додаємо нові
        orderDetails.addAll(pizzas);
        for (Pizza pizza : pizzas) {
            pizza.stateProperty().addListener((observable, oldValue, newValue) -> {
                orderDetailsTable.refresh();

            });
        }

    }

    public void setOrderText(String orderText){
        this.orderText.setText(orderText);
    }
}