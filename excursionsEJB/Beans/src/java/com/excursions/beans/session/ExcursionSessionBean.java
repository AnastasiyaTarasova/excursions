package com.excursions.beans.session;

import com.excursions.beans.entities.Excursion;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by root on 14.05.2016.
 */
@Stateless(name = "ExcursionSessionEJB")
public class ExcursionSessionBean {
    @PersistenceContext(unitName = "ExcursionsPersistenceUnit")
    private EntityManager entityManager;


    public ExcursionSessionBean() {
    }


    public String create(Excursion excursion) {
        entityManager.persist(excursion);
        return excursion.toString();
    }
    public List<Excursion> read() throws Exception {
        TypedQuery query = entityManager
            .createQuery("SELECT e from Excursion as e", Excursion.class)
        ;
        return query.getResultList();
    }
}
