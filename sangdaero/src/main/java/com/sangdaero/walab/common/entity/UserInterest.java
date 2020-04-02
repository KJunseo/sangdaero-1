package com.sangdaero.walab.mapper.entity;

import com.sangdaero.walab.interest.domain.entity.InterestCategory;
import com.sangdaero.walab.mapper.id.UserInterestId;
import com.sangdaero.walab.user.domain.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@IdClass(UserInterestId.class)
public class UserInterest {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "interest_id")
    private InterestCategory interest;
}
