package com.TrabajoFinal.IgnaShop.model;

public class ValuationsModel {

	private int id;
	private int sellerUser_id;
	private int buyerUser_id;
	private int level;
	
	public ValuationsModel() {
		super();
	}

	public ValuationsModel(int id, int sellerUser_id, int buyerUser_id, int level) {
		super();
		this.id = id;
		this.sellerUser_id = sellerUser_id;
		this.buyerUser_id = buyerUser_id;
		this.level = level;

	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSellerUser_id() {
		return sellerUser_id;
	}

	public void setSellerUser_id(int sellerUser_id) {
		this.sellerUser_id = sellerUser_id;
	}

	public int getBuyerUser_id() {
		return buyerUser_id;
	}

	public void setBuyerUser_id(int buyerUser_id) {
		this.buyerUser_id = buyerUser_id;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "ValuationsModel [id=" + id + ", SellerUser_id=" + sellerUser_id + ", BuyerUser_id=" + buyerUser_id + ", level="
				+ level + "]";
	}

}
