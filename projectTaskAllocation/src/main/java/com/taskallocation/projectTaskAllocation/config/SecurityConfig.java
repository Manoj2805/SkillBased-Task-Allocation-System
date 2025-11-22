package com.taskallocation.projectTaskAllocation.config;

public class SecurityConfig {

//  @Bean
//  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//      http
//          .csrf(csrf -> csrf
//              .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//              .ignoringRequestMatchers("/api/auth/login", "/api/auth/register")
//          )
//          .authorizeHttpRequests(auth -> auth
//              .requestMatchers("/api/auth/csrf", "/api/auth/login", "/api/auth/register").permitAll()
//              .anyRequest().authenticated()
//          )
//          .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
//          .cors(Customizer.withDefaults())
//          .formLogin(form -> form.disable())
//          .httpBasic(Customizer.withDefaults()); // enable Basic Auth here
//
//      return http.build();
//  }
}