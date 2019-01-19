package com.boon.boonapp.service;

import com.boon.boonapp.dao.HelpRepository;
import com.boon.boonapp.dao.UserRepository;
import com.boon.boonapp.exception.HelpNotFoundException;
import com.boon.boonapp.model.Help;
import com.boon.boonapp.model.User;
import io.micrometer.core.lang.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HelpService {

    private final HelpRepository helpRepository;
    private final UserService userService;

    @Autowired
    public HelpService(HelpRepository helpRepository, UserService userService) {
        this.helpRepository = helpRepository;
        this.userService = userService;
    }

    public Help createNewHelp(Help help, User initHelper) {
        help.getHelpers().add(initHelper);
        return save(help);
    }

    public Help save(Help help) {
        help.setHelpers(help.getHelpers().stream()
                .filter(user -> user.getId() != null || user.getEmail() != null)
                .map(user -> {
                    if (user.getId() != null) {
                        return userService.getUserById(user.getId());
                    } else {
                        return userService.findUserByEmail(user.getEmail());
                    }
                }).collect(Collectors.toSet()));
        return helpRepository.save(help);
    }

    public Help getHelpById(Long id) {
        return helpRepository.findById(id)
                .orElseThrow(() -> new HelpNotFoundException(id));
    }

    public List<Help> getHelpsByUserId(@Nullable Long userId) {
        List<Help> allHelps = helpRepository.findAll();
        if (userId == null) {
            return allHelps;
        }
        return allHelps
                .stream()
                .filter(help -> help.getHelpers()
                        .stream()
                        .anyMatch(helper -> helper.getId().equals(userId)))
                .collect(Collectors.toList());
    }

    public List<Help> getHelpsByNeedyId(@Nullable Long needyId) {
        List<Help> allHelps = helpRepository.findAll();
        if (needyId == null) {
            return allHelps;
        }
        return allHelps
                .stream()
                .filter(help -> help.getNeedy().getId().equals(needyId))
                .collect(Collectors.toList());
    }

    public List<Help> getHelpsByNeedyAndUserId(@Nullable Long needyId, @Nullable Long userId) {
        List<Help> helpsByUserId = getHelpsByUserId(userId);
        if (needyId == null) {
            return helpsByUserId;
        }
        return helpsByUserId
                .stream()
                .filter(help -> help.getNeedy().getId().equals(needyId))
                .collect(Collectors.toList());
    }
}
