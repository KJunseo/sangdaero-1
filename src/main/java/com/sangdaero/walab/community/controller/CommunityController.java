package com.sangdaero.walab.community.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sangdaero.walab.common.category.controller.CategoryController;
import com.sangdaero.walab.common.category.dto.CategoryDto;
import com.sangdaero.walab.community.dto.CommunityDto;
import com.sangdaero.walab.community.service.CommunityService;

@Controller
@RequestMapping("/community")
public class CommunityController extends CategoryController {
	
	
	private CommunityService mCommunityService;
	
	public CommunityController(CommunityService communityService) {
		super(communityService);
		this.mCommunityService = communityService;
	}
	// Community list page
	
	@GetMapping("")
	public String list(
			Model model,
			@RequestParam(value = "page", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "category", defaultValue = "1") Long category,
			@RequestParam(value = "keyword", defaultValue = "") String keyword,
			@RequestParam(value = "type", defaultValue = "0") Integer searchType) {
		
        List<CommunityDto> communityDtoList = mCommunityService.getCommunitylist(pageNum, category, keyword, searchType);
        Integer[] pageList = mCommunityService.getPageList(pageNum, category, keyword, searchType);
        
        List<CategoryDto> categoryDtoList = mCommunityService.getCategory((byte)2);

        model.addAttribute("categoryList", categoryDtoList);
        model.addAttribute("communityList", communityDtoList);
        model.addAttribute("pageList", pageList);
        model.addAttribute("category", category);
        model.addAttribute("keyword", keyword);
        model.addAttribute("type", searchType);

        return "html/community/list.html";
    }

	// Writing community page
	@GetMapping("/post")
    public String write(Model model) {
		List<CategoryDto> categoryDtoList = mCommunityService.getCategory((byte)2);
		
		model.addAttribute("categoryDto", categoryDtoList);
		
        return "html/community/write.html";
    }

	// Execute when click save button
    @PostMapping("/post")
    public String write(CommunityDto communityDto) {
        mCommunityService.savePost(communityDto);
        return "redirect:/community";
    }

    // Detail page of community
    @GetMapping("/post/{no}")
    public String detail(@PathVariable("no") Long id, Model model) {
        CommunityDto communityDto = mCommunityService.getPost(id);
        
        // Category with Korean which shows to detail page
        String category;
        
        // 수정 필요
        switch(communityDto.getCategoryId().toString()) {
	        case "1":
	        	category = "전체";
	        	break;
	        case "2":
	        	category = "자원봉사자";
	        	break;
	        case "3":
	        	category = "이용자";
	        	break;
	        default:
	        	category = "에러";
	        	break;
        }

        model.addAttribute("communityDto", communityDto);
        model.addAttribute("category", category);
        
        
        return "html/community/detail.html";
    }

    // Edit page which through detail
    @GetMapping("/post/edit/{no}")
    public String edit(@PathVariable("no") Long id, Model model) {
        CommunityDto communityDto = mCommunityService.getPost(id);
        List<CategoryDto> categoryDtoList = mCommunityService.getCategory((byte)2);
		
        model.addAttribute("communityDto", communityDto);
        model.addAttribute("categoryDto", categoryDtoList);
        return "html/community/update.html";
    }

    // Saving edit content
    @PutMapping("/post/edit/{no}")
    public String update(CommunityDto communityDto) {
        mCommunityService.updatePost(communityDto);
        return "redirect:/community";
    }

    // Deleting community
    @DeleteMapping("/post/{no}")
    public String delete(@PathVariable("no") Long id) {
        mCommunityService.deletePost(id);

        return "redirect:/community";
    }
}
