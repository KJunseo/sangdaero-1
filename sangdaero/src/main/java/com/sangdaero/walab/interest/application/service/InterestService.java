package com.sangdaero.walab.interest.application.service;


import com.sangdaero.walab.interest.application.DTO.InterestDTO;
import com.sangdaero.walab.interest.application.DTO.InterestName;
import com.sangdaero.walab.common.entity.InterestCategory;
import com.sangdaero.walab.interest.domain.repository.InterestRepository;
import com.sangdaero.walab.mapper.repository.UserInterestRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InterestService {

    private InterestRepository mInterestRepository;
    private UserInterestRepository mUserInterestRepository;

    public InterestService(InterestRepository interestRepository, UserInterestRepository mUserInterestRepository) {
        this.mInterestRepository = interestRepository;
        this.mUserInterestRepository = mUserInterestRepository;
    }

    public Long addInterest(InterestDTO interestDto) {
        return mInterestRepository.save(interestDto.toEntity()).getId();
    }

    public List<InterestDTO> getInterestList() {
//        List<InterestName> interestNames = mInterestRepository.findAllByOrderByName();
        List<InterestCategory> interestCategories = mInterestRepository.findAll();
//        List<InterestCategory> interestCategories = mInterestRepository.findByTypeEquals(type);
        List<InterestDTO> interestDTOList = new ArrayList<>();

        for(InterestCategory interestCategory: interestCategories) {
            InterestDTO interestDTO = InterestDTO.builder()
                    .id(interestCategory.getId())
                    .name(interestCategory.getName())
                    .type(interestCategory.getType())
                    .on_off(interestCategory.getOn_off())
                    .createdDate(interestCategory.getRegDate())
                    .modifiedDate(interestCategory.getModDate())
                    .count(mUserInterestRepository.countByInterest_Id(interestCategory.getId()))
                    .build();

            interestDTOList.add(interestDTO);
        }
        return interestDTOList;
    }

    public InterestDTO getInterest(Long id) {
        Optional<InterestCategory> interestWrapper = mInterestRepository.findById(id);
        InterestCategory interestCategory = interestWrapper.get();

        InterestDTO interestDTO = InterestDTO.builder()
                .id(interestCategory.getId())
                .name(interestCategory.getName())
                .type(interestCategory.getType())
                .on_off(interestCategory.getOn_off())
                .build();

        return interestDTO;
    }

    public void deleteInterest(Long id) {
        mInterestRepository.deleteById(id);
    }
}
