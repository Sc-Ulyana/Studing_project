package dao;

import domain.Role;
import utilities.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoleDaoImpl implements RoleDao{
    @Override
    public void addUserRole(int userId, ArrayList<Role> roles) {
        String sqlInsertRoles = "INSERT INTO project.user_roles (user_id, role_id) values (?, ?);";
        try (Connection connection = DBConnection.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertRoles)){
            for (Role r : roles) {
                preparedStatement.setInt(1, userId);
                preparedStatement.setInt(2, r.getId());
                preparedStatement.executeUpdate();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void editUserRole(int userId, ArrayList<Role> roles) {
        String sqlDeleteUserRoles = "DELETE  FROM project.user_roles WHERE user_id = ?;";
        String sqlInsertUserRoles = "INSERT INTO project.user_roles (user_id, role_id) values (?, ?);";
        try(Connection connection = DBConnection.getConnect();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlDeleteUserRoles);
        PreparedStatement secondPreparedStatement = connection.prepareStatement(sqlInsertUserRoles)
        ) {

            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();

            for (Role r : roles) {
                secondPreparedStatement.setInt(1, userId);
                secondPreparedStatement.setInt(2, r.getId());
                secondPreparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getUserRoles(String login, ArrayList<Role> roles) {
        String sqlRolesSelect = "SELECT user_id, role_id, project.roles.name FROM project.user_roles LEFT JOIN project.roles on project.roles.id = user_roles.role_id LEFT JOIN project.users on project.users.id = user_roles.user_id where login=?;";
        try(Connection connection = DBConnection.getConnect();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlRolesSelect)){
            preparedStatement.setString(1,login);
            ResultSet rsRolesSelect = preparedStatement.executeQuery();
            while (rsRolesSelect.next()) {
                Role role = new Role(
                        rsRolesSelect.getInt("role_id"),
                        rsRolesSelect.getString("name")
                );
                roles.add(role);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void getAllUsersRoles(int userId, ArrayList<Role> roles) {
        String sqlRolesSelect = "SELECT user_id, role_id, project.roles.name FROM project.user_roles LEFT JOIN project.roles on project.roles.id = user_roles.role_id LEFT JOIN project.users on project.users.id = user_roles.user_id where user_id=?;";
        try(Connection connection = DBConnection.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlRolesSelect)){
            preparedStatement.setInt(1, userId);
            ResultSet rsRolesSelect = preparedStatement.executeQuery();
            while (rsRolesSelect.next()) {
                Role role = new Role(
                        rsRolesSelect.getInt("role_id"),
                        rsRolesSelect.getString("name")
                );
                roles.add(role);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
