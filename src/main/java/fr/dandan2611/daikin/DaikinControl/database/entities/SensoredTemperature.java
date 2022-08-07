package fr.dandan2611.daikin.DaikinControl.database.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class SensoredTemperature {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Double innerTemperature;

    @Column(nullable = false)
    private Double innerHumidity;

    @Column(nullable = false)
    private Double outerTemperature;

    @CreationTimestamp
    @Column(updatable = false)
    private Date retrievedAt;

    public SensoredTemperature(double innerTemperature, double innerHumidity, double outerTemperature) {
        this.innerTemperature = innerTemperature;
        this.innerHumidity = innerHumidity;
        this.outerTemperature = outerTemperature;
    }

    protected SensoredTemperature() {

    }

    public Long getId() {
        return id;
    }

    public double getInnerTemperature() {
        return innerTemperature;
    }

    public double getInnerHumidity() {
        return innerHumidity;
    }

    public double getOuterTemperature() {
        return outerTemperature;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setInnerTemperature(double innerTemperature) {
        this.innerTemperature = innerTemperature;
    }

    public void setInnerHumidity(double innerHumidity) {
        this.innerHumidity = innerHumidity;
    }

    public void setOuterTemperature(double outerTemperature) {
        this.outerTemperature = outerTemperature;
    }

}
