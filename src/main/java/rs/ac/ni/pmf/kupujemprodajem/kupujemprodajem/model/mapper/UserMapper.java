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

        return UserDTO.builder()
                .userID(userEntity.getUserID())
                .userName(userEntity.getUserName())
                .fullName(userEntity.getFullName())
                .email(userEntity.getEmail())
                .role(userEntity.getRole())
                .password(userEntity.getPassword())
                .avgRating(userEntity.getAvgRating())
                .build();
    }

    public static UserEntity toEntity(final UserDTO userDto, PasswordEncoder passwordEncoder) {

        return UserEntity.builder()
                .userID(userDto.getUserID())
                .userName(userDto.getUserName())
                .fullName(userDto.getFullName())
                .email(userDto.getEmail())
                .role(userDto.getRole())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .avgRating(userDto.getAvgRating())
                .build();
    }

}
