package br.edu.uniciv.dao;

import br.edu.uniciv.utils.HibernateUtil;

import java.util.List;

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

    public E updateMerge(E entidade) {
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        final E entityUpdated = entityManager.merge(entidade);
        transaction.commit();

        return entityUpdated;
    }

    public E pesquisar(E entidade) {
        final Object id = HibernateUtil.getPrimaryKey(entidade);
        return (E) entityManager.find(entidade.getClass(), id);
    }

    public E pesquisarPorId(Class<E> clazz, Long id) {
        return (E) entityManager.find(clazz, id);
    }

    public void deletarPorId(E entidade) {
        final EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        final Object id = HibernateUtil.getPrimaryKey(entidade);
        entityManager.createNativeQuery("delete from " + entidade.getClass().getSimpleName().toLowerCase() + " where id = " + id)
            .executeUpdate();
        entityManager.flush();
        entityManager.clear();
        transaction.commit();
    }

    public List<E> listar(Class<E> entidade) {
        return entityManager.createQuery("from " + entidade.getName())
            .getResultList(); 

    }

}
