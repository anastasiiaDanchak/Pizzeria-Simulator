package org.example.cppprojectui.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.cppprojectui.models.Order;

import java.io.IOException;

public class CashRegisterController {

    @FXML
    private Pane register_pane;

    @FXML
    private Text registerID;

    @FXML
    private AnchorPane anchor;

    @FXML
    private VBox ordersVBox;

    public void setRegisterLayoutX(int layoutX){
        register_pane.setLayoutX(layoutX);
    }

    public void setRegisterText(int n){
        registerID.setText("Checkout #" + n);

        anchor.setId("checkout" + n);

    }
    @FXML
    public void addOrder(Order order) {
        Platform.runLater(() -> {
            try {
                // Завантажуємо шаблон замовлення
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/cppprojectui/views/order_item.fxml"));
                Node orderNode = loader.load();

                // Налаштування даних замовлення
                OrderItemController controller = loader.getController();
                controller.setData(order);

                // Збереження контролера в userData
                orderNode.setUserData(controller);

                // Додавання замовлення до VBox
                ordersVBox.getChildren().add(orderNode);


            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    public void removeOrder(int orderId) {
        Platform.runLater(() -> {
            for (Node node : ordersVBox.getChildren()) {
                if (node != null) {
                    OrderItemController controller = (OrderItemController) node.getUserData();
                    if (controller != null && controller.getOrderId() == orderId) {
                        ordersVBox.getChildren().remove(node);
                        break;
                    }
                }
            }
        });
    }


}
