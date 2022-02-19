package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.repositories.AdRepository;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.repositories.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource("/it_test.properties")
public class ApplicationIT {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AdRepository adRepository;

    @Test
    void shouldCountUsers(){
        assertThat(userRepository.count()).isEqualTo(2);
    }
}
