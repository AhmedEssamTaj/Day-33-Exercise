package com.example.day33exercise.Repository;

import com.example.day33exercise.Model.UserAccount;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

    UserAccount findUserAccountById(Integer id);

    @Query("select u from UserAccount u where u.username = ?1 and u.password = ?2")
    UserAccount getUserByUsernameAndPassword(String username, String password);

    UserAccount findUserAccountByEmail(String email);

    List<UserAccount> findUserAccountByRole(String role);

    @Query("select u from UserAccount u where u.age>=?1")
    List<UserAccount> getUserBySpecAge(Integer min);
}
