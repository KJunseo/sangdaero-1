package com.sangdaero.walab.common.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.sangdaero.walab.mapper.id.UserEventId;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@ToString
@Table(name = "userevent_mapper")
@IdClass(UserEventId.class)
public class UserEventMapper extends TimeEntity {

	@Id
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	@Id
	@ManyToOne
	@JoinColumn(name="event_id")
	private EventEntity event;

	@Column(name="user_type", nullable=false)
	@ColumnDefault("0")
	private Byte userType;

}
