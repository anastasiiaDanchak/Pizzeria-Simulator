package org.example.cppprojectui.data;

import org.example.cppprojectui.models.Pizza;
import org.example.cppprojectui.models.PizzaState;

import java.util.Arrays;
import java.util.List;

public class DataService {
    public static List<String> getNamesList(){
        return List.of(
                "James", "Mary", "Michael", "Patricia", "Robert", "Jennifer", "John", "Linda", "David", "Elizabeth",
                "William", "Barbara", "Richard", "Susan", "Joseph", "Jessica", "Thomas", "Karen", "Christopher", "Sarah",
                "Charles", "Lisa", "Daniel", "Nancy", "Matthew", "Sandra", "Anthony", "Betty", "Mark", "Ashley", "Donald",
                "Emily", "Steven", "Kimberly", "Andrew", "Margaret", "Paul", "Donna", "Joshua", "Michelle", "Kenneth",
                "Carol", "Kevin", "Amanda", "Brian", "Melissa", "Timothy", "Deborah", "Ronald", "Stephanie", "George",
                "Rebecca", "Jason", "Sharon", "Edward", "Laura", "Jeffrey", "Cynthia", "Ryan"
        );
    }

    public static List<Pizza> getPizzaList() {
        return Arrays.asList(
                new Pizza("Margherita", PizzaState.Awaiting_Preparation, 10),
                new Pizza("Marinara", PizzaState.Awaiting_Preparation, 8),
                new Pizza("Quattro Stagioni", PizzaState.Awaiting_Preparation, 12),
                new Pizza("Carbonara", PizzaState.Awaiting_Preparation, 11),
                new Pizza("Frutti di Mare", PizzaState.Awaiting_Preparation, 13),
                new Pizza("Quattro Formaggi", PizzaState.Awaiting_Preparation, 11),
                new Pizza("Crudo", PizzaState.Awaiting_Preparation, 11),
                new Pizza("Capricious", PizzaState.Awaiting_Preparation, 10),
                new Pizza("Caesar", PizzaState.Awaiting_Preparation, 9),
                new Pizza("Pepperoni", PizzaState.Awaiting_Preparation, 10)
        );
    }

}
