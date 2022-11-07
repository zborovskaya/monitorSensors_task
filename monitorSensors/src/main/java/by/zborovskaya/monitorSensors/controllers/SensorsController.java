package by.zborovskaya.monitorSensors.controllers;

import by.zborovskaya.monitorSensors.dto.SensorDTOAddUpdate;
import by.zborovskaya.monitorSensors.dto.SensorDTOGet;
import by.zborovskaya.monitorSensors.models.Sensor;
import by.zborovskaya.monitorSensors.models.SensorType;
import by.zborovskaya.monitorSensors.models.Unit;
import by.zborovskaya.monitorSensors.repositories.SensorsRepository;
import by.zborovskaya.monitorSensors.repositories.TypesRepository;
import by.zborovskaya.monitorSensors.repositories.UnitsRepository;
import by.zborovskaya.monitorSensors.util.ErrorResponse;
import by.zborovskaya.monitorSensors.util.SensorNotFoundException;
import by.zborovskaya.monitorSensors.util.SensorNotValidException;
import by.zborovskaya.monitorSensors.util.SensorValidator;
import io.swagger.annotations.ApiParam;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sensors")
public class SensorsController {

    private SensorsRepository sensorsRepository;
    private final ModelMapper modelMapper;
    private SensorValidator sensorValidator;
    private UnitsRepository unitsRepository;
    private TypesRepository typesRepository;

    @Autowired
    public SensorsController(SensorsRepository sensorsRepository, ModelMapper modelMapper, SensorValidator sensorValidator, UnitsRepository unitsRepository, TypesRepository typesRepository) {
        this.sensorsRepository = sensorsRepository;
        this.modelMapper = modelMapper;
        this.sensorValidator = sensorValidator;
        this.unitsRepository = unitsRepository;
        this.typesRepository = typesRepository;
    }

    @GetMapping()
    public List<SensorDTOGet> getAllSensors() {
        return  sensorsRepository.getAllSensors().stream()
                .map(s->convertToSensor(s))
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> save(@RequestBody @Valid  SensorDTOAddUpdate sensorDTO, BindingResult bindingResult) {
        sensorValidator.validate(sensorDTO,bindingResult);

        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error : errors){
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new SensorNotValidException(errorMsg.toString());
        }
        Sensor sensor =convertToSensor(sensorDTO);
        sensorsRepository.save(sensor);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public SensorDTOGet getById(@PathVariable("id") @ApiParam(defaultValue = "10") int id){
        Sensor sensor = sensorsRepository.getById(id);
        if (sensor == null) {
            throw new SensorNotFoundException();
        }
        return convertToSensor(sensor);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") @ApiParam(defaultValue = "10") int id) {
        Sensor sensor = sensorsRepository.getById(id);
        if (sensor == null) {
            throw new SensorNotFoundException();
        }
        sensorsRepository.delete(id);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") @ApiParam(defaultValue = "10") int id,@RequestBody @Valid SensorDTOAddUpdate sensorDTO,BindingResult bindingResult) {
        Sensor sensorfind = sensorsRepository.getById(id);
        if (sensorfind == null) {
            throw new SensorNotFoundException();
        }
        sensorValidator.validate(sensorDTO,bindingResult);

        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error : errors){
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new SensorNotValidException(errorMsg.toString());
        }
        Sensor sensor =convertToSensor(sensorDTO);
        sensor.setId(id);
        sensorsRepository.update(sensor);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/searchByTitle")
    public List<SensorDTOGet> findByName(@RequestParam("title") @ApiParam(defaultValue = "barometer")String title) {
        return sensorsRepository.findByName(title.trim())
                .stream()
                .map(s->(convertToSensor(s)))
                .collect(Collectors.toList());
    }


    @GetMapping("/searchByModel")
    public List<SensorDTOGet> findByModel(@RequestParam("model") @ApiParam(defaultValue = "ac-23")String model) {
        return sensorsRepository.findByModel(model.trim())
                .stream()
                .map(s->(convertToSensor(s)))
                .collect(Collectors.toList());
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(SensorNotFoundException e) {
        ErrorResponse response = new ErrorResponse(
                "Sensor with this id wasn't found!", System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(SensorNotValidException e) {
        ErrorResponse response = new ErrorResponse(
                e.getMessage(), System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(NoResultException e) {
        ErrorResponse response = new ErrorResponse(
                "This type or unit does not exist", System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    public Sensor convertToSensor(SensorDTOAddUpdate sensorDTOAddUpdate){
        Sensor sensor = this.modelMapper.map(sensorDTOAddUpdate, Sensor.class);
        SensorType sensorType = typesRepository.getByName(sensorDTOAddUpdate.getType());
        sensor.setType(sensorType);
        String unitStr  = sensorDTOAddUpdate.getUnit();
        Unit unit = null;
        if(unitStr!=null && !unitStr.isEmpty()) {
            unit = unitsRepository.getByName(sensorDTOAddUpdate.getUnit());
        }
        sensor.setUnit(unit);
        return sensor;
    }
    public SensorDTOGet convertToSensor(Sensor sensor){
        SensorDTOGet sensorDTOAGet = this.modelMapper.map(sensor, SensorDTOGet.class);
        sensorDTOAGet.setType(sensor.getType().getName());
        Unit unit = sensor.getUnit();
        if(unit!=null) {
            sensorDTOAGet.setUnit(sensor.getUnit().getName());
        }
        return sensorDTOAGet;
    }
}
