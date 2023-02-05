package domain;

import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.sql.*;

public class UserDao {

    private SimpleDriverDataSource dataSource;
    private Connection c;
    private User user;

    public void setDataSource(SimpleDriverDataSource dataSource){
        this.dataSource = dataSource;
    }
    public void add(User user) throws SQLException {
        this.c = dataSource.getConnection();

        PreparedStatement ps = this.c.prepareStatement(
                "insert into users(id, name, password) values(?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        this.c.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        this.c = dataSource.getConnection();
        PreparedStatement ps = this.c
                .prepareStatement("select * from users where id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        this.user = new User();
        this.user.setId(rs.getString("id"));
        this.user.setName(rs.getString("name"));
        this.user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return this.user;
    }

}
