package com.scoder.hs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.scoder.hs.dto.CHUserCustom;
import com.scoder.hs.dto.Generic;
import com.scoder.hs.service.GenericService;

@Controller
@RequestMapping("/generic")
public class GenericController {
	
	@Autowired
	private GenericService genericService;
	
	/**
	 * 일반회원 마이페이지로 이동
	 * @author 이정은
	 * @since 2020/03/24 
	 * @param chuserCustom
	 * @param model
	 * @return
	 */
	@GetMapping("/genericMyPage")
	public String userMyPage(@AuthenticationPrincipal CHUserCustom chuserCustom, Model model) {
		genericService.getUserInfo(chuserCustom.getUsername());
		model.addAttribute("genericUser", chuserCustom);
		return "generic/genericMyPage";
	}
	
	/**
	 * 회원정보 수정페이지로 이동
	 * @author 이정은
	 * @since 2020/03/24
	 * @param chuserCustom
	 * @param model
	 * @return "generic/genericUserEdit";
	 */
	@GetMapping("/genericUserEdit")
	public String userEditPage(@AuthenticationPrincipal CHUserCustom chuserCustom, Model model) {
		genericService.getUserInfo(chuserCustom.getUsername());
		model.addAttribute("genericUser", chuserCustom);
		
		Generic generic = genericService.getUserDetailInfo(chuserCustom.getUsername());
		model.addAttribute("generic", generic);
		return "generic/genericUserEdit";
	}
	
	
}
