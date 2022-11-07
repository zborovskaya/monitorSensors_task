package by.zborovskaya.monitorSensors.repositories;

import by.zborovskaya.monitorSensors.models.SensorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class TypesRepository {
    private EntityManager entityManager;

    @Autowired
    public TypesRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Transactional
    public List<SensorType> getAllTypes() {
        String typeSelect = "SELECT s FROM SensorType s";
        TypedQuery<SensorType> query = entityManager.createQuery(typeSelect, SensorType.class);
        return query.getResultList();
    }

    @Transactional
    public SensorType getById(int id) {
        return entityManager.find(SensorType.class, id);
    }

    @Transactional
    public SensorType getByName(String name) {
        return (SensorType) entityManager.createNativeQuery("SELECT * FROM types WHERE name =:typeName", SensorType.class)
                .setParameter("typeName",name).getSingleResult();
    }
}
