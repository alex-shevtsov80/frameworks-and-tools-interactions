package by.salex.spring.data.service;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import by.salex.spring.data.SpringDataJdbcApp;

@SpringBootTest(classes = { SpringDataJdbcApp.class })
@TestPropertySource("classpath:application-test.properties")
public abstract class BaseServiceTest {

}
