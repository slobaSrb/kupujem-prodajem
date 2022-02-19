package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.AdStatus;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.Currency;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.Roles;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.entity.*;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.repositories.*;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;

@Configuration
@Slf4j
public class StartupConfiguration {

    @Bean(name = "passwordEncoder")
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CommandLineRunner commandLineRunner(final ApplicationContext context, final UserRepository userRepository, final CommentRepository commentRepository, final AdRepository adRepository, final RatingRepository ratingRepository, final PurchaseRepository purchaseRepository) {
        return args -> {
            //	showBeans(context);
            initializeData(userRepository, commentRepository, adRepository, ratingRepository, purchaseRepository);
        };
    }

    private void initializeData(final UserRepository userRepository, final CommentRepository commentRepository, final AdRepository adRepository, final RatingRepository ratingRepository, final PurchaseRepository purchaseRepository) {
        System.out.println("Initializing the data...");

        final PasswordEncoder passwordEncoder = getPasswordEncoder();
        final UserEntity e1 = userRepository.save(UserEntity.builder().userID(1L).userName("Zika1").fullName("Zivorad Zivoradovic").avgRating(0.0).email("zivorad.zivoradovic@gmail.com").password(passwordEncoder.encode("zivorad123")).role(Roles.Admin).build());
        //System.out.println(e1);
        final UserEntity e2 = userRepository.save(UserEntity.builder().userID(2L).userName("Zika2").fullName("Zivorad Zivoradovic2").avgRating(0.0).email("zivorad.zivoradovic@gmail.com").password(passwordEncoder.encode("zivorad1234")).role(Roles.User).build());

        final AdEntity a1 = adRepository.save(AdEntity.builder().adPlacementDate(OffsetDateTime.parse("2022-02-15T20:21:20.810+00:00")).category("cipele").currency(Currency.RSD).description("cipele italijanske, prava koza").price(22000.0).quantityAvailable(20).status(AdStatus.Available).title("Paciotti cipele 44").avgRating(0).seller(userRepository.findById(e1.getUserID()).get()).build());
        final AdEntity a2 = adRepository.save(AdEntity.builder().adPlacementDate(OffsetDateTime.parse("2022-02-15T20:21:20.810+00:00")).category("cipele2").currency(Currency.RSD).description("cipele italijanske, prava koza2").price(22000.0).quantityAvailable(20).status(AdStatus.Available).title("Paciotti cipele 44 2").avgRating(0).seller(userRepository.findById(e2.getUserID()).get()).build());

        final CommentEntity c1 = commentRepository.save(CommentEntity.builder().comment("Koje su boje dostupne").ad(a1).commentDate(OffsetDateTime.parse("2022-02-15T20:21:20.810+00:00")).userCommenter(e1).mainComment(null).build());

        final RatingEntity r1 = ratingRepository.save(RatingEntity.builder().ad(a1).dateOfRating(OffsetDateTime.parse("2022-02-15T20:21:20.810+00:00")).rating(5).ratingComment("Odlicna saradnja").user(e1).build());

        final PurchaseEntity p1 = purchaseRepository.save(PurchaseEntity.builder().purchaseID(1L).ad(a1).buyer(e1).datePurchased(OffsetDateTime.parse("2022-02-15T20:21:20.810+00:00")).amountPurchesed(2).currency(Currency.RSD).totalValueOfPurchase(22000.0).build());

        //System.out.println(r1);
        //System.out.println(UserEntity.builder().userName("Zika1").fullName("Zivorad Zivoradovic").avgRating(0.0).email("zivorad.zivoradovic@gmail.com").password("zivorad123").role(Roles.Admin).build());
//		final EmployeeEntity e2 = employeeRepository.save(EmployeeEntity.builder().name("Tasa").role("user").build());
//		System.out.println(e2);

        System.out.println("Data initialized");
    }

}
