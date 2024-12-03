package com.example.day33exercise.Service;

import com.example.day33exercise.ApiResponse.ApiException;
import com.example.day33exercise.Model.UserAccount;
import com.example.day33exercise.Repository.UserAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserAccountService
{
    private final UserAccountRepository userAccountRepository;

    public List<UserAccount> getAll(){
        return userAccountRepository.findAll();
    }

    public void add(UserAccount userAccount){
        userAccountRepository.save(userAccount);
    }
    public void update(Integer id,UserAccount userAccount){
        UserAccount tempUser = userAccountRepository.findUserAccountById(id);
        if (tempUser == null){
            throw new ApiException("no user with this id was found");
        }
        tempUser.setName(userAccount.getName());
        tempUser.setUsername(userAccount.getUsername());
        tempUser.setPassword(userAccount.getPassword());
        tempUser.setEmail(userAccount.getEmail());
        tempUser.setRole(userAccount.getRole());
        tempUser.setAge(userAccount.getAge());
        userAccountRepository.save(tempUser);

    }

    public void delete(Integer id){
        UserAccount tempUser = userAccountRepository.findUserAccountById(id);
        if (tempUser == null){
            throw new ApiException("no user with this id was found");
        }
        userAccountRepository.delete(tempUser);
    }

    public UserAccount login(String username, String password){
        UserAccount tempUser = userAccountRepository.getUserByUsernameAndPassword(username, password);
        if (tempUser == null){
            throw new ApiException("no user with this username or password was found");
        }
        return tempUser;
    }

    public UserAccount getUserAccountByEmail(String email){
        UserAccount tempUser = userAccountRepository.findUserAccountByEmail(email);
        if (tempUser == null){
            throw new ApiException("no user with this email was found");
        }
        return tempUser;
    }

    public List<UserAccount> getUserAccountsByRole(String role){
     List <UserAccount> tempUsers = userAccountRepository.findUserAccountByRole(role);
        if (tempUsers.isEmpty()){
            throw new ApiException("no users with this role were found");
        }
        return tempUsers;
    }

    public List<UserAccount> getUserAccountsByMinAge(Integer min){
      List<UserAccount>tempUsers = userAccountRepository.getUserBySpecAge(min);
      if (tempUsers.isEmpty()){
          throw new ApiException("no users with this age or higher was found");
      }
        return tempUsers;
    }

}
