package com.boon.boonapp.controller;

import com.boon.boonapp.domain.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import static com.boon.boonapp.controller.BoonServiceConstants.DEFAULT_PAGE_SIZE;

@Controller
public class BoonBaseController implements BoonBaseService {

    @Override
    public ResponseEntity<UserDTO> getUserById(@PathVariable("userId") Integer userId) {
        return null;
    }

    @Override
    public ResponseEntity<TokenDTO> register(@Validated(value = BaseDTO.CreateValidationGroup.class) @RequestBody UserDTO userDTO) {
        return null;
    }

    @Override
    public ResponseEntity<TokenDTO> login(@Validated(value = UserDTO.LoginValidationGroup.class) @RequestBody UserDTO userDTO) {
        return null;
    }

    @Override
    public ResponseEntity<NeedyDTO> getNeedyById(@PathVariable("needyId") Integer needyId) {
        return null;
    }

    @Override
    public ResponseEntity<Page<NeedyDTO>> getAllNeedyInArea(@RequestParam(name = "lat", required = false) Double lat,
                                                            @RequestParam(name = "lng", required = false) Double lng,
                                                            @RequestParam(name = "distance", required = false) Double distance,
                                                            @PageableDefault(size = 50) Pageable pageable) {
        return null;
    }

    @Override
    public ResponseEntity<NeedyDTO> createNeedy(@Validated(value = BaseDTO.CreateValidationGroup.class) @RequestBody NeedyDTO needyDTO) {
        return null;
    }

    @Override
    public ResponseEntity<HelpDTO> getHelpById(@PathVariable("helpId") Integer helpId) {
        return null;
    }

    @Override
    public ResponseEntity<Page<HelpDTO>> getAllHelpsForUserAndNeedy(@RequestParam(name = "userId", required = false) Integer userId,
                                                                    @RequestParam(name = "needyId", required = false) Integer needyId) {
        return null;
    }

    @Override
    public ResponseEntity<HelpDTO> createHelp(@Validated(value = BaseDTO.CreateValidationGroup.class) @RequestBody HelpDTO helpDTO) {
        return null;
    }
}
