package app.model.authorization;

import app.model.entity.Role;

public interface RoleService {
    Role getRoleByUserId(Long id);
}
