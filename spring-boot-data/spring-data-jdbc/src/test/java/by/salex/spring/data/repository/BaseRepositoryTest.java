package by.salex.spring.data.repository;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import by.salex.spring.data.SpringDataJdbcApp;

@SpringBootTest(classes = { SpringDataJdbcApp.class })
@TestPropertySource("classpath:application-test.properties")
public class BaseRepositoryTest {

}
