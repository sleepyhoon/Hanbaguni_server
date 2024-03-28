package com.hanbaguni.project.domain.user.api;


import com.hanbaguni.project.domain.user.dto.RegisterDto;
import com.hanbaguni.project.domain.user.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


/**
 * controller to register new user.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class RegisterController {
    private final MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {

        memberService.createNewMember(registerDto);

        return ResponseEntity.ok("success register test");
    }

    @PostMapping("/test")
    public ResponseEntity<?> justTest() {
        return ResponseEntity.ok("success with 24");
    }
}
