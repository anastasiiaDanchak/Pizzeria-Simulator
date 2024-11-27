package org.example.cppprojectui.data;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.example.cppprojectui.models.OrderDTO;
import org.example.cppprojectui.models.OrderState;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class FirebaseOrderService {
    private static FirebaseOrderService instance;

    private FirebaseOrderService() {}

    public static synchronized FirebaseOrderService getInstance() {
        if (instance == null) {
            instance = new FirebaseOrderService();
        }
        return instance;
    }

    public void saveOrderAsync(int cashRegisterId, OrderDTO orderDTO) {
        CompletableFuture.runAsync(() -> {
            System.out.println("Order saved with id" + orderDTO.getOrderId());
            FirebaseInitializer.initializeFirebase();
            DatabaseReference ordersRef = FirebaseDatabase.getInstance()
                    .getReference("cashregisters/" + cashRegisterId + "/orders");

            DatabaseReference newOrderRef = ordersRef.push();

            newOrderRef.setValue(orderDTO, (databaseError, databaseReference) -> {
                if (databaseError != null) {
                    System.err.println("Order save failed: " + databaseError.getMessage());
                } else {
                    System.out.println("Order saved successfully with ID: " + newOrderRef.getKey());
                    orderDTO.setOrderUUID(newOrderRef.getKey());
                }
            });
        });
    }
    public void updateOrder(int cashRegisterId, String orderUUID, OrderDTO updatedOrderDTO) {

        CompletableFuture.runAsync(() -> {
            System.out.println("Updating order with ID: " + orderUUID);
            FirebaseInitializer.initializeFirebase();

            DatabaseReference orderRef = FirebaseDatabase.getInstance()
                    .getReference("cashregisters/" + cashRegisterId + "/orders/" + orderUUID);

            // Оновлення даних замовлення
            orderRef.setValue(updatedOrderDTO, (databaseError, databaseReference) -> {
                if (databaseError != null) {
                    System.err.println("Order update failed: " + databaseError.getMessage());

                } else {
                    System.out.println("Order updated successfully with ID: " + orderUUID);
                    if(Objects.equals(updatedOrderDTO.getOrderState(), "Done")) {
                        deleteOrderAsync(cashRegisterId, orderUUID);
                    }
                }
            });
        });
    }


    public void deleteOrderAsync(int cashRegisterId, String orderUUID) {
        CompletableFuture.runAsync(() -> {
            System.out.println("Order deleted with ID: " + orderUUID);
            FirebaseInitializer.initializeFirebase();
            DatabaseReference orderRef = FirebaseDatabase.getInstance()
                    .getReference("cashregisters/" + cashRegisterId + "/orders/" + orderUUID);

            orderRef.removeValue((databaseError, databaseReference) -> {
                if (databaseError != null) {
                    System.err.println("Order deletion failed: " + databaseError.getMessage());
                } else {
                    System.out.println("Order deleted successfully: " + orderUUID);
                }
            });
        });
    }

    public void initDB(int registersAmount) {
            FirebaseInitializer.initializeFirebase();
            DatabaseReference cashRegistersRef = FirebaseDatabase.getInstance()
                    .getReference("cashregisters");

            cashRegistersRef.removeValue((databaseError, databaseReference) -> {
                if (databaseError != null) {
                    System.err.println("Cash registers clear failed: " + databaseError.getMessage());
                } else {
                    System.out.println("Cash registers cleared successfully");
                    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
                    dbRef.child("numberOfCashRegisters").setValue(registersAmount,(databaseSetError,db) -> {
                        if (databaseError != null) {
                            System.err.println("numberOfCashRegisters save failed: " + databaseError.getMessage());
                        } else {
                            System.out.println("numberOfCashRegisters saved successfully" );
                        }
                    });
                    
                }
            });
    }
}