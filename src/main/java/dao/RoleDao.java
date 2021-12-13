package dao;

import domain.Role;

import java.util.ArrayList;

public interface RoleDao {
    void addUserRole(int userId,ArrayList<Role> roles);

    void editUserRole(int userId,ArrayList<Role> roles);

    void getAllUsersRoles(int userId, ArrayList<Role> roles);

    void getUserRoles(String login, ArrayList<Role> roles);
}
