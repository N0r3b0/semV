package tdo.springtodo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Date;

@Entity
public class Task {

    @Id
    private Long idt;
    private String title;
    private Date dueDate;
    private boolean isCompleted;


    public Long idt() {
        return idt;
    }

    public void idt(Long idt) {
        this.idt = idt;
    }
}
