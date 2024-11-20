package org.example.cppprojectui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
//import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.CheckComboBox;
import org.example.cppprojectui.data.DataService;
import org.example.cppprojectui.models.Pizza;

import java.io.IOException;
import java.util.ArrayList;

public class ConfigController {
    @FXML
    private CheckComboBox<Pizza> pizzasComboBox;
    @FXML
    private ComboBox<Integer> registersComboBox;  // для кас
    @FXML
    private ComboBox<Integer> cooksComboBox;  // для кухарів
    @FXML
    private TextField cookingTimeField;  // для часу приготування
    private DataService dataService = new DataService();

    @FXML
    public void initialize() {
        registersComboBox.setItems(FXCollections.observableArrayList(1, 2, 3, 4));
        cooksComboBox.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5));
        cookingTimeField.setText("30");

        ObservableList<Pizza> pizzaList = FXCollections.observableArrayList(dataService.getPizzaList());
        pizzasComboBox.getItems().addAll(pizzaList);
        pizzasComboBox.getCheckModel().getCheckedItems().addListener(new ListChangeListener<Pizza>() {
            @Override
            public void onChanged(Change<? extends Pizza> change) {
                while (change.next()) {
                    if (change.wasAdded()) {
                        System.out.println("Додано: " + change.getAddedSubList());
                    }
                    if (change.wasRemoved()) {
                        System.out.println("Вилучено: " + change.getRemoved());
                    }
                }
            }
        });
    }

    @FXML
    private AnchorPane bgAnchorPane;

    @FXML
    public void handleCancelButtonClicked(ActionEvent event) {
        //to-do
    }

    @FXML
    public void handleDoneButtonClicked(ActionEvent event) {
        //to-do: функціонал з комбо-боксами з конфігураційного вікна

        //checkComboBox.getCheckModel().getCheckedItems()

        if(registersComboBox.getValue() == null || cooksComboBox.getValue() == null || pizzasComboBox.getCheckModel().getCheckedItems().size() < 1){
            //to-do - повідомлення 'заповніть всі поля!'
            return;
        }
        else{
            int registers = registersComboBox.getValue();
            int cooks = cooksComboBox.getValue();
            ObservableList<Pizza> pizzas = pizzasComboBox.getCheckModel().getCheckedItems();
            loadMainPage(registers, cooks, pizzas);
        }


    }

    private void loadMainPage(int registersAmount, int cooksAmount, ObservableList<Pizza> pizzas) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/cppprojectui/views/main-view.fxml"));
            Node mainPageContent = loader.load();

            // Отримати контролер
            MainController controller = loader.getController();
            controller.setData(registersAmount, cooksAmount, new ArrayList<Pizza>(pizzas));

            bgAnchorPane.getChildren().clear();
            bgAnchorPane.getChildren().add(mainPageContent);
        }
        catch(IOException e){
            System.out.println("IOException caught: " + e.getMessage());
        }

    }



}