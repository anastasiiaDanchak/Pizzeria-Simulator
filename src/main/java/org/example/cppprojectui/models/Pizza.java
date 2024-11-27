package org.example.cppprojectui.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Pizza {
    private String name;
    private int cookingTime;
    private SimpleObjectProperty<PizzaState> state;

    public Pizza(String name, PizzaState state, int cookingTime) {
        this.name = name;
        this.state = new SimpleObjectProperty<>(state);
        this.cookingTime = cookingTime;
    }

    public ObjectProperty<PizzaState> stateProperty() {
        return state;
    }

    public String getName(){
        return name;
    }

    public PizzaState getState() {
        return state.get();
    }

    public void setState(PizzaState state) {
        this.state.set(state);
    }

    public int getCookingTime(){
        return cookingTime;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
