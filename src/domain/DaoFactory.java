package main.java.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
public class DaoFactory {
    @Bean
    public UserDao userDao() throws ClassNotFoundException {
        UserDao userDao = new UserDao();
        userDao.setDataSource(dataSource());
        return userDao;
    }


    @Bean
    public DataSource dataSource() throws ClassNotFoundException {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        Class driverClass = Class.forName("(com.mysql.jdbc.Drive.class");

        dataSource.setDriverClass(driverClass);
        dataSource.setUrl("jdbc:mysql://localhost/toby?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        return (DataSource) dataSource;
    }
    @Bean
    public ConnectionMaker connectionMaker() {
        return new SimpleConnectionMaker();
    }
}

