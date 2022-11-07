package by.zborovskaya.monitorSensors.repositories;

import by.zborovskaya.monitorSensors.models.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class SensorsRepository {
    private EntityManager entityManager;

    @Autowired
    public SensorsRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public List<Sensor> getAllSensors() {
        String jpql = "SELECT s FROM Sensor s";
        TypedQuery<Sensor> query = entityManager.createQuery(jpql, Sensor.class);
        return query.getResultList();
    }

    @Transactional
    public void save(Sensor sensor) {
        entityManager.persist(sensor);
    }

    @Transactional
    public Sensor getById(int id) {
        return entityManager.find(Sensor.class, id);
    }

    @Transactional
    public void delete(int id) {
        Sensor sensor = entityManager.find(Sensor.class, id);
        entityManager.remove(sensor);

    }

    @Transactional
    public void update(Sensor sensor) {
        entityManager.merge(sensor);
    }

    @Transactional
    public List<Sensor> findByName(String title) {
        return entityManager.createNativeQuery(
                "SELECT * FROM sensors WHERE title LIKE :sensorTitle",Sensor.class)
                .setParameter("sensorTitle","%"+title+"%").getResultList();
    }

    @Transactional
    public List<Sensor> findByModel(String model) {
        return entityManager.createNativeQuery(
                        "SELECT * FROM sensors WHERE model LIKE :sensorModel",Sensor.class)
                .setParameter("sensorModel","%"+model+"%").getResultList();
    }
}
