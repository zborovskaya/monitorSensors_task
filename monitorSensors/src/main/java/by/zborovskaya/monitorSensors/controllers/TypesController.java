package by.zborovskaya.monitorSensors.controllers;

import by.zborovskaya.monitorSensors.models.SensorType;
import by.zborovskaya.monitorSensors.repositories.TypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/types")
public class TypesController {
    private TypesRepository typesRepository;

    @Autowired
    public TypesController(TypesRepository typesRepository) {
        this.typesRepository = typesRepository;
    }

    @GetMapping()
    public List<SensorType> getAllSensors() {
        return  typesRepository.getAllTypes();
    }
}
