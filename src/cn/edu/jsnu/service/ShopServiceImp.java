//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cn.edu.jsnu.service;

import cn.edu.jsnu.bean.Shop;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.RequestParam;

public class ShopServiceImp {
    private JdbcTemplate jdbcTemplate;

    public ShopServiceImp() {
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return this.jdbcTemplate;
    }

    public List<Shop> getAllShops() {
        String sql = "select * from shop";
        return this.jdbcTemplate.query(sql, new ShopMapper((ShopMapper)null));
    }

    public Shop getShopById(String shop_id) {
        String sql = "select * from shop where shop_id=" + shop_id;
        List<Shop> shops = this.jdbcTemplate.query(sql, new ShopMapper((ShopMapper)null));
        return shops != null && shops.size() > 0 ? (Shop)shops.get(0) : null;
    }

    public int insertShop(Shop shop) {
        String sql = "insert into shop values(null,?,?,?,?,?,?,?)";
        return this.jdbcTemplate.update(sql, new Object[]{shop.getShopname(), shop.getAddress(), shop.getPhonenum(), shop.getIntro(), shop.getPic(), shop.getComment(), shop.getLevel()});
    }

    public int deleteShop(String shop_id) {
        return this.jdbcTemplate.update("delete from shop where shop_id=" + shop_id);
    }

    public int updateShop(Shop shop) {
        System.out.println(shop);
        return this.jdbcTemplate.update("update shop set shopname=?,phonenum=?,address=?,intro=?,pic=?,level=? where shop_id=?", new Object[]{shop.getShopname(), shop.getPhonenum(), shop.getAddress(), shop.getIntro(), shop.getPic(), shop.getLevel(), shop.getShop_id()});
    }

    private static final class ShopMapper implements RowMapper {
        private ShopMapper(ShopMapper shopMapper) {
        }

        public Shop mapRow(ResultSet rs, int rowNum) throws SQLException {
            Shop shop = new Shop();
            shop.setShop_id(rs.getInt("shop_id"));
            shop.setLevel(rs.getInt("level"));
            shop.setShopname(rs.getString("shopname"));
            shop.setPhonenum(rs.getString("phonenum"));
            shop.setAddress(rs.getString("address"));
            shop.setIntro(rs.getString("intro"));
            shop.setPic(rs.getString("pic"));
            shop.setComment(rs.getString("comment"));
            return shop;
        }
    }
}
