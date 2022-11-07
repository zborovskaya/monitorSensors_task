package by.zborovskaya.monitorSensors.util;

import by.zborovskaya.monitorSensors.dto.Range;
import by.zborovskaya.monitorSensors.dto.SensorDTOAddUpdate;
import by.zborovskaya.monitorSensors.models.Sensor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(target==null){
            errors.rejectValue("range","400",
                    "Range can not be null" );
        }
        SensorDTOAddUpdate sensor = (SensorDTOAddUpdate) target;
        Range range = sensor.getRange();
        if(range.getFrom()>= range.getTo()){
            errors.rejectValue("range","400",
                    "Range to must be greater than range from" );
        }

    }
}