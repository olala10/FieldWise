package com.example.fieldwise.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val userProfileDao: UserProfileDao) {

    //checking if the user already exists
    suspend fun isFirstTimeUser(username: String): Boolean = withContext(Dispatchers.IO) {
        val userProfile = userProfileDao.getUserProfile(username)
        return@withContext userProfile == null
    }

    //saving new user method
    suspend fun saveUserProfile(userProfile: UserProfile) = withContext(Dispatchers.IO) {
        userProfileDao.insertUserProfile(userProfile)
    }

    //getting user profile
    suspend fun getUserProfile(username: String): UserProfile? {
        return userProfileDao.getUserProfile(username)
    }

    //updating daily goal
    suspend fun updateDailyGoal(username: String, newGoal: Int) = withContext(Dispatchers.IO) {
        val userProfile = userProfileDao.getUserProfile(username)
        if (userProfile != null) {
            val updatedProfile = userProfile.copy(dailyGoal = newGoal)
            userProfileDao.insertUserProfile(updatedProfile) // Zaktualizowanie profilu z nowym celem
        }
    }

    //getting daily goal
    suspend fun getDailyGoal(username: String): Int? {
        val userProfile = userProfileDao.getUserProfile(username)
        return userProfile?.dailyGoal
    }
}

