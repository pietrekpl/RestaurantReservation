package example.projects.restaurant_reservation.service;


import example.projects.restaurant_reservation.model.User;
import example.projects.restaurant_reservation.repos.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImplementation implements UserDetailsService {


    private UserRepository userRepository;


    public UserDetailServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        final User user = userRepository.findUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(
                user.getUsername()).password(user.getPasswordHash()).roles("USER").build();

        return userDetails;


    }
}
