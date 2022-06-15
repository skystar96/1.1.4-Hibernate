package jm.task.core.jdbc.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
public class User {

    @Override
    public String toString() {
        return super.toString();
    }

    @Id
    private Long ID;

    @Column
    private String NAME;

    @Column
    private String LASTNAME;

    @Column
    private Byte AGE;

    public User() {
    }

    public User(String name, String lastName, Byte age) {
        this.NAME = name;
        this.LASTNAME = lastName;
        this.AGE = age;
    }

    public Long getId() {
        return ID;
    }

    public void setId(Long id) {
        this.ID = id;
    }

    public String getName() {
        return NAME;
    }

    public void setName(String name) {
        this.NAME = name;
    }

    public String getLastName() {
        return LASTNAME;
    }

    public void setLastName(String lastName) {
        this.LASTNAME = lastName;
    }

    public Byte getAge() {
        return AGE;
    }

    public void setAge(Byte age) {
        this.AGE = age;
    }

}
