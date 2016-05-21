package mailClient;

public class Person {
		public String username;
		public String password;
		
		Person()
		{
			username = new String("");
			password = new String("");
		}
		
		Person(String user, String pass)
		{
			username = new String(user);
			password = new String(pass);
		}
		
		Person(String user)
		{
			username = new String(user);
			password = new String("");
		}
}
