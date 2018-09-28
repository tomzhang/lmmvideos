package com.lmm.service;

import com.lmm.pojo.Users;

public interface UserService {
    /**
     * @description:判断用户名是否存在
     * @return 用户存在返回true
     * */
    public boolean queryUsernameIsExist(String username);

    /**
     * @desciptiion: 保存用户（用户注册）
     * */
    public void saveUser(Users users);
}