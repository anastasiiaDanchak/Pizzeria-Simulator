package org.example.cppprojectui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.cppprojectui.models.Cook;
import org.example.cppprojectui.models.CookState;
import org.example.cppprojectui.models.CookStrategy;

public class CooksController {

    @FXML
    private TableView<Cook> cookTable;

    @FXML
    private TableColumn<Cook, String> nameColumn;

    @FXML
    private TableColumn<Cook, CookStrategy> strategyColumn;

    @FXML
    private TableColumn<Cook, CookState> stateColumn;

    private ObservableList<Cook> cookList;

    private int cooksAmount; // кількість кухарів

    public void setCooksAmount(int cooksAmount) {
        this.cooksAmount = cooksAmount;

        // Ініціалізуємо колонки
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        strategyColumn.setCellValueFactory(new PropertyValueFactory<>("strategy"));
        stateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));

        // Заповнюємо таблицю даними
        cookList = FXCollections.observableArrayList(
                new Cook("Victor Borysenko", CookStrategy.Only_Dough, CookState.Active),
                new Cook("Karina Ryndzak", CookStrategy.Full_Chef, CookState.Active),
                new Cook("Roman Demchuk", CookStrategy.Only_Ingredients, CookState.Active),
                new Cook("Olha Lupan", CookStrategy.Only_Baking, CookState.Active),
                new Cook("Andrii Horak", CookStrategy.Full_Chef, CookState.Active)
        );

        for(int i = 0; i < 5 - cooksAmount; i++) {
            cookList.removeLast();
        }

        cookTable.setItems(cookList);
    }
    @FXML
    public void initialize() {

    }

}
