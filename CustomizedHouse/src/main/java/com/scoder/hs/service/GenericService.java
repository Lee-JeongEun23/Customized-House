package com.scoder.hs.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scoder.hs.domain.entity.CHUserEntity;
import com.scoder.hs.domain.entity.GenericEntity;
import com.scoder.hs.dto.CHUser;
import com.scoder.hs.dto.Generic;
import com.scoder.hs.repository.CHUserRepository;
import com.scoder.hs.repository.GenericRepository;

@Service
public class GenericService {
	
	@Autowired
	private CHUserRepository chUserRepository;
	
	@Autowired
	private GenericRepository genericRepository;
	
	
	//일반회원 마이페이지 화면에 보일 기본 정보 가져오기
	public CHUser getUserInfo (String userId) {
		Optional<CHUserEntity> chUserEntityWrapper = chUserRepository.findById(userId);
		CHUserEntity chUserEntity = chUserEntityWrapper.get();		
		CHUser chUser = CHUser.builder()
							.userId(chUserEntity.getUserId())
							.userName(chUserEntity.getUserName())
							.userEmail(chUserEntity.getUserEmail())
							.userPhoneNum(chUserEntity.getUserPhoneNum())
							.build();
		return chUser;
		
	}
	
	//회원 정보 수정에 필요한 정보 가져오기
	public Generic getUserDetailInfo (String userId) {
		Optional<GenericEntity> genericEntityWrapper = genericRepository.findById(userId);
		GenericEntity genericEntity = genericEntityWrapper.get();		
		Generic generic = Generic.builder()
							.userId(genericEntity.getUserId())
							.userAddress(genericEntity.getUserAddress())
							.userDetailAddress(genericEntity.getUserDetailAddress())
							.build();
		return generic;
		
	}

}