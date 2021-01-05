package io.kimmking.cache.service;

import io.kimmking.cache.entity.User;
import io.kimmking.cache.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    // 开启spring cache
    @Override
    @Cacheable(key="#id",value="userCache",unless = "#result == null")
    public User find(int id) {
        System.out.println(" ==> find " + id);
        return userMapper.find(id);
    }

    // 开启spring cache
    @Override
    @Cacheable //(key="methodName",value="userCache")
    public List<User> list(){
        return userMapper.list();
    }

    @Override
    @CachePut(key = "#user.id", value = "userCache", unless = "#result == null")
    public User update(User user) {
        userMapper.update(user);
        return find(user.getId());
    }

    @Override
    @CacheEvict(key = "#id", value = "userCache")
    public void delete(int id) {
        userMapper.delete(id);
    }

}
