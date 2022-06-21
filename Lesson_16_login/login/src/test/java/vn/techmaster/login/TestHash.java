package vn.techmaster.login;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

import vn.techmaster.login.hash.Hashing;

@SpringBootTest
public class TestHash {
    @Autowired
    Hashing hashing;

    @Test
    public void hashPassword() {
        var passwords = List.of("abc", "123456@", "123456789");
        for (String password : passwords) {
            String hashedPass = hashing.hashPassword(password);
            assertThat(hashedPass).isNotNull();
            System.out.println(hashedPass);
        }
    }

    @Test
    public void validatePassword() {
        var passwords = List.of("abc", "123456@", "123456789");
        for (String password : passwords) {
            String hashedPass = hashing.hashPassword(password);
            assertThat(hashing.validatePassword(password, hashedPass)).isTrue();
        }
        assertThat(hashing.validatePassword("123",
                "1000:2c06f8ebcada0f08fff3dd4042d37b5f:31f09f8aafe2ff9c17da30bada9b545f21c170403d52fbe4b7050b773bda21205eedae6c600895df719d75368276b0dca3cf72ee48f81df15ac421549ae35c48"))
                .isFalse();
    }
}
