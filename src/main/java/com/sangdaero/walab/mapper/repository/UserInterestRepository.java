package com.sangdaero.walab.mapper.repository;

import com.sangdaero.walab.common.entity.UserInterest;
import com.sangdaero.walab.mapper.id.UserInterestId;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface UserInterestRepository extends JpaRepository<UserInterest, UserInterestId> {

    Long countByInterest_Id(Long id);

    List<UserInterest> findByUser_Id(Long id);

    @Transactional
    void deleteByUser_Id(Long id);


//    UserInterest findByUser_IdAndInterestId(Long user_id, Long interest_id);

    void deleteByUser_IdAndInterestId(Long user_id, Long interest_id);
//    void deletByUser_Id(Long id);

}
