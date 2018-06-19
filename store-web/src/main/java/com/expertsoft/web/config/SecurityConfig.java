package com.expertsoft.web.config;

import com.expertsoft.web.security.UserDetailsServiceImpl;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.acls.AclPermissionEvaluator;
import org.springframework.security.acls.domain.AclAuthorizationStrategy;
import org.springframework.security.acls.domain.AclAuthorizationStrategyImpl;
import org.springframework.security.acls.domain.AuditLogger;
import org.springframework.security.acls.domain.ConsoleAuditLogger;
import org.springframework.security.acls.domain.DefaultPermissionGrantingStrategy;
import org.springframework.security.acls.domain.EhCacheBasedAclCache;
import org.springframework.security.acls.jdbc.BasicLookupStrategy;
import org.springframework.security.acls.jdbc.JdbcMutableAclService;
import org.springframework.security.acls.jdbc.LookupStrategy;
import org.springframework.security.acls.model.AclCache;
import org.springframework.security.acls.model.AclService;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.PermissionGrantingStrategy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

import static com.expertsoft.web.controller.LoginPageController.LOGIN_URL;
import static com.expertsoft.web.util.Constants.ADMIN;
import static com.expertsoft.web.util.Constants.ROLE_ADMIN;
import static com.expertsoft.web.util.Constants.ROLE_USER;
import static java.lang.String.format;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${rememberMe.key}")
    private String rememberMeKey;

    @Value("${acl.classIdentityQuery}")
    private String classIdentityQuery;

    @Value("${acl.sidIdentityQuery}")
    private String sidIdentityQuery;

    @Value("${acl.objectIdentityPrimaryKeyQuery}")
    private String objectIdentityPrimaryKeyQuery;

    @Value("${acl.findChildrenQuery}")
    private String findChildrenQuery;

    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(final UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/admin/**").hasRole(ADMIN)
                .antMatchers(LOGIN_URL).anonymous()
            .and()
                .formLogin()
                .loginPage(LOGIN_URL)
            .and()
                .logout().logoutSuccessUrl("/")
            .and()
                .rememberMe()
                .key(rememberMeKey);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public RoleHierarchyVoter roleHierarchyVoter() {
        return new RoleHierarchyVoter(roleHierarchy());
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy(format("%s > %s", ROLE_ADMIN, ROLE_USER));
        return roleHierarchy;
    }

    @Bean
    public AuditLogger auditLogger() {
        return new ConsoleAuditLogger();
    }

    @Bean
    public PermissionGrantingStrategy permissionGrantingStrategy() {
        return new DefaultPermissionGrantingStrategy(auditLogger());
    }

    @Bean
    public AclAuthorizationStrategy aclAuthorizationStrategy() {
        return new AclAuthorizationStrategyImpl(new SimpleGrantedAuthority(ADMIN));
    }

    @Bean
    public AclCache aclCache(CacheManager cacheManager) {
        Ehcache cache = cacheManager.getCache("aclCache");
        return new EhCacheBasedAclCache(cache, permissionGrantingStrategy(), aclAuthorizationStrategy());
    }

    @Bean
    public LookupStrategy lookupStrategy(DataSource dataSource, AclCache aclCache) {
        return new BasicLookupStrategy(dataSource, aclCache, aclAuthorizationStrategy(), auditLogger());
    }

    @Bean
    public MutableAclService aclService(DataSource dataSource, LookupStrategy lookupStrategy, AclCache aclCache) {
        JdbcMutableAclService aclService = new JdbcMutableAclService(dataSource, lookupStrategy, aclCache);
        aclService.setClassIdentityQuery(classIdentityQuery);
        aclService.setSidIdentityQuery(sidIdentityQuery);
        aclService.setObjectIdentityPrimaryKeyQuery(objectIdentityPrimaryKeyQuery);
        aclService.setFindChildrenQuery(findChildrenQuery);
        return aclService;
    }

    @Bean
    public PermissionEvaluator permissionEvaluator(AclService aclService) {
        return new AclPermissionEvaluator(aclService);
    }
}

@Configuration
@Profile("test")
class SecurityConfigTest {

    @Bean
    public MutableAclService aclService(DataSource dataSource, LookupStrategy lookupStrategy, AclCache aclCache) {
        return new JdbcMutableAclService(dataSource, lookupStrategy, aclCache);
    }
}
