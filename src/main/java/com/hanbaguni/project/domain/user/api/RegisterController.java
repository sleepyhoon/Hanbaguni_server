package com.hanbaguni.project.domain.user.api;


import com.hanbaguni.project.domain.user.dto.RegisterDto;
import com.hanbaguni.project.domain.user.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RegisterController {
    private final MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDto registerDto) {
        memberService.createNewMember(registerDto);

        return ResponseEntity.ok("success register");
    }
}
