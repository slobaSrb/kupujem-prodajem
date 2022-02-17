package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
