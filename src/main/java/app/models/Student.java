package app.models;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;

@Entity
@DiscriminatorColumn(name = "student")
public class Student extends Person {

    private String register;

}
