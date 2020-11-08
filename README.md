# SpringSecurity_Db_RoleBasedAuth
Spring security project using SQLite Db. Secures with BCrypt Hash and Role-based authentication to access Admin API.

### Login
- URL: localhost:8080/ <br>
- Open [Database](/src/main/resources/Database.db) with [DB Browser](https://sqlitebrowser.org/)<br>
**Username**= First name; **Password**= firstName123;<br>
Example; User *Tony Stark* username *Tony* password *tony123* 


## Overall

- Seperates the general user api with secured api (/admin).<br>
- Access to /admin is only possible to users with ADMIN role.<br>
- New users are added and role is set by ADMIN.<br>
- Added users automatically have password as their first name followed by 123.
- Any users can change their password provided that they know the old password and match 2 new password input fields.<br>


## Codes
- Uses Spring Security with password hashing using Bcrupt Hash of strength 10.<br>
- Uses native SQL quiries using "nativeQuery = true" inside @Query
- @Modifying, @Transactional,@Param annotations used to CRUD from Sqlite3 database.<br>
- Uses passwordEncoder.match(*String*, *Hash*) to verify the old password with database record; inside [PasswordChangeController](src/main/java/com/qwertyfox/thefox/controller/PasswordChangeController.java) class.



### License & Copyright
[License Link](LICENSE). <br> 
