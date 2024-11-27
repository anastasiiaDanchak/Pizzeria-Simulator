package org.example.cppprojectui.models;

import java.util.ArrayList;

public class Menu {

    private static Menu instance;
    private static final Object lock = new Object();
    private ArrayList<Pizza> pizzas;

    // Private constructor to restrict instantiation
    private Menu(ArrayList<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    // Thread-safe method to provide access to the instance
    public static Menu getInstance(ArrayList<Pizza> pizzas) {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {  // Double-checked locking
                    instance = new Menu(pizzas);
                }
            }
        }
        return instance;
    }

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }
}