package dao;

public class RoleDaoSingleton {
    public RoleDao value;
    private static RoleDaoSingleton instance;
    private RoleDaoSingleton() {
        this.value = new RoleDaoImpl();
    }
    public static RoleDaoSingleton getInstance() {
        if (instance == null) {
            instance = new RoleDaoSingleton();
        }
        return instance;
    }
    public RoleDao getValue() {
        return value;
    }
}
