package by.zborovskaya.monitorSensors.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SensorDTOAddUpdate {

    @ApiModelProperty(example = "barometer", required = true)
    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 1, max = 30, message = "Имя должно быть от 1 до 30 символов длиной")
    private String title;

    @ApiModelProperty(example = "ac-23", required = true)
    @NotEmpty(message = "Название модели не должно быть пустым")
    @Size(min = 1, max = 15, message = "Модель должна быть от 1 до 15 символов длиной")
    private String model;

    @ApiModelProperty(example = "Pressure", required = true)
    @NotEmpty(message = "Тип не может быть пустым")
    private String type;

    @ApiModelProperty(example = "bar", required = false)
    private String unit;

    @ApiModelProperty(example = "kitchen", required = false)
    @Size(max = 40, message = "Местоположение должно быть от 1 до 40 символов длиной")
    private String location;

    @ApiModelProperty(example = "description", required = false)
    @Size( max = 200, message = "Описание должно быть от 1 до 200 символов длиной")
    private String description;

    private Range range;

    public SensorDTOAddUpdate() {
    }

    public SensorDTOAddUpdate(String title, String model, String sensorType, String unit, String location, String description, Range range) {
        this.title = title;
        this.model = model;
        this.type = sensorType;
        this.unit = unit;
        this.location = location;
        this.description = description;
        this.range = range;
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
}
