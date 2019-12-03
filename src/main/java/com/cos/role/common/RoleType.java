package com.cos.role.common;

public enum RoleType {
	USER(1), MANAGER(2), ADMIN(3), TESTER(4);
	
	public final int ID;
	
	private RoleType(int id) {
		this.ID = id;
	}
}
