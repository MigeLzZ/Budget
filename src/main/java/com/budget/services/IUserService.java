package com.budget.services;

import com.budget.dao.entities.Role;
import com.budget.dao.entities.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface IUserService {
    static boolean hasAllEmployeeRoleType(User user) {
        return user.getRoles().stream().anyMatch(r -> r.getType() == Role.RoleType.ROLE_ADMIN);
    }

    /**
     * Получить всех пользователей приложения
     *
     * @return
     */
    List<User> findAllUsers();

    /**
     * Получить пользователя по его имени пользователя
     *
     * @param username
     * @return
     */

    User findUserByUsername(String username);

    /**
     * Получить все роли приложения
     *
     * @return
     */
    List<Role> findAllRoles();

    /**
     * Удалить пользователя
     *
     * @param id
     */
    void deleteUser(long id);

    /**
     * Сохранить или изменить пользователя
     *
     * @param user
     * @return
     */
    User saveUser(User user);


    @CacheEvict(value = "DataCache", allEntries = true)
    User saveCurrentUserWithDetailsUpdate(User user);

    /**
     * Изменить пароль для пользователя
     *
     * @param username
     * @param newPassword
     */
    void changeUserPassword(String username, String currentPassword, String newPassword) throws Exception;

    public void setUserRole(User user);

    @Cacheable(value = "DataCache", key = "'UserService_' + #root.methodName + '_' + #login")
    boolean checkIfUserExists(String login);

    @Cacheable(value = "DataCache", key = "'UserService_' + #root.methodName + '_' + #mail")
    boolean checkIfMailExists(String mail);

    User changeUsername(long userId, String newUsername);

    User changeUserMail(long userId, String newMail);

    User changeUserLimit(long userId, float limit);
}
