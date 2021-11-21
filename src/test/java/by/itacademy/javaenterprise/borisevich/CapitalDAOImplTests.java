package by.itacademy.javaenterprise.borisevich;

import by.itacademy.javaenterprise.borisevich.dao.CapitalDAO;
import by.itacademy.javaenterprise.borisevich.dao.impl.CapitalDAOImpl;
import by.itacademy.javaenterprise.borisevich.entity.Capital;
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
public class CapitalDAOImplTests {
    private CapitalDAO capitalDAO;
    @Mock
    private EntityTransaction entityTransactionMock;
    @Mock
    private EntityManager entityManagerMock;

    Capital capital;

    @BeforeEach
    void beforeEach() {
        capitalDAO = new CapitalDAOImpl(entityManagerMock);
        lenient().when(entityManagerMock.getTransaction()).thenReturn(entityTransactionMock);
        capital = Capital.builder().countryId(2L).name("Amsterdam").build();
    }

    @Test
    void entityAddTest() throws DAOException {
        capitalDAO.addCapital(capital);
        verify(entityTransactionMock, times(1)).begin();
        verify(entityManagerMock, times(1)).persist(capital);
        verify(entityTransactionMock, times(1)).commit();

    }

    @Test
    void addTestWithEntityNull() {
        assertThrows(DAOException.class, () -> {
            capitalDAO.addCapital(null);
        });
    }

    @Test
    public void entityFindTest() throws DAOException {
        when(entityManagerMock.find(Capital.class, capital.getCountryId())).thenReturn(capital);
        assertEquals(capital, capitalDAO.findCapitalbyId(capital.getCountryId()));
        verify(entityTransactionMock, times(1)).begin();
        verify(entityTransactionMock, times(1)).commit();
    }

    @Test
    public void entityFindTestWithNull() throws DAOException {
        capital.setCountryId(0L);
        when(entityManagerMock.find(Capital.class, 0)).thenReturn(null);
        assertThrows(DAOException.class, () -> {
            capitalDAO.findCapitalbyId(capital.getCountryId());
        });
    }

    @Test
    public void entityUpdateTest() throws DAOException {
        when(entityManagerMock.merge(capital)).thenReturn(capital);
        capitalDAO.updateCapital(capital);
        verify(entityTransactionMock, times(1)).begin();
        verify(entityManagerMock, times(1)).merge(capital);
        verify(entityTransactionMock, times(1)).commit();

    }

    @Test
    public void entityDeleteTest() throws DAOException {
        when(entityManagerMock.find(Capital.class, capital.getCountryId())).thenReturn(capital);
        doNothing().when(entityManagerMock).remove(capital);
        capitalDAO.deleteCapital(capital.getCountryId());
        verify(entityTransactionMock, times(1)).begin();
        verify(entityManagerMock, times(1)).remove(capital);
        verify(entityTransactionMock, times(1)).commit();

    }

}
