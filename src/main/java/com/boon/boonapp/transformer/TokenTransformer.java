package com.boon.boonapp.transformer;

import com.boon.boonapp.domain.TokenDTO;
import com.boon.boonapp.model.Token;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenTransformer {

    @Autowired
    private ModelMapper modelMapper;

    public TokenDTO toDto(Token source) {
        TokenDTO result = modelMapper.map(source, TokenDTO.class);
        return result;
    }

    public Token toEntity(TokenDTO source) {
        Token result = modelMapper.map(source, Token.class);
        return result;
    }
}
