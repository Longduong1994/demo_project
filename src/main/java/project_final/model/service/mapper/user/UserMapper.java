package project_final.model.service.mapper.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project_final.model.dto.request.UserRequest;
import project_final.model.dto.response.UserResponse;
import project_final.model.entity.Role;
import project_final.model.entity.RoleName;
import project_final.model.entity.User;
import project_final.model.service.impl.role.IRoleService;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserMapper implements IUserMapper{
    @Autowired
    private IRoleService roleService;
    @Override
    public User toEntity(UserRequest userRequest) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.findByRoleName(RoleName.ROLE_USER));
        return User.builder()
                .name(userRequest.getName())
                .username(userRequest.getUsername())
                .password(userRequest.getPassword())
                .email(userRequest.getEmail())
                .roles(roles)
                .status(true).build();
    }

    @Override
    public UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhone())
                .status(user.isStatus())
              .build();
    }
}
