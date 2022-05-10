package com.TrabajoFinal.IgnaShop.model;


public class CommentsModel {

	private int id;
	private int senderUser_id;
	private int receiverUser_id;
	private String text;

	public CommentsModel() {
		super();
	}

	public CommentsModel(int id, int senderUser_id, int receiverUser_id, String text) {
		super();
		this.id = id;
		this.senderUser_id = senderUser_id;
		this.receiverUser_id = receiverUser_id;
		this.text = text;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSenderUser() {
		return senderUser_id;
	}

	public void setSenderUser(int senderUser_id) {
		this.senderUser_id = senderUser_id;
	}

	public int getReceiverUser() {
		return receiverUser_id;
	}

	public void setReceiverUser(int receiverUser_id) {
		this.receiverUser_id = receiverUser_id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "MessageModel [id=" + id + ", senderUser_id=" + senderUser_id + ", receiverUser_id=" + receiverUser_id
				+ ", text=" + text + "]";
	}
	

}
