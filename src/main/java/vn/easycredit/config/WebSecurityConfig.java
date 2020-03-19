package vn.easycredit.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
//	@Value("${spring.queries.users-query}")
//	private String usersQuery;
//	
//	@Value("${spring.queries.roles-query}")
//	private String rolesQuery;
//	
//	@Autowired
//	private DataSource dataSource;
	
	
	
	
//	 @Autowired
//	 @Qualifier("ldaploginService")
//	 LdapAuthoritiesPopulator ldapAuthoritiesPopulator;

	/*@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
			.usersByUsernameQuery(usersQuery)
			.authoritiesByUsernameQuery(rolesQuery)
			.dataSource(dataSource).passwordEncoder(new BCryptPasswordEncoder());
	}*/
	
	
//	@Autowired
//	public void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth
//			.ldapAuthentication()
//				.userSearchBase("ou=ECF_Users") //don't add the base
//		    	.userSearchFilter("(&(sAMAccountName={0})(objectclass=user))")
//		    	.groupSearchBase("ou=ECF_Users") //don't add the base
//		    	.groupSearchFilter("member=(sAMAccountName={0},DC=int,DC=easycredit,DC=vn)")
//		    	.contextSource(getContextSource())
//	    	;
//		.and()
//		auth
//		.jdbcAuthentication()
//				.usersByUsernameQuery(usersQuery)
//				.authoritiesByUsernameQuery(rolesQuery)
//				.dataSource(dataSource).passwordEncoder(new BCryptPasswordEncoder())
//	    	;
		
//		auth
//		.ldapAuthentication()
//			.userSearchBase("ou=ECF_Users") //don't add the base
//	    	.userSearchFilter("(&(sAMAccountName={0})(objectclass=user))")
//	    	.groupSearchBase("ou=ECF_Users") //don't add the base
//	    	.groupSearchFilter("member=(sAMAccountName={0},DC=int,DC=easycredit,DC=vn)")
//	    	.contextSource(getContextSource())
//	    	.ldapAuthoritiesPopulator(ldapAuthoritiesPopulator)
//	    	;
//	}
	
	@Autowired
	CustomAuthoritiesPopulator custom;
	
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth        
            .ldapAuthentication()
//                .ldapAuthoritiesPopulator(new CustomAuthoritiesPopulator())
                .ldapAuthoritiesPopulator(custom)
                .userSearchFilter("(&(sAMAccountName={0})(objectclass=user))")           
            .contextSource(getContextSource());    
    }
	
	@Bean
    public LdapContextSource getContextSource() {
    	  LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl("ldap://***");
        contextSource.setBase("DC=int,DC=easycredit,DC=vn");
//	        contextSource.setUserDn("OU=ECF_Users,DC=int,DC=easycredit,DC=vn");
        contextSource.setUserDn("***");
        contextSource.setPassword("***");
        contextSource.afterPropertiesSet(); //needed otherwise you will have a NullPointerException in spring

        return contextSource;
    }

	protected void configure(HttpSecurity http) throws Exception {
		((HttpSecurity) http.cors().and()).csrf().disable();
	}
}