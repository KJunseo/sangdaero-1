package com.sangdaero.walab.user.application.dto;

import com.sangdaero.walab.common.entity.Device;

import java.util.List;

public interface SimpleUser {

    Long getId();

    String getName();

    String getNickname();

    Byte getUserType();

    String getPhone();

    Integer getVolunteerTime();

    List<Device> getDevices();

}
