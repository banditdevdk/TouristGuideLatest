package com.banditdev.touristguide.repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = "classpath:sql/h2database.sql", executionPhase = BEFORE_TEST_METHOD)
class TouristRepositoryTest {

    @Test
    void findAll() {
    }
}