package com.example.day33exercise.Controller;

import com.example.day33exercise.ApiResponse.ApiResponse;
import com.example.day33exercise.Model.UserAccount;
import com.example.day33exercise.Service.UserAccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserAccountController {

    private final UserAccountService userAccountService;

    // READ
    @GetMapping("/get-all")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(200).body(userAccountService.getAll());
    }

    // CREATE
    @PostMapping("/add")
    public ResponseEntity add(@RequestBody @Valid UserAccount userAccount, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        userAccountService.add(userAccount);
        return ResponseEntity.status(200).body(new ApiResponse("user added successfully"));
    }

    // UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody @Valid UserAccount userAccount, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        userAccountService.update(id, userAccount);
        return ResponseEntity.status(200).body(new ApiResponse("user updated successfully"));
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        userAccountService.delete(id);
        return ResponseEntity.status(200).body(new ApiResponse("user deleted successfully"));
    }

    // Login
    @GetMapping("login/{username}/{password}")
    public ResponseEntity login(@PathVariable String username, @PathVariable String password) {
        return ResponseEntity.status(200).body(userAccountService.login(username, password));
    }

    // Get by Email
    @GetMapping("/get-email/{email}")
    public ResponseEntity getUserAccountByEmail(@PathVariable String email) {
        return ResponseEntity.status(200).body(userAccountService.getUserAccountByEmail(email));
    }

    // Get users by role
    @GetMapping("/get-role/{role}")
    public ResponseEntity getUsersByRole (@PathVariable String role){
        return ResponseEntity.status(200).body(userAccountService.getUserAccountsByRole(role));
    }

    // Get users by specific age or higher
    @GetMapping("/get-age/{minAge}")
    public ResponseEntity getUsersBySpecAge (@PathVariable Integer minAge){
        return ResponseEntity.status(200).body(userAccountService.getUserAccountsByMinAge(minAge));
    }
}
