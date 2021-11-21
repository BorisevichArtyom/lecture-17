package by.itacademy.javaenterprise.borisevich;

import by.itacademy.javaenterprise.borisevich.dao.CountryDAO;
import by.itacademy.javaenterprise.borisevich.dao.impl.CountryDAOImpl;
import by.itacademy.javaenterprise.borisevich.entity.Country;
import by.itacademy.javaenterprise.borisevich.exception.DAOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CountryDAOImplTests {
    private CountryDAO countryDAO;
    @Mock
    private EntityTransaction entityTransactionMock;
    @Mock
    private EntityManager entityManagerMock;

    Country country;

    @BeforeEach
    void beforeEach() {
        countryDAO = new CountryDAOImpl(entityManagerMock);
        when(entityManagerMock.getTransaction()).thenReturn(entityTransactionMock);
        country = Country.builder().name("Netherlands").population(20000L).build();
    }

    @Test
    void entityAddTest() throws DAOException {
        countryDAO.addCountry(country);
        verify(entityTransactionMock, times(1)).begin();
        verify(entityManagerMock, times(1)).persist(country);
        verify(entityTransactionMock, times(1)).commit();

    }

    @Test
    void addTestWithEntityNull() {
        assertThrows(NullPointerException.class, () -> {
            countryDAO.addCountry(null);
        });
    }

    @Test
    public void entityFindTest() throws DAOException {
        country.setCountryId(1L);
        when(entityManagerMock.find(Country.class, country.getCountryId())).thenReturn(country);
        assertEquals(country,  countryDAO.findCountrybyId(country.getCountryId()));
        verify(entityTransactionMock, times(1)).begin();
        verify(entityTransactionMock, times(1)).commit();
    }

    @Test
    public void entityFindTestWithNull() throws DAOException {
        country.setCountryId(0L);
        when(entityManagerMock.find(Country.class, 0)).thenReturn(null);
        assertThrows(DAOException.class, () -> {
            countryDAO.findCountrybyId(country.getCountryId());
        });
    }

    @Test
    public void entityUpdateTest() throws DAOException {
        country.setCountryId(1L);
        when(entityManagerMock.merge(country)).thenReturn(country);
        countryDAO.updateCountry(country);
        verify(entityTransactionMock, times(1)).begin();
        verify(entityManagerMock, times(1)).merge(country);
        verify(entityTransactionMock, times(1)).commit();

    }

    @Test
    public void entityDeleteTest() throws DAOException {
        country.setCountryId(1L);
        when(entityManagerMock.find(Country.class, country.getCountryId())).thenReturn(country);
        doNothing().when(entityManagerMock).remove(country);
        countryDAO.deleteCountry(country.getCountryId());
        verify(entityTransactionMock, times(1)).begin();
        verify(entityManagerMock, times(1)).remove(country);
        verify(entityTransactionMock, times(1)).commit();

    }

}
