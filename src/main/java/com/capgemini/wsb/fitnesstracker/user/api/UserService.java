package com.capgemini.wsb.fitnesstracker.user.api;

import java.util.List;
import java.util.Optional;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    /**
     * Creates a new user.
     *
     * @param user the user to create
     * @return the created user
     */
    User createUser(User user);

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to delete
     */
    void deleteUserById(Long id);

    /**
     * Updates an existing user.
     *
     * @param userId         the ID of the user to update
     * @param updatedUserDto the DTO containing the updated user data
     * @return the updated user
     */
    User updateUser(Long userId, UserDto updatedUserDto);
}