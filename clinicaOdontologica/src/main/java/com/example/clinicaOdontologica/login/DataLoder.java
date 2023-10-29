/*package com.example.clinicaOdontologica.login;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


public class DataLoder implements ApplicationRunner {

    private UserRepository userRepository;

    public DataLoder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("password");
        String password2 = passwordEncoder.encode("password2");

        Usuario miusuario1 = new Usuario("Pepe","PepeUsername","pepe@gmail.com",password,UsuarioRoles.ROLE_USER);
        Usuario miusuario2 = new Usuario("MariaAdmin","MariaAdminUsername","maria@gmail.com",password2,UsuarioRoles.ROLE_ADMIN);

        userRepository.save(miusuario1);
        userRepository.save(miusuario2);
    }
}

 */
