package by.zborovskaya.monitorSensors.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "sensors")
public class Sensor {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 1, max = 30, message = "Имя должно быть от 1 до 30 символов длиной")
    @Column(name = "title")
    private String title;

    @NotEmpty(message = "Название модели не должно быть пустым")
    @Size(min = 1, max = 15, message = "Модель должна быть от 1 до 15 символов длиной")
    @Column(name = "model")
    private String model;

    @ManyToOne
    @JoinColumn(name = "type_id",referencedColumnName ="id" )
    private SensorType sensorType;

    @ManyToOne
    @JoinColumn(name = "unit_id",referencedColumnName ="id" )
    private Unit unit;

    @Size(max = 40, message = "Местоположение должно быть от 1 до 40 символов длиной")
    @Column(name = "location")
    private String location;

    @Size(max = 200, message = "Описание должно быть от 1 до 200 символов длиной")
    @Column(name = "description")
    private String description;

    @Column(name = "range_from")
    private int rangeFrom;

    @Column(name = "range_to")
    private int rangeTo;

    public Sensor(String title, String model, SensorType sensorType, Unit unit, String location, String description, int rangeFrom, int rangeTo) {
        this.id = id;
        this.title = title;
        this.model = model;
        this.sensorType = sensorType;
        this.unit = unit;
        this.location = location;
        this.description = description;
        this.rangeFrom = rangeFrom;
        this.rangeTo = rangeTo;
    }
    public Sensor(int id, String title, String model, SensorType sensorType, Unit unit, String location, String description, int rangeFrom, int rangeTo) {
        this.id = id;
        this.title = title;
        this.model = model;
        this.sensorType = sensorType;
        this.unit = unit;
        this.location = location;
        this.description = description;
        this.rangeFrom = rangeFrom;
        this.rangeTo = rangeTo;
    }

    public Sensor() {
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", model='" + model + '\'' +
                ", type='" + sensorType + '\'' +
                ", unit='" + unit + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", rangeFrom=" + rangeFrom +
                ", rangeTo=" + rangeTo +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public SensorType getType() {
        return sensorType;
    }

    public void setType(SensorType sensorType) {
        this.sensorType = sensorType;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRangeFrom() {
        return rangeFrom;
    }

    public void setRangeFrom(int rangeFrom) {
        this.rangeFrom = rangeFrom;
    }

    public int getRangeTo() {
        return rangeTo;
    }

    public void setRangeTo(int rangeTo) {
        this.rangeTo = rangeTo;
    }
}
