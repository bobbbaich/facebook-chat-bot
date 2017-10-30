package com.bobbbaich.fb.bot.service.domain.impl;

import com.bobbbaich.fb.bot.dao.common.CrudDao;
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
public class CrudDaoTest {

    private User initialUser;
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
    public void create() throws Exception {
        Long id = userCrudDao.create(initialUser);

        Assert.assertNotNull(id);
    }

    @Test
    public void read() throws Exception {
        Long id = userCrudDao.create(initialUser);

        User obtainedUser = userCrudDao.read(id);

        assertEquals(initialUser, obtainedUser);
    }

    @Test
    public void update() throws Exception {
        Long id = userCrudDao.create(initialUser);
        initialUser.setUserRole(UserRole.ROLE_ADMIN);
        initialUser.setUsername("changed_test_user");
        userCrudDao.update(initialUser);

        User updatedUser = userCrudDao.read(id);

        assertEquals(initialUser, updatedUser);
    }

//    TODO: fix delete method
//    @Test
//    public void delete() throws Exception {
//        Long id = userCrudDao.create(initialUser);
//
//        userCrudDao.delete(initialUser);
//
//        User obtainedUser = userCrudDao.read(id);
//        assertNull(obtainedUser);
//    }
}