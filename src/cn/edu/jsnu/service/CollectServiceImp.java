//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cn.edu.jsnu.service;

import cn.edu.jsnu.bean.Collection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class CollectServiceImp {
    private JdbcTemplate jdbcTemplate;

    public CollectServiceImp() {
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return this.jdbcTemplate;
    }

    public List<Collection> getAllUserCollection(int user_id, int flag) {
        new ArrayList();
        List list;
        String sql;
        if (flag == 0) {
            sql = "select u.user_id,u.username,s.*,uc.collect_id,uc.flag from user_collect uc,user u,shop s where u.user_id=uc.user_id and s.shop_id=uc.shop_id and u.user_id=? and flag=?";
            list = this.jdbcTemplate.query(sql, new Object[]{user_id, flag}, new DrinkMapper((DrinkMapper) null));
        } else {
            sql = "select u.user_id,u.username,f.*,uc.collect_id,uc.flag from user_collect uc,user u,drink f where u.user_id=uc.user_id and f.drink_id=uc.drink_id and u.user_id=? and flag=?";
            list = this.jdbcTemplate.query(sql, new Object[]{user_id, flag}, new DrinkMapper((DrinkMapper) null));
        }

        return list;
    }

    public int changeDrinkCollection(int user_id, int drink_id) {
        System.out.println(user_id);
        System.out.println(drink_id);
        int insert = this.jdbcTemplate.update("insert into user_collect select null,?,null,?,now(),1 from dual where not exists(select * from user_collect where user_id=? and drink_id=?)", new Object[]{user_id, drink_id, user_id, drink_id});
        return insert == 0 ? this.jdbcTemplate.update("delete from user_collect where user_id=? and drink_id=?", new Object[]{user_id, drink_id}) : insert;
    }

    public int changeShopCollection(int user_id, int shop_id) {
        int insert = this.jdbcTemplate.update("insert into user_collect select null,?,?,null,now(),0 from dual where not exists(select * from user_collect where user_id=? and shop_id=?)", new Object[]{user_id, shop_id, user_id, shop_id});
        return insert == 0 ? this.jdbcTemplate.update("delete from user_collect where user_id=? and shop_id=?", new Object[]{user_id, shop_id}) : insert;
    }

    public int isCollected(int user_id, int shop_drink_id, int flag) {
        return flag == 0 ? this.jdbcTemplate.queryForInt("select count(*) from user_collect where user_id=? and shop_id=? and flag=?", new Object[]{user_id, shop_drink_id, flag}) : this.jdbcTemplate.queryForInt("select count(*) from user_collect where user_id=? and drink_id=? and flag=?", new Object[]{user_id, shop_drink_id, flag});
    }

    private static final class DrinkMapper implements RowMapper {
        private DrinkMapper(DrinkMapper drinkMapper) {
        }

        public Collection mapRow(ResultSet rs, int rowNum) throws SQLException {
            Collection collection = new Collection();
            collection.setCollect_id(rs.getInt("collect_id"));
            int flag = rs.getInt("flag");
            if (flag == 1) {
                collection.setDrinkname(rs.getString("drinkname"));
                collection.setDrink_id(rs.getInt("drink_id"));
                collection.setShop_id(rs.getInt("shop_id"));
                collection.setPic(rs.getString("pic"));
                collection.setPrice(rs.getDouble("price"));
            } else {
                collection.setShopname(rs.getString("shopname"));
                collection.setShop_id(rs.getInt("shop_id"));
                collection.setPic(rs.getString("pic"));
                collection.setAddress(rs.getString("address"));
            }

            collection.setUser_id(rs.getInt("user_id"));
            collection.setUsername(rs.getString("username"));
            return collection;
        }
    }

}
