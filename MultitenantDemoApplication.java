import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.utility.AES;

@SpringBootApplication
@ComponentScan("com")
@EnableAutoConfiguration(exclude={ DataSourceAutoConfiguration.class, 
        DataSourceTransactionManagerAutoConfiguration.class})
@EnableJpaRepositories(basePackages={"com.domain"})
@EntityScan("com")
@EnableTransactionManagement
public class MultitenantDemoApplication {

	public static void main(String[] args) {
		
		JSONObject jUser = new JSONObject();
		try {
			jUser.put("id", 1);
			jUser.put("username", "lakhyanivishal");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String secret = AES.encrypt(jUser.toString(), "ssshhhhhhhhhhh!!!!");
		System.out.println("Encrypt key ="+secret);
		
		SpringApplication.run(MultitenantDemoApplication.class, args);
	}
}
