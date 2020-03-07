package com.josefczech.blog.examples.datajpatestflushandclear;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(properties = {
    "logging.level.ROOT= WARN",
    "logging.level.org.springframework.test.context.transaction= INFO",
    "logging.level.org.hibernate.SQL= DEBUG"
})
public class BlogTests {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void givenMultipleUsersWithSameName_Save_ShouldKickError() {
        userRepository.save(new User("foo"));
        userRepository.save(new User("foo")); // oops, there's an error, the test should catch it

        testEntityManager.flush();
    }

    @Test
    public void givenUserAndArticle_Save_ShouldPersistBothToDatabase() {
        Article article = new Article("some content");

        User user = new User("foo");
        user.addArticle(article);
        user = userRepository.save(user);

        // all good here!
        assertThat(user.getArticles()).hasSize(1);

        testEntityManager.flush();
        testEntityManager.clear();

        Optional<User> fetchedUser = userRepository.findById(user.getId());

        assertThat(fetchedUser).isPresent();
        // the article must be saved manually OR User#articles should have CascadeType.ALL
        assertThat(fetchedUser.get().getArticles())
            .withFailMessage("the article was never persisted")
            .hasSize(1);
    }
}
