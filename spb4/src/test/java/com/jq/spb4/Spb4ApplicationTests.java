package com.jq.spb4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Spb4ApplicationTests {

	@Autowired
	private DataSource dataSource;


	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	public void contextLoads() {

		System.out.println(dataSource);
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(connection);

		/*List<Object> query = jdbcTemplate.query("select * from user ", new RowMapper<Object>() {
			@Override
			public Object mapRow(ResultSet resultSet, int i) throws SQLException {
				Map<String,Object> map = new HashMap<>();
				map.put("id",resultSet.getInt("id"));
				map.put("user_name",resultSet.getString("user_name"));
				map.put("age",resultSet.getInt("age"));
				return map;
			}
		});
		System.out.println(query);*/
	}

}
