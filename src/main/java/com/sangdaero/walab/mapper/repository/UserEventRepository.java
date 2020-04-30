package com.sangdaero.walab.mapper.repository;

import com.sangdaero.walab.common.entity.UserEventMapper;
import com.sangdaero.walab.mapper.id.UserEventId;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface UserEventRepository extends JpaRepository<UserEventMapper, UserEventId> {

    List<UserEventMapper> findAllByUserTypeAndEvent_id(byte i, Long id);
}
