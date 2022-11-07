package by.zborovskaya.monitorSensors.controllers;

import by.zborovskaya.monitorSensors.models.Unit;
import by.zborovskaya.monitorSensors.repositories.UnitsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/units")
public class UnitsController {
    private UnitsRepository unitsRepository;

    @Autowired
    public UnitsController(UnitsRepository unitsRepository) {
        this.unitsRepository = unitsRepository;
    }

    @GetMapping()
    public List<Unit> getAll() {
        return  unitsRepository.getAllUnits();
    }
}
