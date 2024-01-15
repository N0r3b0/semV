package tdo.springtodo.todo;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class ToDoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Nonnull
    private String title;

    private boolean done;

    public ToDoModel() {

    }

    public ToDoModel(Long id, @Nonnull String title, boolean done) {
        this.id = id;
        this.title = title;
        this.done = done;
    }

    public ToDoModel(String title, boolean done) {
        this.title = title;
        this.done = done;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Nonnull
    public String getTitle() {
        return title;
    }

    public void setTitle(@Nonnull String title) {
        this.title = title;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "ToDoModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", done=" + done +
                '}';
    }
}
