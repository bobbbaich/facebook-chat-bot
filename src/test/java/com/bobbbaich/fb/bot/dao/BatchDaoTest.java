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

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BatchDaoTest {
    private static final int BATCH_SIZE = 200;

    private Set<User> users;

    @Autowired
    private BatchDao<User, Long> userBatchDao;
    @Autowired
    private CrudDao<User, Long> userCrudDao;


    @Before
    public void setUp() throws Exception {
        users = new HashSet<>(BATCH_SIZE);

        for (int i = 0; i < BATCH_SIZE; i++) {
            User user = new User();
            user.setPassword("test_pass_" + i);
            user.setUsername("test_user_" + i);
            user.setUserRole(UserRole.ROLE_USER);

            users.add(user);
        }
    }

    @After
    public void tearDown() throws Exception {
        for (User user : users) {
            userCrudDao.delete(user);
        }
    }


    @Test
    public void insertBatch() throws Exception {
        userBatchDao.insertBatch(users);
    }
}