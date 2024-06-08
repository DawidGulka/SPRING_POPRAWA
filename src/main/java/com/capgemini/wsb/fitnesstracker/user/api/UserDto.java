package com.capgemini.wsb.fitnesstracker.user.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;

import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) representing a user.
 *
 * @param id        the ID of the user
 * @param firstName the first name of the user
 * @param lastName  the last name of the user
 * @param birthdate the birthdate of the user
 * @param email     the email of the user
 */
public record UserDto(@Nullable Long id, String firstName, String lastName,
                      @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthdate,
                      String email) {
}