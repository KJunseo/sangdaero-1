package com.sangdaero.walab;

import com.sangdaero.walab.common.entity.User;
import com.sangdaero.walab.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestRestController {

    private final UserRepository userRepository;

    @GetMapping("/test/get")
    public ResponseEntity testGetFromApp() {
        User user = new User();
        user.setName("App유저(GET)");
        user.setNickname("react-native");
        user.setPhone("010-1111-1111");
        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/test/post")
    public ResponseEntity testPostFromApp() {
        User user = new User();
        user.setName("App유저(POST)");
        user.setNickname("react-native");
        user.setPhone("010-2222-2222");
        userRepository.save(user);

        return ResponseEntity.ok().build();
    }
}
