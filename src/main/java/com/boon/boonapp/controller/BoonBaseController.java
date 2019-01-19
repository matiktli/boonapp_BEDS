package com.boon.boonapp.controller;

import com.boon.boonapp.domain.*;
import com.boon.boonapp.model.*;
import com.boon.boonapp.security.AuthorizationUtil;
import com.boon.boonapp.security.SecurityService;
import com.boon.boonapp.service.*;
import com.boon.boonapp.transformer.*;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BoonBaseController implements BoonBaseService {

    private final LocationService locationService;
    private final NeedyService needyService;
    private final TokenService tokenService;
    private final UserService userService;
    private final HelpService helpService;
    private final SecurityService securityService;

    private final NeedyTransformer needyTransformer;
    private final LocationTransformer locationTransformer;
    private final UserTransformer userTransformer;
    private final TokenTransformer tokenTransformer;
    private final HelpTransformer helpTransformer;

    @Autowired
    public BoonBaseController(LocationService locationService, NeedyService needyService, TokenService tokenService,
                              UserService userService, HelpService helpService, SecurityService securityService,
                              NeedyTransformer needyTransformer, LocationTransformer locationTransformer,
                              UserTransformer userTransformer, TokenTransformer tokenTransformer,
                              HelpTransformer helpTransformer) {
        this.locationService = locationService;
        this.needyService = needyService;
        this.tokenService = tokenService;
        this.userService = userService;
        this.helpService = helpService;
        this.securityService = securityService;
        this.needyTransformer = needyTransformer;
        this.locationTransformer = locationTransformer;
        this.userTransformer = userTransformer;
        this.tokenTransformer = tokenTransformer;
        this.helpTransformer = helpTransformer;
    }

    @Override
    public ResponseEntity<UserDTO> getUserById(@PathVariable("userId") Long userId) {
        User user = userService.getUserById(userId);
        User currentUser = AuthorizationUtil.getCurrentUser();
        if (!user.getEmail().equals(currentUser.getEmail())) {
            user.setPassword(user.getPassword().replaceAll(".", "*"));
        }
        return ResponseEntity.ok(userTransformer.toDto(user));
    }

    @Override
    public ResponseEntity<TokenDTO> register(@Validated(value = BaseDTO.CreateValidationGroup.class) @RequestBody UserDTO userDTO) {
        User user = userTransformer.toEntity(userDTO);
        Token token = securityService.register(user);
        return ResponseEntity.ok(tokenTransformer.toDto(token));
    }

    @Override
    public ResponseEntity<TokenDTO> login(@Validated(value = UserDTO.LoginValidationGroup.class) @RequestBody UserDTO userDTO) {
        User user = userTransformer.toEntity(userDTO);
        Token token = securityService.login(user);
        return ResponseEntity.ok(tokenTransformer.toDto(token));
    }

    @Override
    public ResponseEntity<NeedyDTO> getNeedyById(@PathVariable("needyId") Long needyId) {
        Needy needy = needyService.findNeedyById(needyId);
        return ResponseEntity.ok(needyTransformer.toDto(needy));
    }

    @Override
    public ResponseEntity<List<NeedyDTO>> getAllNeedyInArea(@RequestParam(name = "lat", required = false) Double lat,
                                                            @RequestParam(name = "lng", required = false) Double lng,
                                                            @RequestParam(name = "distance", required = false) Double distance,
                                                            @RequestParam(name = "type", required = false) NeedyType needyType,
                                                            @RequestParam(name = "count", required = false, defaultValue = "20") Integer count) {
        List<Needy> foundNeedies = needyService.getAllNeediesInAreaAndType(Location.builder().build(), distance, needyType, count);
        List<NeedyDTO> result = foundNeedies.stream().map(needyTransformer::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<NeedyDTO> createNeedy(@Validated(value = BaseDTO.CreateValidationGroup.class) @RequestBody NeedyDTO needyDTO) {
        Needy needy = needyTransformer.toEntity(needyDTO);
        needy = needyService.createNewNeedy(needy, AuthorizationUtil.getCurrentUser());
        return ResponseEntity.ok(needyTransformer.toDto(needy));
    }

    @Override
    public ResponseEntity<HelpDTO> getHelpById(@PathVariable("helpId") Long helpId) {
        Help help = helpService.getHelpById(helpId);
        HelpDTO helpDTO = helpTransformer.toDto(help);
        return ResponseEntity.ok(helpDTO);
    }

    @Override
    public ResponseEntity<List<HelpDTO>> getAllHelpsForUserAndNeedy(@RequestParam(name = "userId", required = false) Long userId,
                                                                    @RequestParam(name = "needyId", required = false) Long needyId) {
        List<Help> helps = helpService.getHelpsByNeedyAndUserId(needyId, userId);
        List<HelpDTO> helpDTOs = helps.stream().map(helpTransformer::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(helpDTOs);
    }

    @Override
    public ResponseEntity<HelpDTO> createHelp(@Validated(value = BaseDTO.CreateValidationGroup.class) @RequestBody HelpDTO helpDTO) {
        Help help = helpTransformer.toEntity(helpDTO);
        help = helpService.createNewHelp(help, AuthorizationUtil.getCurrentUser());
        return ResponseEntity.ok(helpTransformer.toDto(help));
    }
}
