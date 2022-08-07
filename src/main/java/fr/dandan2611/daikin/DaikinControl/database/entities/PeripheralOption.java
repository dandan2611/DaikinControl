package fr.dandan2611.daikin.DaikinControl.database.entities;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
@Entity
public class PeripheralOption {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String value;

    @CreationTimestamp
    @Column(nullable = false)
    private Date lastEdited;

    public PeripheralOption(String name, String value, Date lastEdited) {
        this.name = name;
        this.value = value;
        this.lastEdited = lastEdited;
    }

    protected PeripheralOption() {

    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getLastEdited() {
        return this.lastEdited;
    }

    public void setLastEdited(Date lastEdited) {
        this.lastEdited = lastEdited;
    }

}
