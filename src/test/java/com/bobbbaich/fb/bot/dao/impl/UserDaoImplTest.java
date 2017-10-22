package com.bobbbaich.fb.bot.dao.impl;

import com.bobbbaich.fb.bot.dao.UserDao;
import com.bobbbaich.fb.bot.model.User;
import com.bobbbaich.fb.bot.model.UserRole;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoImplTest {

    private User initialUser;
    @Autowired
    private UserDao userDao;

    @Before
    public void setUp() throws Exception {
        initialUser = new User();
        initialUser.setPassword("test_pass");
        initialUser.setUsername("test_user");
        initialUser.setUserRole(UserRole.ROLE_USER);
    }

    @After
    public void tearDown() throws Exception {
        userDao.delete(initialUser);
    }

    @Test
    public void create() throws Exception {
        Long id = userDao.create(initialUser);

        Assert.assertNotNull(id);
    }

    @Test
    public void read() throws Exception {
        Long id = userDao.create(initialUser);

        User obtainedUser = userDao.read(id);

        assertEquals(initialUser, obtainedUser);
    }

    @Test
    public void update() throws Exception {
        Long id = userDao.create(initialUser);
        initialUser.setUserRole(UserRole.ROLE_ADMIN);
        initialUser.setUsername("changed_test_user");
        userDao.update(initialUser);

        User updatedUser = userDao.read(id);

        assertEquals(initialUser, updatedUser);
    }

//    TODO: fix delete method
//    @Test
//    public void delete() throws Exception {
//        Long id = userDao.create(initialUser);
//
//        userDao.delete(initialUser);
//
//        User obtainedUser = userDao.read(id);
//        assertNull(obtainedUser);
//    }

    @Test
    public void findByUsername() throws Exception {
        userDao.create(initialUser);

        User userByUsername = userDao.findByUsername(initialUser.getUsername());

        assertEquals(initialUser, userByUsername);
    }

    @Test
    public void exists() throws Exception {
    }

}