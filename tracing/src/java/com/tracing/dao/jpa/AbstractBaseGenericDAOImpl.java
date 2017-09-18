package com.tracing.dao.jpa;

import com.tracing.dao.BaseGenericDAO;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public abstract class AbstractBaseGenericDAOImpl<T, K extends Serializable>
        implements BaseGenericDAO<T, K> {

    @PersistenceContext(unitName = "tracingPU")
    protected EntityManager em;

    protected Class<T> classType;

    private String entityName;
    private static boolean _refresh = true;

    public static void refresh() {
        _refresh = true;
    }

    @SuppressWarnings("unchecked")
    public AbstractBaseGenericDAOImpl() {
        super();
        this.classType = (Class<T>) getParameterClass(getClass(), 0);
        this.entityName = classType.getSimpleName();

    }

    private static Class<?> getParameterClass(Class<?> clazz, int index) {
        return (Class<?>) (((ParameterizedType) clazz.getGenericSuperclass())
                .getActualTypeArguments()[index]);
    }

    @Override
    public T create(T entity) {
        this.em.persist(entity);
        this.em.flush();
        return entity;

    }

    @Override
    public T update(T entity) {
        this.em.merge(entity);
        this.em.flush();
        return entity;

    }

    @Override
    public void delete(T entity) {
        this.em.remove(em.merge(entity));
    }

    @Override
    public void delete() {
        this.em.createQuery("delete from " + classType.getSimpleName()).executeUpdate();
    }

    @Override
    public T findById(K key) {
        return this.em.find(classType, key);
    }

    @Override
    public Long getMaxId() {
        Long codigoFinal = 0L;
        codigoFinal = (Long) this.em.createQuery("SELECT max(Id) FROM " + classType.getSimpleName()).getResultList().get(0);
        if (codigoFinal == null) {
            codigoFinal = 0L;
        }
        codigoFinal = codigoFinal + 1L;
        return codigoFinal;
    }

    @Override
    public Long getMaxIdDos(String cadena) {
        Long codigoFinal = 0L;
        try {
            //String query = "SELECT max(t." + cadena + ") FROM " + classType.getSimpleName() + " t ";
            codigoFinal = (Long) this.em.createQuery("SELECT max(t." + cadena + ") FROM " + classType.getSimpleName() + " t").getResultList().get(0);
        } catch (Exception e) {
            codigoFinal = null;
        }
        if (codigoFinal == null) {
            codigoFinal = 0L;
        }
        codigoFinal = codigoFinal + 1L;
        return codigoFinal;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAll() {
        Query q = em.createQuery("SELECT distinct t FROM " + classType.getSimpleName()
                + " t");
        List<T> result = q.setHint("eclipselink.refresh", true).getResultList();
        return result;

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAllOrderBy(String orderBy) {
        Query q = em.createQuery("SELECT distinct t FROM " + classType.getSimpleName()
                + " t ORDER BY t." + orderBy);
        List<T> result = q.setHint("eclipselink.refresh", true).getResultList();
        return result;

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findByFk(String cadena) {
        Query q = em.createQuery("SELECT distinct t FROM " + classType.getSimpleName()
                + " t " + cadena);
        List<T> result = q.setHint("eclipselink.refresh", true).getResultList();
        return result;

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object> findByFkObject(String cadena) {
        Query q = em.createQuery("SELECT distinct t FROM " + classType.getSimpleName()
                + " t " + cadena);
        List<T> result = q.getResultList();
        return (List<Object>) result;

    }

    @Override
    public Long getCountId(String cadena) {
        Long codigo = 0L;
        Query q = em.createQuery(cadena);
        codigo = (Long) q.getResultList().get(0);
        return codigo;
    }

}
