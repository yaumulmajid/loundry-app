package com.codeapps.loundry.auth.service;

import com.codeapps.loundry.auth.model.AuthRequest;
import com.codeapps.loundry.auth.model.AuthResponse;
import com.codeapps.loundry.module.user.entity.User;
import com.codeapps.loundry.module.user.model.RoleDetailDto;
import com.codeapps.loundry.module.user.model.UserDetailDto;
import com.codeapps.loundry.module.user.repository.UserRepository;
import com.codeapps.loundry.utill.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    getAuthorities(user)
            );
        } else {
            throw new UsernameNotFoundException("Username not found: " + username);
        }
    }

    public AuthResponse createToken(AuthRequest authRequest) throws Exception{
        String username = authRequest.getUsername();
        String password = authRequest.getPassword();
        authenticate(username,password);

        final UserDetails userDetails = loadUserByUsername(username);

        String newGenerateToken = jwtUtil.generateToken(userDetails);

        User user = userRepository.findByUsername(username);
        UserDetailDto userDetail = new UserDetailDto();
        userDetail.setUsername(user.getUsername());
        userDetail.setEmail(user.getEmail());
        userDetail.setPhone(user.getPhone());
        userDetail.setPassword(user.getPassword());
        List<RoleDetailDto> roleDtos = user.getRole().stream()
                .map(role -> new RoleDetailDto(role.getName(), role.getDescription()))
                .collect(Collectors.toList());
        userDetail.setRole(roleDtos);

        return new AuthResponse(userDetail,newGenerateToken);
    }

    private Set getAuthorities(User user){
        Set authorities = new HashSet();

        user.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return authorities;
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e){
            throw new Exception("User is Disabled");
        } catch (BadCredentialsException e){
            throw new Exception("Bad Credentials");
        }
    }
}
