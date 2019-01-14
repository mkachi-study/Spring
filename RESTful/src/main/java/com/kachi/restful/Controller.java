package com.kachi.restful;

import Manager.BatisManager;
import Mapper.UserMapper;
import Model.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/index")
public class Controller {
    private static SqlSessionFactory _factory;
    static {
        _factory = BatisManager.getSqlSessionFactory();
    }

    public String index() {
        return "Hello!";
    }

    @RequestMapping(method = RequestMethod.POST, value="/test")
    public List<User> getUsers() {
        List<User> result;
        SqlSession session = _factory.openSession();
        try {
            UserMapper mapper = session.getMapper(UserMapper.class);
            result = mapper.getUsers();
            session.commit();

        } catch (Exception except) {
            System.out.println(except.getMessage());
            return null;

        } finally {
            session.close();
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value="/test")
    public boolean insertUser(@RequestBody String name) {
        SqlSession session = _factory.openSession();
        try {
            UserMapper mapper = session.getMapper(UserMapper.class);
            mapper.insert(name);
            session.commit();

        } catch (Exception except) {
            System.out.println(except.getMessage());
            return false;

        } finally {
            session.close();
        }
        return true;
    }

    @RequestMapping(method = RequestMethod.POST, value="/test")
    public boolean updateUser(@PathVariable int id, @PathVariable String name) {
        SqlSession session = _factory.openSession();
        try {
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user = new User(id, name);
            mapper.updateUser(user);
            session.commit();

        } catch (Exception except) {
            System.out.println(except.getMessage());
            return false;

        } finally {
            session.close();
        }
        return true;
    }

    @RequestMapping(method = RequestMethod.DELETE, value="/tests")
    public boolean deleteUsers() {
        SqlSession session = _factory.openSession();
        try {
            UserMapper mapper = session.getMapper(UserMapper.class);
            mapper.deleteAllUsers();
            session.commit();
        } catch (Exception except) {
            System.out.println(except.getMessage());
            return false;

        } finally {
            session.close();
        }
        return true;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
    public boolean deleteUser(@PathVariable int id) {
        SqlSession session = _factory.openSession();
        try {
            UserMapper mapper = session.getMapper(UserMapper.class);
            mapper.deleteUser(id);
            session.commit();
        } catch (Exception except) {
            System.out.println(except.getMessage());
            return false;

        } finally {
            session.close();
        }
        return true;
    }
}
