package com.TrabajoFinal.IgnaShop.service;

import java.util.List;

import com.TrabajoFinal.IgnaShop.entity.ArticleEntity;
import com.TrabajoFinal.IgnaShop.entity.CategoryEntity;
import com.TrabajoFinal.IgnaShop.entity.UsersEntity;
import com.TrabajoFinal.IgnaShop.model.ArticleModel;
import com.TrabajoFinal.IgnaShop.model.UsersModel;

public interface ArticleService {

//	List<ArticleEntity> findArticleByCategoryId(int categoryId);

	List<ArticleEntity> listAllArticles(ArticleEntity articleEntity);

	ArticleEntity createArticle(ArticleModel articleModel);

	ArticleModel transform(ArticleEntity articleEntity);

	ArticleEntity transform(ArticleModel articleModel);

	List<ArticleModel> findArticlesByUsersId(UsersModel usersModel);

	List<ArticleEntity> listArticlesByUser(UsersEntity usersEntity);
	
	List<ArticleEntity> listArticlesByOrder();
	
	List<ArticleEntity>  listArticlesByUsersIdAndPriceAsc(UsersEntity usersId);
	
	ArticleEntity findArticleById(int id);

	public void deleteArticle(int id);

	ArticleEntity updateArticle(ArticleModel articleModel);


	List<ArticleEntity> findArticleByUsersIdAndCategoryId(UsersEntity usersId, CategoryEntity categoryId);



	


}
