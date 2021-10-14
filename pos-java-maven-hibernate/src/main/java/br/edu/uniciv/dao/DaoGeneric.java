package br.edu.uniciv.dao;

import br.edu.uniciv.utils.HibernateUtil;
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

}
