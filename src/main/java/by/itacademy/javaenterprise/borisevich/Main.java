package by.itacademy.javaenterprise.borisevich;


import by.itacademy.javaenterprise.borisevich.dao.impl.CapitalDAOImpl;
import by.itacademy.javaenterprise.borisevich.dao.impl.CountryDAOImpl;
import by.itacademy.javaenterprise.borisevich.entity.Capital;
import by.itacademy.javaenterprise.borisevich.entity.Country;
import by.itacademy.javaenterprise.borisevich.exception.DAOException;
import by.itacademy.javaenterprise.borisevich.spring.SpringConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws DAOException {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        CountryDAOImpl  countryDAO = context.getBean( CountryDAOImpl .class);
        CapitalDAOImpl capitalDAO=context.getBean( CapitalDAOImpl .class);

        Country country = Country.builder().name("Italy").population(20000L).build();

        countryDAO.addCountry(country);
        Capital capital = Capital.builder().countryId(country.getCountryId()).name("Rome").build();
        capitalDAO.addCapital(capital);

        Country countrybyId = countryDAO.findCountrybyId(country.getCountryId());

        LOGGER.info("Country by id {}", countrybyId);

    }
}