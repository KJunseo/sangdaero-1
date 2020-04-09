package com.sangdaero.walab.user.controller;

import com.sangdaero.walab.common.board.dto.BoardDto;
import com.sangdaero.walab.interest.application.dto.InterestDto;
import com.sangdaero.walab.interest.application.service.InterestService;
import com.sangdaero.walab.user.application.dto.SimpleUser;
import com.sangdaero.walab.user.application.dto.UserDto;
import com.sangdaero.walab.user.application.dto.UserDetailDto;
import com.sangdaero.walab.user.application.service.UserService;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

	private UserService mUserService;
	private InterestService mInterestService;

	public UserController(UserService mUserService, InterestService mInterestService) {
		this.mUserService = mUserService;
		this.mInterestService = mInterestService;
	}

	@GetMapping("")
	public String userPage(Model model) {
		List<SimpleUser> simpleUsers = mUserService.getSimpleUserList();
		model.addAttribute("simpleUserList", simpleUsers);
		return "html/user/user.html";
	}

	@GetMapping("/add")
	public String add(Model model) {
		List<InterestDto> interestDTOList = mInterestService.getInterestList();
		model.addAttribute("interestList", interestDTOList);
		return "html/user/add.html";
	}

	@PostMapping("/add")
	public String addUser(UserDto userDTO) {
		mUserService.addUser(userDTO);
		return "redirect:/user";
	}

	@GetMapping("/add/{id}")
	public String detail(@PathVariable Long id, Model model) {
		UserDetailDto userDetailDTO = mUserService.getUser(id);

		model.addAttribute("userInfo", userDetailDTO);

		return "html/user/detail.html";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		UserDetailDto userDetailDTO = mUserService.getUser(id);
		List<InterestDto> allInterest = mInterestService.getInterestList();

		model.addAttribute("userInfo", userDetailDTO);
		model.addAttribute("allInterest", allInterest);

		return "html/user/update.html";
	}

	@PutMapping("/edit/{id}")
	public String update(@RequestParam(value = "interest", required = false) List<String> interest, UserDto userDto) {
		userDto.setUserInterestList(interest.toArray(new String[0]));

		mUserService.addUser(userDto);
		return "redirect:/user";
	}
}
