package by.itacademy.javaenterprise.borisevich.dao;

import by.itacademy.javaenterprise.borisevich.entity.Capital;
import by.itacademy.javaenterprise.borisevich.exception.DAOException;

public interface CapitalDAO {

    void addCapital(Capital capital) throws DAOException;

    Capital findCapitalbyId(Long id) throws DAOException;

    void updateCapital(Capital capital) throws DAOException;

    void deleteCapital(Long id) throws DAOException;

}
