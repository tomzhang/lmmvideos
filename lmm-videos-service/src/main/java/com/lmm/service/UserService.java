package com.lmm.service;

import com.lmm.pojo.Users;

public interface UserService {
    /**
     * @description:判断用户名是否存在
     * @return 用户存在返回true
     * */
    public boolean queryUsernameIsExist(String username);

    /**
     * @descriptiion: 保存用户（用户注册）
     * */
    public void saveUser(Users users);

    /**
     * @description: 用户登录
     *
     * */
    public Users loginUser(String username,String password);

    /**
     * @description:用户修改信息
     *
     */
    public void updateUserInfo(Users user);


    /**
     * @descirption:查询用户信息
     * @param userId
     * @return
     */
    public Users queryUserInfo(String userId);
}
