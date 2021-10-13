package at.htl.zoomanagement.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

@Entity
@XmlRootElement
public class FoodRation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Food food;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Animal animal;
    private double amount;
    private LocalDate date;

    public FoodRation() {
        food = new Food();
        animal = new Animal();
        date = LocalDate.of(1, 1, 1);
        amount = 0;
    }

    public FoodRation(int amount) {
        this();
        this.amount = amount;
    }

    public FoodRation(Food food, Animal animal, double amount) {
        this();
        this.food = food;
        this.animal = animal;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
