package com.boon.boonapp.transformer;

import com.boon.boonapp.domain.UserDTO;
import com.boon.boonapp.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserTransformer {

    @Autowired
    private ModelMapper modelMapper;

    public UserDTO toDto(User source) {
        UserDTO result = modelMapper.map(source, UserDTO.class);
        return result;
    }

    public User toEntity(UserDTO source) {
        User result = modelMapper.map(source, User.class);
        return result;
    }
}
