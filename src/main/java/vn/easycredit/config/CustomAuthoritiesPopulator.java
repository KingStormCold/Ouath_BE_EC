package vn.easycredit.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.stereotype.Component;

import vn.easycredit.controller.EvnProjectApplication;

@Configuration
//@PropertySource("classpath:application.properties")
//@ConfigurationProperties
//@Component
public class CustomAuthoritiesPopulator implements LdapAuthoritiesPopulator {
	
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;
	
	@Value("${spring.queries.roles-query-perm}")
	private String rolesQueryPerm;
	
	@Autowired
	private DataSource dataSource;
	
	/*@PostConstruct
	private void init() {
		System.out.println("khoa sida");
	}
*/

	@Override
    public Collection<? extends GrantedAuthority> getGrantedAuthorities(DirContextOperations userData, String username) {
		List<String> listRole = new ArrayList<>();
		try {
			listRole = getRole(username);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String role = "";
    	for (String oneRole : listRole){
    		role = role +","+ oneRole;
    	}
        Collection<GrantedAuthority> gas = new HashSet<GrantedAuthority>();
        gas.add(new SimpleGrantedAuthority(role));
        return gas;
    }
    
    public List<String> getRole(String username) throws Exception {
    	List<String> rolesQuery = new ArrayList<>();
    	Connection con = dataSource.getConnection();
    	
//    	String rolesQueryPerm = "select p.name from user_account u inner join user_role ur on u.id=ur.user_id inner join role_permission rp on ur.role_id=rp.role_id inner join permission_base p on rp.permission_id = p.id where u.user_name=?";
    	PreparedStatement ps = con.prepareStatement(rolesQueryPerm);
        ps.setString(1, username);
    	
    	ResultSet rs = ps.executeQuery();
    	
    	while(rs.next()){
    		rolesQuery.add(rs.getString(1));
		}
		
    	return rolesQuery;
	} 
}