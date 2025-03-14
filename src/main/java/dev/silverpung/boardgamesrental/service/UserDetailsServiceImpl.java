package dev.silverpung.boardgamesrental.service;

import dev.silverpung.boardgamesrental.repository.OverseerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final OverseerRepository overseerRepository;

    @Autowired
    public UserDetailsServiceImpl(OverseerRepository overseerRepository) {
        this.overseerRepository = overseerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return overseerRepository.getValidOverseerByUsername(username);
    }
}
