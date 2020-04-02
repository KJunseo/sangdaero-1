package com.sangdaero.walab.common.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@DynamicInsert
@Table(name = "event")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EventEntity extends TimeEntity {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 255, nullable = true)
	private String title;

	@ColumnDefault("0")
	private Byte status;

	@Column(name="user_taker", nullable = true)
	@ColumnDefault("0")
	private Integer userTaker;

	@Column(name="user_volunteer", nullable = true)
	@ColumnDefault("0")
	private Integer userVolunteer;

	@ColumnDefault("0")
	private Integer manager;

	@Column(length = 255)
	private String place;

	private LocalDateTime startTime;
	private LocalDateTime endTime;

	@Column(columnDefinition = "TEXT")
	private String content;

	@Column(name="delivery_flag", nullable = true)
	@ColumnDefault("0")
	private Byte deliveryFlag;

	@Column(name="phone_agree", nullable = true)
	@ColumnDefault("0")
	private Byte phoneAgree;

	@Column(length = 255)
	private String donator;

	@Column(name="select_support", nullable = true)
	@ColumnDefault("0")
	private Byte selectSupport;

	@Column(name="donation_price", nullable = true)
	private Integer donationPrice;

	@Column(name="bill_type", nullable = true)
	@ColumnDefault("0")
	private Byte billType;

	@Column(name="payment_check", nullable = true)
	@ColumnDefault("0")
	private Byte paymentCheck;

	@Column(length = 255, name="donator_name", nullable = true)
	private String donatorName;

	@Column(length = 255, name="donator_phone", nullable = true)
	private String donatorPhone;

	@Column(length = 255, name="business_picture", nullable = true)
	private String businessPicture;

	@Column(length = 500)
	private String evaluate;

	private LocalDateTime deadline;

	@Builder
	public EventEntity(Long id, String title, Byte status, Integer userTaker, Integer userVolunteer, Integer manager,
			String place, LocalDateTime startTime, LocalDateTime endTime, String content, Byte deliveryFlag,
			Byte phoneAgree, String donator, Byte selectSupport, Integer donationPrice, Byte billType,
			Byte paymentCheck, String donatorName, String donatorPhone, String businessPicture, String evaluate,
			LocalDateTime deadline) {
		super();
		this.id = id;
		this.title = title;
		this.status = status;
		this.userTaker = userTaker;
		this.userVolunteer = userVolunteer;
		this.manager = manager;
		this.place = place;
		this.startTime = startTime;
		this.endTime = endTime;
		this.content = content;
		this.deliveryFlag = deliveryFlag;
		this.phoneAgree = phoneAgree;
		this.donator = donator;
		this.selectSupport = selectSupport;
		this.donationPrice = donationPrice;
		this.billType = billType;
		this.paymentCheck = paymentCheck;
		this.donatorName = donatorName;
		this.donatorPhone = donatorPhone;
		this.businessPicture = businessPicture;
		this.evaluate = evaluate;
		this.deadline = deadline;
	}
	
}