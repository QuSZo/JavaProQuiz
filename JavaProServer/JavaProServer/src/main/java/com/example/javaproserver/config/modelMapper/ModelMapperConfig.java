package com.example.javaproserver.config.modelMapper;

import com.example.javaproserver.models.DTOs.responses.GetQuizUserScoreResponse;
import com.example.javaproserver.models.entities.UserQuizScore;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        TypeMap<UserQuizScore, GetQuizUserScoreResponse> propertyMapper = modelMapper.createTypeMap(UserQuizScore.class, GetQuizUserScoreResponse.class);
        propertyMapper.addMappings(mapper -> mapper.map(src -> src.getUser().getFirstName(), GetQuizUserScoreResponse::setFirstName));
        propertyMapper.addMappings(mapper -> mapper.map(src -> src.getUser().getLastName(), GetQuizUserScoreResponse::setLastName));
        propertyMapper.addMappings(mapper -> mapper.map(src -> src.getUser().getStudentIdNumber(), GetQuizUserScoreResponse::setStudentIdNumber));

        return modelMapper;
    }
}
