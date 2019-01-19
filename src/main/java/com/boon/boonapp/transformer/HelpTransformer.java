package com.boon.boonapp.transformer;

import com.boon.boonapp.domain.HelpDTO;
import com.boon.boonapp.domain.LocationDTO;
import com.boon.boonapp.model.Help;
import com.boon.boonapp.model.Location;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelpTransformer {

    @Autowired
    private ModelMapper modelMapper;

    public HelpDTO toDto(Help source) {
        HelpDTO result = modelMapper.map(source, HelpDTO.class);
        return result;
    }

    public Help toEntity(HelpDTO source) {
        Help result = modelMapper.map(source, Help.class);
        return result;
    }
}
