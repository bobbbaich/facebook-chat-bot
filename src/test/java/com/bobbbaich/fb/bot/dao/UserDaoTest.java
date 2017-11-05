package com.bobbbaich.fb.bot.dao;

import com.bobbbaich.fb.bot.model.User;
import com.bobbbaich.fb.bot.model.UserRole;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    private User initialUser;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CrudDao<User, Long> userCrudDao;


    @Before
    public void setUp() throws Exception {
        initialUser = new User();
        initialUser.setPassword("test_pass");
        initialUser.setUsername("test_user");
        initialUser.setUserRole(UserRole.ROLE_USER);
    }

    @After
    public void tearDown() throws Exception {
        userCrudDao.delete(initialUser);
    }

    @Test
    public void findByUsername() throws Exception {
        userCrudDao.create(initialUser);

        User userByUsername = userDao.findByUsername(initialUser.getUsername());

        assertEquals(initialUser, userByUsername);
    }
}