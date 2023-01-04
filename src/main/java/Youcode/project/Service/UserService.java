package Youcode.project.Service;


import Youcode.project.Model.Role;
import Youcode.project.Model.User;
import Youcode.project.Repository.RoleRepository;
import Youcode.project.Repository.UserRepository;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService implements UserDetailsService {



   private final UserRepository userRepository;

private  final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       User user=userRepository.findUserByEmail(email);
        Collection<SimpleGrantedAuthority> authorities=new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getNameRole()));

    return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
    }
   public User saveUser(User user){
        user.setPassword(NoOpPasswordEncoder.getInstance().encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Role saveRole(Role role)
    {
        return roleRepository.save(role);
    }




   public User getUser(String email)
    {
        return userRepository.findUserByEmail(email);

    }
   public List<User>  getUsers()
    {
        return userRepository.findAll();
    }


}
