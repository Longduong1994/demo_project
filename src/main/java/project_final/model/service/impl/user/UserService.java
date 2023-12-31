package project_final.model.service.impl.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project_final.exception.RegisterException;
import project_final.model.dto.request.UserRequest;
import project_final.model.dto.response.UserResponse;
import project_final.model.entity.Role;
import project_final.model.entity.RoleName;
import project_final.model.entity.User;
import project_final.model.repository.IUserRepository;
import project_final.model.service.impl.role.IRoleService;
import project_final.model.service.mapper.user.IUserMapper;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements IUserService{
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IUserMapper userMapper;

    @Override
    public Optional<User> findByUserName(String username) {
        return Optional.empty();
    }

    @Override
    public User save(UserRequest userRequest) throws RegisterException {
        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw new RegisterException("User is exits");
        }
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new RegisterException("Email is exits");
        }
        return userMapper.toEntity(userRequest);
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public UserResponse lock(Long id) {
        return null;
    }

    @Override
    public List<UserResponse> findAll() {
        return null;
    }
}
