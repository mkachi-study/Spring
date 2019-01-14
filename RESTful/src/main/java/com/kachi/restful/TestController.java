package com.kachi.restful;

import Manager.BatisManager;
import Mapper.UserMapper;
import Model.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/index")
public class TestController {
    private static SqlSessionFactory _factory;
    static {
        _factory = BatisManager.getSqlSessionFactory();
    }

    public String index() {
        return "Hello!";
    }

    @RequestMapping(method = RequestMethod.GET, value="/select")
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

    @RequestMapping(method = RequestMethod.POST, value = "/insert")
    public boolean insertUser(@RequestParam(value = "name") String name) {
        SqlSession session = _factory.openSession();
        try {
            UserMapper mapper = session.getMapper(UserMapper.class);
            mapper.insertUser(name);
            session.commit();
        } catch (Exception except) {
            System.out.println(except.getMessage());
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    @RequestMapping(method = RequestMethod.POST, value="/update")
    public boolean updateUser(@RequestParam Map<String, Object> param) {
        SqlSession session = _factory.openSession();
        try {
            UserMapper mapper = session.getMapper(UserMapper.class);
            long id = Integer.valueOf((String)param.get("id"));
            User user = new User(id, (String)param.get("name"));
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

    @RequestMapping(method = RequestMethod.GET, value="/deleteall")
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

    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    public boolean deleteUser(@RequestParam(value = "id") int id) {
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
