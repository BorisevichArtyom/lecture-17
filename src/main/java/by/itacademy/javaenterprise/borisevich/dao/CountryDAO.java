package by.itacademy.javaenterprise.borisevich.dao;


import by.itacademy.javaenterprise.borisevich.entity.Country;
import by.itacademy.javaenterprise.borisevich.exception.DAOException;

import java.util.List;

public interface CountryDAO {

    void addCountry(Country country) throws DAOException;

    Country findCountrybyId(Long id) throws DAOException;

    void updateCountry(Country country) throws DAOException;

    void deleteCountry(Long id) throws DAOException;


}
