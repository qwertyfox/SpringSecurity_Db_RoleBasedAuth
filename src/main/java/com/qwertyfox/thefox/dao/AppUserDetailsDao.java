/*
* Responsible for adding record in "User" table and altering "user_details" view
* */
package com.qwertyfox.thefox.dao;

import com.qwertyfox.thefox.model.FoxUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface AppUserDetailsDao extends JpaRepository<FoxUserDetails, Integer> {


    @Modifying // Insert is not a part of the JPA so this has to be annotated
    @Transactional // Required for any query that performs deletion, insertion or update
    @Query(
            value = "INSERT INTO User (first_name, last_name, contact_no) " +
            "VALUES (:firstName, :lastName, :contactNo)",
            nativeQuery = true
    )
    int insertUser(@Param("firstName") String first_name,
                       @Param("lastName") String last_name,
                       @Param("contactNo") Integer contactNo);


    @Query(
            value = "SELECT * FROM User " +
                    "WHERE userID= (SELECT max(userID) FROM User)", // finding the last input
            nativeQuery = true
    )
    FoxUserDetails findLastRecord ();


    @Modifying
    @Transactional
    @Query(
            value = "INSERT INTO user_roles VALUES (:userID, :roleId)",
            nativeQuery = true
    )
    void insertUserRole (@Param("userID") Integer userID,
                         @Param("roleId") Integer roleId);


    @Modifying
    @Transactional
    @Query(
            value = "INSERT INTO username_password VALUES (:userID, :username, :password)",
            nativeQuery = true
    )
    void insertUsernamePassword (
            @Param("userID") Integer userID,
            @Param("username") String username,
            @Param("password") String password);

}
