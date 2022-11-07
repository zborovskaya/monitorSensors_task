package by.zborovskaya.monitorSensors.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SensorDTOGet {
    private int id;
    @Size(min = 1, max = 30, message = "Имя должно быть от 1 до 30 символов длиной")
    private String title;

    @NotEmpty(message = "Название модели не должно быть пустым")
    @Size(min = 1, max = 15, message = "Модель должна быть от 1 до 15 символов длиной")
    private String model;

    @NotEmpty(message = "Тип не может быть пустым")
    private String type;

    private String unit;

    @Size( max = 40, message = "Местоположение должно быть от 1 до 40 символов длиной")
    private String location;

    @Size( max = 200, message = "Описание должно быть от 1 до 200 символов длиной")
    private String description;

    public SensorDTOGet() {

    }

    public SensorDTOGet(int id, String title, String model, String type, String unit, String location, String description, Range range) {
        this.id = id;
        this.title = title;
        this.model = model;
        this.type = type;
        this.unit = unit;
        this.location = location;
        this.description = description;
        this.range = range;
    }

    private Range range;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
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

    public Range getRange() {
        return range;
    }

    public void setRange(Range range) {
        this.range = range;
    }

    @Override
    public String toString() {
        return "SensorDTOGet{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", model='" + model + '\'' +
                ", sensorType='" + type + '\'' +
                ", unit='" + unit + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", range=" + range +
                '}';
    }
}
