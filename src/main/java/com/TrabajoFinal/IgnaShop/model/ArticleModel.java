package com.TrabajoFinal.IgnaShop.model;

import java.math.BigDecimal;

public class ArticleModel {

	private int id;
	private String name;
	private String description;
	private BigDecimal price;
	private String image;
	private int usersId;
	private int categoryId;
	private int code;

	public ArticleModel() {
		super();
	}

	public ArticleModel(int id, String name, String description, BigDecimal price, String image, int usersId,
			int categoryId, int code) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.image = image;
		this.usersId = usersId;
		this.categoryId = categoryId;
		this.code = code;

	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int category_id) {
		this.categoryId = category_id;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getUsersId() {
		return usersId;
	}

	public void setusersId(int usersId) {
		this.usersId = usersId;
	}



	@Override
	public String toString() {
		return "ArticleModel [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", image=" + image + ", user_id=" + usersId + ", categoryId=" + categoryId + "]";
	}

}
