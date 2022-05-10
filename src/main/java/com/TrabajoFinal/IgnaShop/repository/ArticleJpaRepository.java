package com.TrabajoFinal.IgnaShop.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TrabajoFinal.IgnaShop.entity.ArticleEntity;
import com.TrabajoFinal.IgnaShop.entity.UsersEntity;


public interface ArticleJpaRepository extends JpaRepository<ArticleEntity, Serializable> {

	
	public List<ArticleEntity> findArticlesByUsersId(UsersEntity usersId);
	ArticleEntity findArticleById(int id);
	
}
