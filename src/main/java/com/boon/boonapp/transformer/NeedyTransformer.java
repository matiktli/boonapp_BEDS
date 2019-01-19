package com.boon.boonapp.transformer;

import com.boon.boonapp.domain.NeedyDTO;
import com.boon.boonapp.model.Needy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NeedyTransformer {

    @Autowired
    private ModelMapper modelMapper;

    public NeedyDTO toDto(Needy source) {
        NeedyDTO result = modelMapper.map(source, NeedyDTO.class);
        return result;
    }

    public Needy toEntity(NeedyDTO source) {
        Needy result = modelMapper.map(source, Needy.class);
        return result;
    }
}
