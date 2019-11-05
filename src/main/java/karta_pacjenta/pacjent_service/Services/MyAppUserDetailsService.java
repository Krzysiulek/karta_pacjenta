package karta_pacjenta.pacjent_service.Services;

import karta_pacjenta.pacjent_service.Interfaces.MyAppUsersRepository;
import karta_pacjenta.pacjent_service.Models.DAOs.MyServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyAppUserDetailsService implements UserDetailsService {
    @Autowired
    private MyAppUsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        MyServiceUser user = usersRepository.findByUserName(s);

        if (user == null){
            throw new UsernameNotFoundException(s);
        }

        return new MyAppUserPrincipal(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
