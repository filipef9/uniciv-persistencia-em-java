package br.edu.uniciv.dao;

import br.edu.uniciv.utils.HibernateUtil;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class DaoGeneric<E> {

    private EntityManager entityManager = HibernateUtil.getEntityManager();

    public void salvar(E entidade) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(entidade);
        transaction.commit();
    }

    public E pesquisar(E entidade) {
        final Object id = HibernateUtil.getPrimaryKey(entidade);
        return (E) entityManager.find(entidade.getClass(), id);
    }

    public E pesquisarPorId(Class<E> clazz, Long id) {
        return (E) entityManager.find(clazz, id);
    }

}
