package rha.jwt.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import rha.jwt.model.security.User;
import rha.jwt.security.JwtUserFactory;
import rha.jwt.security.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
        		.orElseThrow(() -> new UsernameNotFoundException(
        				String.format("Ningún usuario encontrado con username '%s'.", username)));

        return JwtUserFactory.create(user);
    }
}
