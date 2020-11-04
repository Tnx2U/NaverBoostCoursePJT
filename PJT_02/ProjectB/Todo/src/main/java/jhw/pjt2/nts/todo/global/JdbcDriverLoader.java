package jhw.pjt2.nts.todo.global;

public class JdbcDriverLoader {
	public static void getStaticJdbcDriver() throws ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
	}
}
