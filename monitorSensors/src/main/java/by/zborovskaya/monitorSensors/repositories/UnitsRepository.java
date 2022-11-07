package by.zborovskaya.monitorSensors.repositories;

import by.zborovskaya.monitorSensors.models.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UnitsRepository {
    private EntityManager entityManager;

    @Autowired
    public UnitsRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Transactional
    public List<Unit> getAllUnits() {
        String unitsSelect = "SELECT u FROM Unit u";
        TypedQuery<Unit> query = entityManager.createQuery(unitsSelect, Unit.class);
        return query.getResultList();
    }

    @Transactional
    public Unit getById(int id) {
        return entityManager.getReference(Unit.class, id);
    }
    @Transactional
    public Unit getByName(String name) {
        return (Unit)entityManager.createNativeQuery("SELECT * FROM units WHERE name =:unitName", Unit.class)
                .setParameter("unitName",name).getSingleResult();
    }
}
