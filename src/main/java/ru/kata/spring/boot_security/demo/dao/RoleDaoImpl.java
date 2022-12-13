package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entities.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> index() {
        Query query = entityManager.createQuery("from Role");
        return query.getResultList();
    }


    @Override
    public Role getRole(Long id) {
        return entityManager.find(Role.class,id);
    }
}
