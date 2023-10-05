package project_final.security.user_principle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project_final.model.entity.User;
import project_final.model.service.impl.user.IUserService;


@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private IUserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUserName(username).orElseThrow(()-> new UsernameNotFoundException("Username Not Found"));
        return UserPrinciple.build(user);
    }
    public  User getUserFromAuthentication() {
        UserPrinciple userPrinciple= (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.findById(userPrinciple.getId());
    }
}
