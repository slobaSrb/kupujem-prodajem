package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.entity.UserEntity;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.repositories.UserRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository _userRepository;


    @BeforeEach
    void initializeData() {
        _userRepository.save(UserEntity.builder()
                .userName("Pera")
                .fullName("Pera Peric")
                .build());

        _userRepository.save(UserEntity.builder()
                .userName("Laza")
                .fullName("Laza Lazic")
                .build());
        _userRepository.save(UserEntity.builder()
                .userName("Pera")
                .fullName("Pera Peric")
                .build());

        _userRepository.save(UserEntity.builder()
                .userName("Laza")
                .fullName("Laza Lazic")
                .build());
    }

//    @Test
//    void shouldTestFindBy() {
//        assertThat(_userRepository.findByUserName("Pera").stream().count()).hasSize(2);
//        assertThat(_userRepository.findByUserName("Laza").stream().count()).isEqualTo(2)
//    }

    @Test
    void shouldUseSorting() {
        List<UserEntity> users = _userRepository.findAll(Sort.by("userName", "fullName"));
        assertThat(users.get(0).getUserName()).isEqualTo("Laza");


    }

    @Test
    void shouldUsePaging() {

        int page = 0;
        int pageSize = 2;

        Page<UserEntity> currentPage;

        do {
            currentPage = _userRepository.findAll(PageRequest.of(page, pageSize));

            currentPage.stream().forEach(userEntity -> System.out.println(userEntity.toString()));

            System.out.println("--------------------------------");

        } while (currentPage.hasNext());

        System.out.println("Pages count " + currentPage.getTotalPages());
        System.out.println("Elements count " + currentPage.getTotalElements());

    }

//    @Test
//    public void shouldUseQuery(){
//        final UserEntity user = _userRepository.findByUserNameQuery("Laza");
//        _userRepository.findSomeUsersQuery2("Pera")
//                .stream().forEach(userEntity -> System.out.println(userEntity.toString()));
//    }

}
