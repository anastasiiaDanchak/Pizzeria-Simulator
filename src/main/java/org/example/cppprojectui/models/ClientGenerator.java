package org.example.cppprojectui.models;

import org.example.cppprojectui.controllers.OrdersController;
import org.example.cppprojectui.data.DataService;
import org.example.cppprojectui.data.FirebaseOrderService;

import java.util.ArrayList;
import java.util.List;

public class ClientGenerator implements Runnable {

    private final List<CashRegister> cashRegisters;
    OrdersController ordersController;

    // Конструктор приймає список кас
    public ClientGenerator(List<CashRegister> cashRegisters, OrdersController ordersController) {
        this.cashRegisters = cashRegisters;
        this.ordersController = ordersController;
    }

    // Метод створення клієнта
    private static Client createClient() {
        List<String> names = DataService.getNamesList();

        int randomIndex = (int) (Math.random() * names.size());
        return new Client(names.get(randomIndex));
    }

    @Override
    public void run() {
        while (true) {
            Client newClient = createClient();
            System.out.println("Генератор створив клієнта: " + newClient.getName());
            CashRegister selectedCashRegister = newClient.chooseCashRegister(cashRegisters);
            newClient.createOrder(selectedCashRegister.getOrderId());

            selectedCashRegister.addClient(newClient);
            FirebaseOrderService.getInstance().saveOrderAsync(selectedCashRegister.getId(),newClient.getOrderDTO());
            System.out.println("Клієнт " + newClient.getName() + " доданий до каси " + selectedCashRegister.getId());


            ordersController.addOrderToCashRegister(selectedCashRegister.getId(), newClient.getOrder());
            System.out.println(newClient.getOrder());;
            try {
                // Затримка між створенням клієнтів
                Thread.sleep(4000); // 10 секунд
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Генератор клієнтів зупинено.");
                return;
            }
        }
    }
}
