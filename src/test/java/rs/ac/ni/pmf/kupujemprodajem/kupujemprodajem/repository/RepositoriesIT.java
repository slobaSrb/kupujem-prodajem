package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.AdStatus;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.Currency;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.Roles;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.entity.AdEntity;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.entity.UserEntity;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.repositories.AdRepository;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.repositories.UserRepository;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.OffsetDateTime;

@DataJpaTest
public class RepositoriesIT {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AdRepository adRepository;


    @Test
    void testDatabase()
    {
        //final UserEntity savedUser = userRepository.save(UserEntity.builder().userName("Zika1").fullName("Zivorad Zivoradovic").avgRating(0.0).email("zivorad.zivoradovic@gmail.com").password("zivorad123").role(Roles.Admin).build());

        //final AdEntity savedAd = adRepository.save(AdEntity.builder().adPlacementDate(OffsetDateTime.parse("2022-02-15T20:21:20.810+00:00")).category("cipele").currency(Currency.RSD).description("cipele italijanske, prava koza").price(22000.0).quantityAvailable(20).status(AdStatus.Available).title("Paciotti cipele 44").avgRating(0).seller(savedUser).build());

        //assertThat(savedUser.equals(savedAd.getSeller()));
    }

    @Test
    public void shouldInsertUserWithTwoAds(){

        //UserEntity user1 = UserEntity.builder().userName("Zika1").fullName("Zivorad Zivoradovic").avgRating(0.0).email("zivorad.zivoradovic@gmail.com").password("zivorad123").role(Roles.Admin).build();
//        AdEntity ad1 = AdEntity.builder().adPlacementDate(OffsetDateTime.parse("2022-02-15T20:21:20.810+00:00")).category("cipele 1").currency(Currency.RSD).description("cipele italijanske, prava koza").price(22000.0).quantityAvailable(20).status(AdStatus.Available).title("Paciotti cipele 44").avgRating(0).seller(user1).build();
//        AdEntity ad2 = AdEntity.builder().adPlacementDate(OffsetDateTime.parse("2022-02-15T20:21:20.810+00:00")).category("cipele 2").currency(Currency.RSD).description("cipele italijanske, prava koza").price(22000.0).quantityAvailable(20).status(AdStatus.Available).title("Paciotti cipele 44").avgRating(0).seller(user1).build();
//
//        user1.getAds().add(ad1);
//        user1.getAds().add(ad2);
//
//
//        UserEntity savedUser = userRepository.save(user1);
//
//        assertThat(adRepository.count()).isEqualTo(2);

    }

    @Test
    void shouldNotAllowDuplicateUserNames(){
        //UserEntity u1 = userRepository.save(UserEntity.builder().userName("Zika1").build());
       // assertThat(u1).isNotNull();

        //assertThatThrownBy(() -> userRepository.save(UserEntity.builder().userName("Zika1").build()));
    }
}
