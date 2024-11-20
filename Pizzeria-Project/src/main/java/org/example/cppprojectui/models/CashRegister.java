package org.example.cppprojectui.models;

import java.util.LinkedList;
import java.util.Queue;

public class CashRegister implements Runnable {
    private int id;

    private OrderCounter orderId = new OrderCounter();
    private final Queue<Client> clientQueue;
    private boolean isProccesing = false;

    public int getClientQueueSize() {
        if (isProccesing) {
            return clientQueue.size() + 1;
        }
        return clientQueue.size();
    }

    public CashRegister(int id) {
        this.id = id;
        this.clientQueue = new LinkedList<>();
    }

    public int getId() {
        return id;
    }

    public int getOrderId() {
        return orderId.getOrderAmount();
    }

    // Метод для додавання клієнта до черги
    public void addClient(Client client) {
        synchronized (clientQueue) {
            clientQueue.add(client);
            clientQueue.notify();
            orderId.AddToCount();// Повідомляємо, що з'явився новий клієнт
        }
    }

    @Override
    public void run() {
        while (true) {
            Client client;
            synchronized (clientQueue) {
                while (clientQueue.isEmpty()) {
                    try {
                        System.out.println("Каса " + id + " чекає на клієнтів...");
                        clientQueue.wait(); // Чекаємо на нових клієнтів
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return; // Завершення потоку
                    }
                }
                client = clientQueue.poll(); // Забираємо клієнта з черги
            }
            processClient(client);
        }
    }

    // Симуляція обробки клієнта
    private void processClient(Client client) {
        isProccesing = true;

        System.out.println("Каса " + id + " обробляє клієнта: " + client.getName());
        Order order = client.getOrder();
        for(Pizza pizza : order.getPizzas()) {
            Cook.preparePizza(pizza);
            order.getState();
        }
        isProccesing = false;

        System.out.println("Каса " + id + " завершила обробку клієнта: " + client.getName());
        System.out.println(order.getState());
    }

    public boolean isProccesing() {
        return isProccesing;
    }
}
