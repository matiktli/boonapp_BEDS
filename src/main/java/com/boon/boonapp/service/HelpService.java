package com.boon.boonapp.service;

import com.boon.boonapp.dao.HelpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelpService {

    @Autowired
    private HelpRepository helpRepository;
}
