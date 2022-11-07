package by.zborovskaya.monitorSensors.repositories;

import by.zborovskaya.monitorSensors.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Optional;


@Repository
public class PeopleRepository {
    private EntityManager entityManager;

    @Autowired
    public PeopleRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public Optional<Person> findByUsername(String username){
        Person person = null;
        try {
            person = (Person) entityManager.createNativeQuery("SELECT * FROM users WHERE username =:name", Person.class)
                    .setParameter("name", username).getSingleResult();
        }catch (NoResultException ex){}
        return Optional.ofNullable(person);
    }

    @Transactional
    public void save(Person person){
        entityManager.persist(person);
    }
}
