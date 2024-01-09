//package edu.pnu.connect;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import jakarta.annotation.PostConstruct;
//
//@Component
//public class JDBConnectSpring {
//	
////	private final JdbcTemplate jdbcTemp;
//	
////	@Autowired
////	public JDBConnect(JdbcTemplate jdbcTemp) {
////		this.jdbcTemp = jdbcTemp;
////	}
//	private Connection con;
//	@Value("${spring.datasource.url}")
//    private String url;
//
//    @Value("${spring.datasource.username}")
//    private String id;
//
//    @Value("${spring.datasource.password}")
//    private String pwd;
//
//    @PostConstruct
//    public void initialize() {
//        try {
//            // Assuming MySQL driver is included in the classpath, Spring Boot auto-configures it.
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            con = DriverManager.getConnection(url, id, pwd);
//
//            System.out.println("DB 연결 성공 (Spring Boot)");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    
//    public JDBConnectSpring() {
//		try {
//			//HardCoding
//			String driver = "com.mysql.cj.jdbc.Driver";
//			String url = "jdbc:mysql://localhost:3306/musthave";
//			String username ="scott";
//			String password ="tiger";
//			
//			Class.forName(driver);
//			con=DriverManager.getConnection(url,username,password);
//			
//			System.out.println("DB 연결 성공 (기본 생성자");
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//}
