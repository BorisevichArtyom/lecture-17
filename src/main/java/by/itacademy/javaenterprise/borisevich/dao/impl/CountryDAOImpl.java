package by.itacademy.javaenterprise.borisevich.dao.impl;


import by.itacademy.javaenterprise.borisevich.dao.CountryDAO;
import by.itacademy.javaenterprise.borisevich.entity.Country;
import by.itacademy.javaenterprise.borisevich.exception.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class CountryDAOImpl implements CountryDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryDAOImpl.class);
    private final EntityManager em;

    public CountryDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void addCountry(Country country) throws DAOException {
        try {
            em.getTransaction().begin();
            em.persist(country);
            country.setCountryId(country.getCountryId());
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DAOException(country.toString(), e);
        }
    }

    @Override
    public Country findCountrybyId(Long id) throws DAOException {
        Country country = null;
        try {
            em.getTransaction().begin();
            country = em.find(Country.class, id);
            if (country == null) {
                throw new DAOException(" Cannot find country by this id:" + id);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DAOException(" id:" + id, e);
        }
        return country;
    }

    @Override
    public void updateCountry(Country country) throws DAOException {
        try {
            em.getTransaction().begin();
            em.find(Country.class, country.getCountryId());
            em.merge(country);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DAOException(" country:" + country.toString(), e);
        }
    }

    @Override
    public void deleteCountry(Long id) throws DAOException {
        try {
            em.getTransaction().begin();
            Country country = em.find(Country.class, id);
            em.remove(country);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DAOException(" id:" + id, e);
        }
    }

}
