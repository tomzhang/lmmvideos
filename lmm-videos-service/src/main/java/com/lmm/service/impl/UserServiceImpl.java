/**
 * @program: lmmvideos
 * @description: UserService实现类
 * @author: minmin.liu
 * @create: 2018-09-28 15:34
 **/
package com.lmm.service.impl;

import com.lmm.mapper.UsersMapper;
import com.lmm.pojo.Users;
import com.lmm.service.UserService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUsernameIsExist(String username) {
        Users users=new Users();
        users.setUsername(username);

        Users result=usersMapper.selectOne(users);
        return result==null ?false:true;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveUser(Users users) {
        //创建id
        String userId=sid.nextShort();
        users.setId(userId);
        usersMapper.insert(users);
    }

    @Override
    public Users loginUser(String username,String password) {
        Example userExample=new Example(Users.class);
        Example.Criteria criteria=userExample.createCriteria();
        criteria.andEqualTo("username",username);
        criteria.andEqualTo("password",password);

        Users result=usersMapper.selectOneByExample(userExample);

        return result;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public void updateUserInfo(Users user) {
        Example userExample=new Example(Users.class);
        Example.Criteria criteria=userExample.createCriteria();
        criteria.andEqualTo("id",user.getId());
        usersMapper.updateByExampleSelective(user,userExample);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users queryUserInfo(String userId) {
        Example userExample=new Example(Users.class);
        Example.Criteria criteria=userExample.createCriteria();
        criteria.andEqualTo("id",userId);
        Users users=usersMapper.selectOneByExample(userExample);
        return users;
    }
}
