package ma.achraf.hopitalmvc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private PasswordEncoder passwordEncoder ;


@Bean // au demmarage spring va appele cette metode la (securityFiler..) qui utilise la notation bean ca veut dire c est une methode qui va exucite au demarage


   public InMemoryUserDetailsManager inMemoryUserDetailsManager (){
       return new InMemoryUserDetailsManager(
               User.withUsername("user1").password(passwordEncoder.encode("1234")).roles("USER").build(), // noop ca veut dire no password en coder ce mote de passe la pour verifier ou compare ce mot de passe avec lle mot de passe saisie par l utilisateur je demande a sprinf security de ne pas utiliser un password encoder
               User.withUsername("user2").password(passwordEncoder.encode("1234")).roles("USER").build(),
               User.withUsername("Ashraf").password(passwordEncoder.encode("1234")).roles("ASHRAF").build(),
               User.withUsername("admin").password(passwordEncoder.encode("1234")).roles("USER","ADMIN").build()
       );

    } //ca veut dire que je vais preciser que en memoire les utilisateur qui ont le droit d acceder a la application apres on peut utilise GDBC authentication c est pour dans le cas des utilsateurs sont stocker sur base de donne avec un acces JDBC
    @Bean


    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws  Exception{
        httpSecurity.formLogin();
        httpSecurity.authorizeHttpRequests().anyRequest().authenticated(); //ca veut dire que vous etes en train de dire a spring securite je voudrais que toutes les requetes necessite une authentification
        return  httpSecurity.build ();


    }
}
