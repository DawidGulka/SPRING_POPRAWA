package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing users.
 */
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    /**
     * Retrieves all users.
     *
     * @return a list of all users
     */
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Retrieves basic information about all users.
     *
     * @return a list of user summaries
     */
    @GetMapping("/simple")
    public List<UserSummaryDto> getAllUsersBasicInfo() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toSummaryDto)
                .toList();
    }

    /**
     * Adds a new user.
     *
     * @param userDto the DTO containing the data for the new user
     * @return the created user
     * @throws InterruptedException if the operation is interrupted
     */
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody UserDto userDto) throws InterruptedException {
        System.out.println("User with e-mail: " + userDto.email() + " passed to the request");

        User user = userMapper.toEntity(userDto);
        User savedUser = userService.createUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user to retrieve
     * @return the user DTO
     */
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        User user = userService.getUser(id).orElseThrow(() -> new UserNotFoundException(id));
        return userMapper.toDto(user);
    }

    /**
     * Searches for users based on various criteria.
     *
     * @param firstName the first name of the user to search for
     * @param lastName  the last name of the user to search for
     * @param birthdate the birthdate of the user to search for
     * @param minAge    the minimum age of the users to search for
     * @return a list of users matching the search criteria
     */
    @GetMapping("/search")
    public List<UserDto> searchUsers(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) LocalDate birthdate,
            @RequestParam(required = false) Integer minAge) {

        List<User> users;
        if (minAge != null) {
            LocalDate startDate = LocalDate.now().minusYears(minAge);
            users = userService.findUsersByBirthdateAfter(startDate);
        } else if (birthdate != null) {
            users = userService.findUsersByBirthdate(birthdate);
        } else if (firstName != null && lastName != null) {
            users = userService.findUsersByFirstNameAndLastName(firstName, lastName);
        } else {
            users = userService.findAllUsers();
        }

        return users.stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Retrieves a user by their email.
     *
     * @param email the email of the user to retrieve
     * @return the user DTO
     */
    @GetMapping("/email")
    public ResponseEntity<UserDto> getUserByEmail(@RequestParam String email) {
        Optional<User> user = userService.getUserByEmail(email);
        return user.map(value -> ResponseEntity.ok(userMapper.toDto(value)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Retrieves users older than a specified date.
     *
     * @param date the date to compare against
     * @return a list of users older than the specified date
     */
    @GetMapping("/older/{date}")
    public ResponseEntity<List<User>> getUsersOlderThan(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<User> users = userService.findUsersByBirthdateAfter(date);
        return ResponseEntity.ok(users);
    }

    /**
     * Deletes a user by their ID.
     *
     * @param userId the ID of the user to delete
     * @return a response entity indicating the result of the operation
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Updates an existing user.
     *
     * @param id             the ID of the user to update
     * @param updatedUserDto the DTO containing the updated user data
     * @return the updated user DTO
     */
    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto updatedUserDto) {
        User updatedUser = userService.updateUser(id, updatedUserDto);
        return userMapper.toDto(updatedUser);
    }
}