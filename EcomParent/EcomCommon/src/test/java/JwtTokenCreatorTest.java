import java.util.Objects;

import org.junit.Assert;
import org.junit.Test;

import com.ecomenum.UserRoleEnum;
import com.model.User;
import com.utilities.JsonConverter;
import com.utilities.JwtTokenCreator;

public class JwtTokenCreatorTest {
	
	@Test
	public void test() {
		User user = new User("nikhil", "12345", UserRoleEnum.USER.toString());
		String userJson = JsonConverter.objToJsonConverter(user);
		String token = JwtTokenCreator.generateToken(userJson);
		System.out.println("Token=" + token);
		Assert.assertTrue(Objects.nonNull(JwtTokenCreator.validateToken(token)));
	}
}
