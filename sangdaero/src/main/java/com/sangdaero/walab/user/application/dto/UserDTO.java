package com.sangdaero.walab.user.application.DTO;

import com.sangdaero.walab.interest.domain.entity.InterestCategory;
import com.sangdaero.walab.user.domain.entity.User;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String nickname;
    private String phone;
    private String[] userInterestList;
    private Set<InterestCategory> interests = new HashSet<>();

    public User toEntity() {
        User user = User.builder()
                .id(id)
                .name(name)
                .nickname(nickname)
                .phone(phone)
                .build();
        return user;
    }

    @Builder
    public UserDTO(Long id, String name, String nickname, String phone) {
        this.id=id;
        this.name=name;
        this.nickname=nickname;
        this.phone=phone;
    }

}
