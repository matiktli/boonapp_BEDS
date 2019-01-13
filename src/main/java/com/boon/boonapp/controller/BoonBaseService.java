package com.boon.boonapp.controller;

import com.boon.boonapp.domain.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.boon.boonapp.controller.BoonServiceConstants.*;

public interface BoonBaseService {

    // * USER ENDPOINTS *

    @GetMapping(value = USER_WITH_ID_URL)
    ResponseEntity<UserDTO> getUserById(@PathVariable("userId") Integer userId);

    @PostMapping(value = REGISTER_URL)
    ResponseEntity<TokenDTO> register(@Validated @RequestBody UserDTO userDTO);

    @PostMapping(value = LOGIN_URL)
    ResponseEntity<TokenDTO> login(@Validated @RequestBody UserDTO userDTO);

    // * NEEDY ENDPOINTS *
    @GetMapping(value = NEEDY_WITH_ID_URL)
    ResponseEntity<NeedyDTO> getNeedyById(@PathVariable("needyId") Integer needyId);

    @GetMapping(value = NEEDY_URL)
    ResponseEntity<Page<NeedyDTO>> getAllNeedyInArea(@RequestParam(name = "lat", required = false) Double lat,
                                                     @RequestParam(name = "lng", required = false) Double lng,
                                                     @RequestParam(name = "distance", required = false) Double distance,
                                                     @PageableDefault(size = 50) Pageable pageable);

    @PostMapping(value = NEEDY_URL)
    ResponseEntity<NeedyDTO> createNeedy(@Validated @RequestBody NeedyDTO needyDTO);

    // * HELP ENDPOINTS *
    @GetMapping(value = HELP_WITH_ID_URL)
    ResponseEntity<HelpDTO> getHelpById(@PathVariable("helpId") Integer helpId);

    @GetMapping(value = HELP_URL)
    ResponseEntity<Page<HelpDTO>> getAllHelpsForUserAndNeedy(@RequestParam(name = "userId", required = false) Integer userId,
                                                             @RequestParam(name = "needyId", required = false) Integer needyId);

    @PostMapping(value = HELP_URL)
    ResponseEntity<HelpDTO> createHelp(@Validated @RequestBody HelpDTO helpDTO);

}
