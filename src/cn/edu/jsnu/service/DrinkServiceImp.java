//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cn.edu.jsnu.service;

import cn.edu.jsnu.bean.Drink;
import cn.edu.jsnu.bean.DrinkType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class DrinkServiceImp {
    private JdbcTemplate jdbcTemplate;

    public DrinkServiceImp() {
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return this.jdbcTemplate;
    }

    public List<Drink> getDrinksByShop(String shop_id) {
        String sql = "select * from drink where shop_id='" + shop_id + "'";
        return this.jdbcTemplate.query(sql, new DrinkServiceImp.DrinkMapper((DrinkServiceImp.DrinkMapper)null));
    }

    public List<Drink> getDrinksBySearch(String search) {
        String sql = "select * from drink where drinkname like '%" + search + "%' " + "or drink.type_id in(select t.type_id from drinktype t where t.typename like '%" + search + "%')";
        return this.jdbcTemplate.query(sql, new DrinkServiceImp.DrinkMapper((DrinkServiceImp.DrinkMapper)null));
    }

    public Drink getDrinkById(String drink_id) {
        String sql = "select * from drink where drink_id=" + drink_id;
        List<Drink> lists = this.jdbcTemplate.query(sql, new DrinkServiceImp.DrinkMapper((DrinkServiceImp.DrinkMapper)null));
        return lists.size() > 0 ? (Drink)lists.get(0) : null;
    }

    public List<DrinkType> getDrinkType() {
        String sql = "select * from drinktype";
        return this.jdbcTemplate.query(sql, new DrinkServiceImp.DrinkTypeMapper((DrinkServiceImp.DrinkTypeMapper)null));
    }

    public int insertDrink(String shop_id, String drinkname, String type_id, String pic, double price, String intro, String recommand) {
        String sql = "insert into drink values(null,?,?,?,?,?,?,?)";
        return this.jdbcTemplate.update(sql, new Object[]{drinkname, price, intro, pic, shop_id, type_id, recommand});
    }

    public int updateDrink(Drink drink) {
        String sql = "update drink set drinkname=?,price=?,intro=?,pic=?,type_id=?,recommand=? where drink_id=?";
        return this.jdbcTemplate.update(sql, new Object[]{drink.getDrinkname(), drink.getPrice(), drink.getIntro(), drink.getPic(), drink.getType_id(), drink.getRecommand(), drink.getDrink_id()});
    }

    public int deleteDrink(String drink_id) {
        String sql = "delete from drink where drink_id=?";
        return this.jdbcTemplate.update(sql, new Object[]{drink_id});
    }

    private static final class DrinkMapper implements RowMapper {
        private DrinkMapper(DrinkMapper drinkMapper) {
        }

        public Drink mapRow(ResultSet rs, int rowNum) throws SQLException {
            Drink drink = new Drink();
            drink.setShop_id(rs.getInt("shop_id"));
            drink.setDrink_id(rs.getInt("drink_id"));
            drink.setType_id(rs.getInt("type_id"));
            drink.setDrinkname(rs.getString("drinkname"));
            drink.setIntro(rs.getString("intro"));
            drink.setPrice(rs.getDouble("price"));
            drink.setIntro(rs.getString("intro"));
            drink.setPic(rs.getString("pic"));
            drink.setRecommand(rs.getInt("recommand"));
            return drink;
        }
    }

    private static final class DrinkTypeMapper implements RowMapper {
        private DrinkTypeMapper(DrinkTypeMapper drinkTypeMapper) {
        }

        public DrinkType mapRow(ResultSet rs, int rowNum) throws SQLException {
            DrinkType drink = new DrinkType();
            drink.setType_id(rs.getInt("type_id"));
            drink.setTypename(rs.getString("typename"));
            return drink;
        }
    }
}
