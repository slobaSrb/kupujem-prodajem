package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.dto.UserDTO;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUserName(String userName);

//    List<UserEntity> findByUserNameLikeOrderByFullNameAdsAsc(String userName);
//
//    List<UserEntity> findByUserNameOrFullNameIgnoreCaseOrderByUserIDAdsAsc(String userName, String fullName);
//
//    List<UserEntity> findByUserNameAndFullNameAndSort(String userName, String fullName);
//
//    @Query("select u from UserEntity as u where u.userName = ?1")
//    UserEntity findByUserNameQuery(String namePrefix);
//
//    @Query("select u from UserEntity as u where u.userName like ?1%")
//    List<UserEntity> findSomeUsersQuery2(String namePrefix);
}
