package com.josefczech.blog.examples.datajpatestflushandclear;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest(properties = {
    "logging.level.ROOT= WARN",
    "logging.level.org.springframework.test.context.transaction= INFO",
    "logging.level.org.hibernate.SQL= DEBUG"
})
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void givenMultipleUsersWithSameName_Save_ShouldKickError() {
        userRepository.save(new User("foo"));
        userRepository.save(new User("foo"));

        testEntityManager.flush();
    }
}
