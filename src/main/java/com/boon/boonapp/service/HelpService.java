package com.boon.boonapp.service;

import com.boon.boonapp.dao.HelpRepository;
import com.boon.boonapp.exception.HelpNotFoundException;
import com.boon.boonapp.model.Help;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelpService {

    @Autowired
    private HelpRepository helpRepository;

    public Help createNewHelp(Help help) {
        return save(help);
    }

    public Help save(Help help) {
        return helpRepository.save(help);
    }

    public Help getHelpById(Long id) {
        return helpRepository.findById(id)
                .orElseThrow(() -> new HelpNotFoundException(id));
    }

    public List<Help> getHelpsByUser() {
        return null;
    }
}
