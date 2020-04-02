package com.sangdaero.walab.user.application.service;

import com.sangdaero.walab.common.entity.InterestCategory;
import com.sangdaero.walab.interest.domain.repository.InterestRepository;
import com.sangdaero.walab.common.entity.UserInterest;
import com.sangdaero.walab.mapper.repository.UserInterestRepository;
import com.sangdaero.walab.user.application.dto.SimpleUser;
import com.sangdaero.walab.user.application.dto.UserDTO;
import com.sangdaero.walab.user.application.dto.UserDetailDTO;
import com.sangdaero.walab.common.entity.User;
import com.sangdaero.walab.user.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private InterestRepository mInterestRepository;
    private UserRepository mUserRepository;
    private UserInterestRepository mUserInterestRepository;

    public UserService(UserRepository mUserRepository, InterestRepository mInterestRepository, UserInterestRepository mUserInterestRepository) {
        this.mUserRepository = mUserRepository;
        this.mInterestRepository = mInterestRepository;
        this.mUserInterestRepository = mUserInterestRepository;
    }

    public void addUser(UserDTO userDTO) {

        for (String e :userDTO.getUserInterestList()) {
            InterestCategory interestList = mInterestRepository.findByNameEquals(e);
            userDTO.getInterests().add(interestList);
        }

        User user = mUserRepository.save(userDTO.toEntity());

        for(InterestCategory interest : userDTO.getInterests()) {
            UserInterest userInterest = new UserInterest();

            userInterest.setInterest(interest);
            userInterest.setUser(user);

            mUserInterestRepository.save(userInterest);
        }
    }


    public List<SimpleUser> getSimpleUserList() {
        List<SimpleUser> simpleUserList = mUserRepository.findAllByOrderByName();
        return simpleUserList;
    }


    public UserDetailDTO getUser(Long id) {
        Optional<User> userWrapper = mUserRepository.findById(id);

        User user = userWrapper.get();

        List<UserInterest> byUser_id = mUserInterestRepository.findByUser_Id(user.getId());

        Set<String> interestName = new HashSet<>();

        for(UserInterest e : byUser_id) {
            Optional<InterestCategory> byId = mInterestRepository.findById(e.getInterest().getId());
            interestName.add(byId.get().getName());
        }

        UserDetailDTO userDetailDTO = UserDetailDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .nickname(user.getNickname())
                .socialId(user.getSocialId())
                .phone(user.getPhone())
                .userType(user.getUserType())
                .volunteerTime(user.getVolunteerTime())
                .interestName(interestName)
                .build();

        return userDetailDTO;
    }
}
