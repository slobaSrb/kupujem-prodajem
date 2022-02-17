package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.mapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Component;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.dto.UserDTO;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.entity.AdEntity;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.entity.RatingEntity;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.entity.UserEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.repositories.AdRepository;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {


    public static UserDTO toDto(final UserEntity userEntity) {


//        Double avgRating = 0.0;
//        Integer counter = 0;
//
//        for(AdEntity adEntity : adRepository.findAll()){
//            if(adEntity.getUser().getUserName().equals(userEntity.getUserName())){
//                for( RatingEntity rating : adEntity.getRatings()){
//                    avgRating += rating.getRating();
//                    counter += 1;
//                }
//            }
//        }

//        avgRating /= counter;

        return UserDTO.builder()
                .userID(userEntity.getUserID())
                .userName(userEntity.getUserName())
                .fullName(userEntity.getFullName())
                .email(userEntity.getEmail())
                .role(userEntity.getRole())
                .password(userEntity.getPassword())
                .avgRating(userEntity.getAvgRating())
                //vrv se ovako ne radi nego mora da se uzmu svi ratinzi za odredjenog usera
                //.avgRating(userEntity.getAvgRating())
                //ne bi smelo rolu da mu otkrije po nekom mom ubedjenju
                .build();
    }

    public static UserEntity toEntity(final UserDTO userDto /*, RolesOfUser userRole*/) {
//        Double avgRating = 0.0;
//        Integer counter = 0;
//
//        for(AdEntity adEntity : adRepository.findAll()){
//            if(adEntity.getUser().getUserName().equals(userDto.getUserName())){
//                for( RatingEntity rating : adEntity.getRatings()){
//                    avgRating += rating.getRating();
//                    counter += 1;
//                }
//            }
//        }
//
//        avgRating /= counter;

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        return UserEntity.builder()
                .userID(userDto.getUserID())
                .userName(userDto.getUserName())
                .fullName(userDto.getFullName())
                .email(userDto.getEmail())
                .role(userDto.getRole())
                //.password(passwordEncoder.encode(userDto.getPassword()))
                .password(encoder.encode(userDto.getPassword()))
                //isti komentar odozgo
                .avgRating(userDto.getAvgRating())

                // kako promeniti password useru

                // rola se ne menja
                // ostali atributi se isto ne menjaju
                // pitanje je da l se to dobavi iz baze, videcemo u servisima
                .build();
    }

}
