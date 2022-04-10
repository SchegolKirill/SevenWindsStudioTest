package com.testtask.SevenWindsStudio.converter;

import com.testtask.SevenWindsStudio.DTO.RequestUserDTO;
import com.testtask.SevenWindsStudio.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RequestUserConverter {
    public RequestUserDTO entityToDTO(User user){
        RequestUserDTO dto = new RequestUserDTO();
        dto.setSurname(user.getSurname());
        dto.setName(user.getName());
        dto.setPatronymic(user.getPatronymic());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        return dto;
    }

    public List<RequestUserDTO> entityToDTO(List<User> users) {
        return users.stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    public User DTOToEntity(RequestUserDTO dto) {
        User user = new User();
        user.setSurname(dto.getSurname());
        user.setName(dto.getName());
        user.setPatronymic(dto.getPatronymic());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        return user;
    }

    public List<User> DTOToEntity(List<RequestUserDTO> dto) {
        return dto.stream().map(this::DTOToEntity).collect(Collectors.toList());
    }
}
