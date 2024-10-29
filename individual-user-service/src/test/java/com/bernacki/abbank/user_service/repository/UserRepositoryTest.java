package com.bernacki.abbank.user_service.repository;

import com.bernacki.abbank.user_service.entity.IndividualUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(locations = "/test.properties")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JdbcTemplate jdbc;

    @BeforeEach
    public void beforeEach(){
        jdbc.execute("ALTER TABLE individual_user ALTER COLUMN id RESTART WITH 1000");

        jdbc.execute("insert into individual_user (name, surname, email, phone_number, birth_date) VALUES " +
                "('John', 'Smith', 'john.smith@email.com', '555123456', '1985-06-15')");

        jdbc.execute("insert into individual_user (name, surname, email, phone_number, birth_date) VALUES " +
                "('Emily', 'Johnson', 'emily.johnson@email.com', '555987654', '1992-09-30')");

        jdbc.execute("insert into individual_user (name, surname, email, phone_number, birth_date) VALUES " +
                "('Michael', 'Brown', 'michael.brown@email.com', '555456123', '1977-12-10')");
    }

    @Test
    public void getUserById_success(){
        IndividualUser user = userRepository.findById(1000L).orElse(null);
        assertNotNull(user);
        assertEquals("John", user.getName(), "Name should be John");
    }

    @Test
    public void getUserById_failure(){
        IndividualUser user = userRepository.findById(9999L).orElse(null);
        assertNull(user);
    }
}
