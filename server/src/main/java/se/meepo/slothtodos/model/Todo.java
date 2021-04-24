package se.meepo.slothtodos.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Todo {
    @Id
    @GeneratedValue
    public int id;
    public String title, description;

    @OneToMany(mappedBy = "todoId")
    public List<Task> tasks = new ArrayList<>();

    public Todo() {}

    public Todo(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
}
