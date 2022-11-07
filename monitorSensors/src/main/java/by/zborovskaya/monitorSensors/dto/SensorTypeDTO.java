package by.zborovskaya.monitorSensors.dto;

public class SensorTypeDTO {

    private int id;

    private String name;

    public SensorTypeDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public SensorTypeDTO() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
