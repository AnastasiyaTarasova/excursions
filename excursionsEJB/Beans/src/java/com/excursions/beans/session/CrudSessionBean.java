package com.excursions.beans.session;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class CrudSessionBean {
    @PersistenceContext(unitName = "ExcursionsPersistenceUnit")
    private EntityManager em;


    public <T> T create(T t) {
        em.persist(t);
        return t;
    }
    public <T> T find(Class<T> type, Object id) {
        return em.find(type, id);
    }
    public <T> void delete(T t) {
        t = em.merge(t);
        em.remove(t);
    }
    public <T> T update(T t) {
        return em.merge(t);
    }
    public List findWithNamedQuery(String queryName) {
        return em.createNamedQuery(queryName)
            .getResultList()
        ;
    }
    public List findWithNamedQuery(String queryName, int resultLimit) {
        return em.createNamedQuery(queryName)
            .setMaxResults(resultLimit)
            .getResultList()
        ;
    }
    public List findWithNamedQuery(String namedQueryName,
                                   Map<String, Object> parameters) {
        return findWithNamedQuery(namedQueryName, parameters, 0);
    }
    public List findWithNamedQuery(String namedQueryName,
                                   Map<String, Object> parameters,
                                   int resultLimit) {
        Query query = this.em.createNamedQuery(namedQueryName);
        if(resultLimit > 0) {
            query.setMaxResults(resultLimit);
        }
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }
    @SuppressWarnings("unchecked")
    public <T>  List<T> findWithNativeQuery(String sql, Class<T> type) {
        return em.createNativeQuery(sql, type).getResultList();
    }
}
