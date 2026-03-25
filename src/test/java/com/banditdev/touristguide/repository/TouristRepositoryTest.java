package com.banditdev.touristguide.repository;

import com.banditdev.touristguide.model.TouristAttraction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = "classpath:sql/h2database.sql", executionPhase = BEFORE_TEST_METHOD)
class TouristRepositoryTest {

    @Autowired
    private TouristRepository repository;

    @Test
    void findAll() {
        List<TouristAttraction> test = repository.findAll();

        // der burde være 6 attractions i H2 SQL scriptet - classpath:sql/h2database.sql
        assertThat(test.size()).isEqualTo(6);
        assertThat(test.getFirst().getName()).isEqualTo("Den Lille Havfrue");
        assertThat(test.getFirst().getId()).isEqualTo(1);
    }

    @Test
    void insertNewAttractionAndTest() {
        repository.addTouristAttraction(new TouristAttraction(7, "testName", "Test Description", "Vejle"));

        TouristAttraction testAttraction = repository.findTouristAttractionById(7);
        assertThat(testAttraction).isNotNull();
        assertThat(testAttraction.getName()).isEqualTo("testName");
    }

    @Test
    void deleteAttractionAndTest() {
        assertThat(repository.findAll().size()).isEqualTo(6);
        repository.deleteTouristAttractionById(1);

        assertThat(repository.findAll().size()).isEqualTo(5);
        assertThat(repository.findAll().getFirst().getId()).isEqualTo(2);
    }
}