package by.itacademy.javaenterprise.borisevich.dao.impl;

import by.itacademy.javaenterprise.borisevich.dao.CapitalDAO;
import by.itacademy.javaenterprise.borisevich.entity.Capital;
import by.itacademy.javaenterprise.borisevich.exception.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
@Component
public class CapitalDAOImpl implements CapitalDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(CapitalDAOImpl.class);
    private final EntityManager em;

    public CapitalDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void addCapital(Capital capital) throws DAOException {
        if (capital == null) {
            throw new DAOException("Capital is null!");
        }
        try {
            em.getTransaction().begin();
            em.persist(capital);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DAOException(capital.toString(), e);
        }
    }

    @Override
    public Capital findCapitalbyId(Long id) throws DAOException {
        Capital capital = null;
        try {
            em.getTransaction().begin();
            capital = em.find(Capital.class, id);
            if (capital == null) {
                throw new DAOException("Cannot find capital by this id:" + id);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DAOException(" id:" + id, e);
        }
        return capital;
    }

    @Override
    public void updateCapital(Capital capital) throws DAOException {
        try {
            em.getTransaction().begin();
            em.find(Capital.class, capital.getCountryId());
            em.merge(capital);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DAOException(" capital:" + capital.toString(), e);
        }
    }

    @Override
    public void deleteCapital(Long id) throws DAOException {
        try {
            em.getTransaction().begin();
            Capital capital = em.find(Capital.class, id);
            em.remove(capital);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DAOException(" id:" + id, e);
        }
    }

}
