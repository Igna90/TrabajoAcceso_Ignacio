package com.TrabajoFinal.IgnaShop.service;

import org.springframework.stereotype.Service;

import com.TrabajoFinal.IgnaShop.entity.UsersEntity;
import com.TrabajoFinal.IgnaShop.model.UsersModel;

@Service("userService")
public interface UserService {

	UsersEntity findUserByEmail(String email);

	UsersModel findUserByName(String name);

	UsersModel transform(UsersEntity usersEntity);

	UsersEntity transform(UsersModel usersModel);

	UsersEntity editUser(UsersModel usersModel);

}
