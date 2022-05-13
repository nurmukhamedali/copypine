package kz.pine.config;

import kz.pine.domain.Cart;
import kz.pine.domain.User;
import kz.pine.repositories.CartRepository;
import kz.pine.repositories.CustomerRepository;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/login**", "/js/**", "/error**").permitAll()
                .anyRequest().authenticated()
                .and().logout().logoutSuccessUrl("/").permitAll()
                .and()
                .csrf().disable();
    }

    @Bean
    public PrincipalExtractor principalExtractor(CustomerRepository customerRepository, CartRepository cartRepository){
        return map -> {
            String id = (String) map.get("sub");
            User user = customerRepository.findById(id).orElseGet(() -> {
                User nuser = new User();

                nuser.setId(id);
                nuser.setUsername((String) map.get("email"));
                nuser.setCart(cartRepository.save(new Cart()));
                return nuser;
            });
            return customerRepository.save(user);
        };
    }
}
