package rutke.julio.gptw.auth.endpoint.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import rutke.julio.gptw.core.model.ApplicationUser;

import java.util.List;
import java.util.Map;

@Repository
public class DAOService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void updateFAProperties(String userid, String code){
        jdbcTemplate.update(
                "update application_user set authemail = ? where id = ?"
                ,new Object[]{code, userid});
    }

    public boolean getFACode(String username, String code){
        String sql = "SELECT * FROM application_user WHERE username = ? and authemail = ?";
        jdbcTemplate = new JdbcTemplate();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        return rows.size() > 0;
    }
}
