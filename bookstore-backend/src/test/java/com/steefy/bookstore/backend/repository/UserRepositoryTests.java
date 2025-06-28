package com.steefy.bookstore.backend.repository;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.steefy.bookstore.entity.User;
import com.steefy.bookstore.entity.UserRole;
import com.steefy.bookstore.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void UserRepository_Save() {
        
        User user = User.builder()
        .userName("testUserName")
        .email("testEmail")
        .password("testPassword")
        .role(UserRole.ROLE_USER)
        .build();

        User savedUser = userRepository.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);

        Assertions.assertThat(savedUser.getUserName()).isEqualTo("testUserName");
        Assertions.assertThat(savedUser.getEmail()).isEqualTo("testEmail");
        Assertions.assertThat(savedUser.getPassword()).isEqualTo("testPassword");
        Assertions.assertThat(savedUser.getRole()).isEqualTo(UserRole.ROLE_USER);
    }

    @Test
    public void UserRepository_GetById() {

        User user = User.builder()
        .userName("testUserName")
        .email("testEmail")
        .password("testPassword")
        .role(UserRole.ROLE_USER)
        .build();

        userRepository.save(user);
        User savedUser = userRepository.findById(user.getId()).get();

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);

        Assertions.assertThat(savedUser.getUserName()).isEqualTo("testUserName");
        Assertions.assertThat(savedUser.getEmail()).isEqualTo("testEmail");
        Assertions.assertThat(savedUser.getPassword()).isEqualTo("testPassword");
        Assertions.assertThat(savedUser.getRole()).isEqualTo(UserRole.ROLE_USER);
    }

    @Test
    public void UserRepository_GetAll() {

        User user1 = User.builder()
        .userName("testUserName1")
        .email("testEmail1")
        .password("testPassword1")
        .role(UserRole.ROLE_USER)
        .build();

        User user2 = User.builder()
        .userName("testUserName2")
        .email("testEmail2")
        .password("testPassword2")
        .role(UserRole.ROLE_ADMIN)
        .build();

        userRepository.save(user1);
        userRepository.save(user2);

        List<User> users = userRepository.findAll();

        Assertions.assertThat(users).isNotNull();
        Assertions.assertThat(users.size()).isEqualTo(2);

        Assertions.assertThat(users.get(0)).isNotNull();
        Assertions.assertThat(users.get(0).getId()).isGreaterThan(0);

        Assertions.assertThat(users.get(0).getUserName()).isEqualTo("testUserName1");
        Assertions.assertThat(users.get(0).getEmail()).isEqualTo("testEmail1");
        Assertions.assertThat(users.get(0).getPassword()).isEqualTo("testPassword1");
        Assertions.assertThat(users.get(0).getRole()).isEqualTo(UserRole.ROLE_USER);


        Assertions.assertThat(users.get(1)).isNotNull();
        Assertions.assertThat(users.get(1).getId()).isGreaterThan(0);

        Assertions.assertThat(users.get(1).getUserName()).isEqualTo("testUserName2");
        Assertions.assertThat(users.get(1).getEmail()).isEqualTo("testEmail2");
        Assertions.assertThat(users.get(1).getPassword()).isEqualTo("testPassword2");
        Assertions.assertThat(users.get(1).getRole()).isEqualTo(UserRole.ROLE_ADMIN);
    }

    @Test
    public void UserRepository_Update() {

        User user = User.builder()
        .userName("testUserName")
        .email("testEmail")
        .password("testPassword")
        .role(UserRole.ROLE_USER)
        .build();

        userRepository.save(user);

        User savedUser = User.builder()
        .userName("updatedTestUserName")
        .email("updatedTestEmail")
        .password("updatedTestPassword")
        .role(UserRole.ROLE_ADMIN)
        .build();

        User updatedUser = userRepository.save(savedUser);

        Assertions.assertThat(updatedUser).isNotNull();
        Assertions.assertThat(updatedUser.getId()).isGreaterThan(0);

        Assertions.assertThat(updatedUser.getUserName()).isEqualTo("updatedTestUserName");
        Assertions.assertThat(updatedUser.getEmail()).isEqualTo("updatedTestEmail");
        Assertions.assertThat(updatedUser.getPassword()).isEqualTo("updatedTestPassword");
        Assertions.assertThat(updatedUser.getRole()).isEqualTo(UserRole.ROLE_ADMIN);
    }

    @Test
    public void UserRepository_Delete() {

        User user = User.builder()
        .userName("testUserName")
        .email("testEmail")
        .password("testPassword")
        .role(UserRole.ROLE_USER)
        .build();

        userRepository.save(user);
        userRepository.deleteById(user.getId());

        Optional<User> deletedUser = userRepository.findById(user.getId());

        Assertions.assertThat(deletedUser).isEmpty();
    }
}
