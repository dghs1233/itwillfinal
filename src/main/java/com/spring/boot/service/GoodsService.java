package com.spring.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.boot.dao.GoodsRepository;
import com.spring.boot.dto.Goods;
import com.spring.boot.dto.GoodsForm;
import com.spring.boot.model.SiteUser;

@Service
public class GoodsService {
	
	@Autowired
	private GoodsRepository goodsRepository;
	
	@Transactional
    public void registerProduct(GoodsForm goodsForm, Goods goods, Long userId) {
        // Goods 엔티티에 나머지 정보 설정
        goods.setName(goodsForm.getName());
        goods.setContent(goodsForm.getContent());
        goods.setPrice(goodsForm.getPrice());
        goods.setCategory(goodsForm.getCategory());
        goods.setStock(goodsForm.getStock());

        // 사용자 정보 설정 (글 작성시 product 테이블에 사용자 id 값도 넣기)
        SiteUser user = new SiteUser();
        user.setId(userId);
        goods.setUser(user);

        // 상품 등록
        goodsRepository.save(goods);
    }
	
	//상품 출력
	public List<Goods> getAllProducts() {
        // 모든 상품을 가져오는 로직
        return goodsRepository.findAll();
    }
	
	//Sale 출력
	public List<Goods> getSaleProducts() {
	    return goodsRepository.findByStockGreaterThan(0);
	}
	
	//Soldout 출력
	public List<Goods> getSoldoutProducts() {
	    return goodsRepository.findByStockEquals(0);
	}
	
	public Page<Goods> getProductsPaged(int pageNum, int itemsPerPage) {
        Pageable pageable = PageRequest.of(pageNum - 1, itemsPerPage);
        return goodsRepository.findAll(pageable);
    }

    public long getTotalItemCount() {
        return goodsRepository.count();
    }
    
    public Goods getGoodsById(int pno) {
        // 상품 ID를 사용하여 데이터베이스에서 상품 가져오기
        return goodsRepository.findById(pno).orElse(null);
    }
    
    @Transactional
    public void updateGoods(int pno, GoodsForm goodsForm) {
        // 상품 ID를 사용하여 데이터베이스에서 상품을 가져옵니다.
        Goods existingGoods = goodsRepository.findById(pno).orElse(null);
        
        if (existingGoods != null) {
            // 상품 정보 업데이트
            existingGoods.setName(goodsForm.getName());
            existingGoods.setContent(goodsForm.getContent());
            existingGoods.setPrice(goodsForm.getPrice());
            existingGoods.setCategory(goodsForm.getCategory());
            existingGoods.setStock(goodsForm.getStock());
            
            // 상품 업데이트
            goodsRepository.save(existingGoods);
        }
    }
   
}    