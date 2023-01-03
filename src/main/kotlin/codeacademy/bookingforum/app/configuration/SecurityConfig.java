package codeacademy.bookingforum.app.configuration;

import codeacademy.bookingforum.app.ecxeption.global.AuthEntryPointExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    protected UserDetailsServiceImpl userDetailsService;
    @Autowired
    protected AuthEntryPointExceptionHandler unauthorizedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http.cors().and().csrf().disable()
////                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
////                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
////                .authorizeHttpRequests().requestMatchers("/api/auth/**").permitAll()
//////      .requestMatchers("/api/test/**").permitAll()
////                .anyRequest().authenticated();
//
//        http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                .authorizeHttpRequests()
//                //.requestMatchers("/", "**", "/**").permitAll()
//                .requestMatchers("/api/user/get/**").hasAnyRole("ADMIN")
//                .requestMatchers("/api/user/register/user","/api/user/login").permitAll()
//                .anyRequest().authenticated();
//
//        return http.build();
//    }


//@Bean
//public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
//    httpSecurity
//            .authorizeHttpRequests((requests) -> requests
//                    .requestMatchers( new AntPathRequestMatcher("/api/user/login")).permitAll()
//                    .requestMatchers( new AntPathRequestMatcher("/api/user/register/user")).permitAll()
//                    .requestMatchers( new AntPathRequestMatcher("/api/user/get/**")).hasAnyRole("ADMIN")
//                    .anyRequest().authenticated())
//            .httpBasic();
//    return httpSecurity.build();
//}

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(HttpMethod.GET, "/api/user/get/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/user/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/user/register/user", "/api/users/{username}", "/api/users/logout", "/api/costumers", "/api/storages").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/costumers", "/api/storages").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/costumers/{id}", "/api/storages/{id}").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/users/{id}", "/api/storages/{id}", "/api/costumers/{id}").hasAnyRole("ADMIN")
                        .anyRequest().denyAll())
                .httpBasic();
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }
}
