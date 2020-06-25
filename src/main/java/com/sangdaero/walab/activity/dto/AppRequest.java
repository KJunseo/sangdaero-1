package com.sangdaero.walab.activity.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppRequest {

    private String email;

    private String name;

    private Long id;

    private Byte type;

    private String title;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String memo;

}
