package com.spring.boot.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.boot.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer>{
	
	//리뷰전체조회
	Page<Review> findAll(Pageable pageable);
	
	//한id가 작성한 리스트 보기
	Page<Review> findByrUser(Integer rUser,Pageable pageable);
	
	//지금 뭘로 뿌려야 할지 모르겠어 쌤은 옵서녈 레포에 안만드셧던데 걍 쓰는거임 ..? 
	//아 레포에서는옵셔널안쓰고 페이징쓰네 
	
	
	
	

}
