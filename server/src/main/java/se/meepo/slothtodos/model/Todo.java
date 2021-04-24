package se.meepo.slothtodos.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Todo {
    @Id
    @GeneratedValue
    public int id;

    @NotBlank(message = "Description is missing.")
    public String description;
    @NotBlank(message = "Title is missing.")
    public String title;

    @Valid
    @OneToMany(mappedBy = "todoId", cascade = CascadeType.ALL)
    public List<Task> tasks = new ArrayList<>();

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Todo() {}

    public Todo(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
}
