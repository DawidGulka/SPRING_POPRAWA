package com.capgemini.wsb.fitnesstracker.user.internal;

import jakarta.annotation.Nullable;

/**
 * Data Transfer Object (DTO) representing a summary of user information.
 *
 * @param id        the ID of the user
 * @param firstName the first name of the user
 * @param lastName  the last name of the user
 */
public record UserSummaryDto(@Nullable Long id, String firstName, String lastName) {
}