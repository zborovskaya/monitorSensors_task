package by.zborovskaya.monitorSensors.util;

public class SensorNotValidException extends RuntimeException{
    public SensorNotValidException(String msg){
        super(msg);
    }
}
