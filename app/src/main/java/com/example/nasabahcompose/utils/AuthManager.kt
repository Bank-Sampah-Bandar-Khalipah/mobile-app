package com.example.nasabahcompose.utils

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

object AuthManager {

    /**
     * Save login state to SharedPreferences
     */
    fun saveLoginState(context: Context, username: String, role: String) {
        val sharedPref = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putBoolean("isLoggedIn", true)
            putString("username", username)
            putString("role", role)
            apply()
        }
    }

    /**
     * Clear login state and sign out from Firebase
     */
    fun logout(context: Context) {
        val sharedPref = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            clear()
            apply()
        }
        // Sign out from Firebase
        FirebaseAuth.getInstance().signOut()
    }

    /**
     * Check if user is currently logged in
     */
    fun isLoggedIn(context: Context): Boolean {
        val sharedPref = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("isLoggedIn", false)
    }

    /**
     * Get saved username
     */
    fun getUsername(context: Context): String {
        val sharedPref = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        return sharedPref.getString("username", "") ?: ""
    }

    /**
     * Get saved role
     */
    fun getRole(context: Context): String {
        val sharedPref = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        return sharedPref.getString("role", "user") ?: "user"
    }

    /**
     * Check user authentication status on app start
     */
    fun checkUserAuth(
        context: Context,
        onResult: (Boolean, String, String) -> Unit
    ) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val sharedPref = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)

        if (currentUser != null) {
            // User is logged in Firebase, fetch data from Realtime Database
            val uid = currentUser.uid
            val userRef = FirebaseDatabase.getInstance()
                .getReference("user")
                .child(uid)

            userRef.get()
                .addOnSuccessListener { snapshot ->
                    val username = snapshot.child("username").getValue(String::class.java) ?: "User"
                    val role = snapshot.child("role").getValue(String::class.java) ?: "user"

                    // Save to SharedPreferences
                    saveLoginState(context, username, role)

                    onResult(true, username, role)
                }
                .addOnFailureListener {
                    // If failed to fetch from Firebase, use SharedPreferences
                    val isLoggedIn = sharedPref.getBoolean("isLoggedIn", false)
                    val username = sharedPref.getString("username", "") ?: ""
                    val role = sharedPref.getString("role", "user") ?: "user"

                    if (isLoggedIn && username.isNotEmpty()) {
                        onResult(true, username, role)
                    } else {
                        onResult(false, "", "")
                    }
                }
        } else {
            // No Firebase user, check SharedPreferences
            val isLoggedIn = sharedPref.getBoolean("isLoggedIn", false)
            val username = sharedPref.getString("username", "") ?: ""
            val role = sharedPref.getString("role", "user") ?: "user"

            if (isLoggedIn && username.isNotEmpty()) {
                onResult(true, username, role)
            } else {
                onResult(false, "", "")
            }
        }
    }
}