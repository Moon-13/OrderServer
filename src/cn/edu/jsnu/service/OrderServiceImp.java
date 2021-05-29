//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cn.edu.jsnu.service;

import cn.edu.jsnu.bean.Order;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class OrderServiceImp {
    private JdbcTemplate jdbcTemplate;

    public OrderServiceImp() {
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return this.jdbcTemplate;
    }

    public int insertOrder(Order order) {
        String sql = "insert into user_order values(null,?,?,?,?,now(),?,null,null)";
        return this.jdbcTemplate.update(sql, new Object[]{order.getUser_id(), order.getDrink_id(), order.getNum(), order.getSum(), order.getSuggesttime()});
    }

    public int insertComment(int order_id, String content) {
        String sql = "update user_order set content=?,comment_time=now() where order_id=?";
        return this.jdbcTemplate.update(sql, new Object[]{content, order_id});
    }

    public List<Order> getAllUserOrder(String user_id) {
        String sql = "select d.*,u.*,o.order_id,o.sum,o.num,o.ordertime,o.suggesttime,o.content,o.comment_time,s.shopname,s.address as shopaddress from drink d,user u,user_order o,shop s where d.drink_id=o.drink_id and u.user_id=o.user_id and u.user_id=? and d.shop_id=s.shop_id";
        return this.jdbcTemplate.query(sql, new Object[]{user_id}, new OrderMapper((OrderMapper)null));
    }

    public List<Order> getAllUserComment(String user_id) {
        String sql = "select f.*,u.*,o.order_id,o.sum,o.num,o.ordertime,o.suggesttime,o.content,o.comment_time,s.shopname,s.address as shopaddress from drink f,user u,user_order o,shop s where f.drink_id=o.drink_id and u.user_id=o.user_id and u.user_id=? and f.shop_id=s.shop_id and o.comment_time is not null";
        System.out.println(sql);
        return this.jdbcTemplate.query(sql, new Object[]{user_id}, new OrderMapper((OrderMapper)null));
    }

    public int updateComment(int order_id, String content) {
        content = content.substring(1, content.length() - 1);
        String sql = "update user_order set content=?,comment_time=now() where order_id=?";
        return this.jdbcTemplate.update(sql, new Object[]{content, order_id});
    }

    public int deleteComment(int order_id) {
        String sql = "update user_order set content=null,comment_time=null where order_id=?";
        return this.jdbcTemplate.update(sql, new Object[]{order_id});
    }

    public List<Order> getAllDrinkOrder(String drink_id) {
        String sql = "select f.*,u.*,o.order_id,o.sum,o.num,o.ordertime,o.suggesttime,o.content,o.comment_time,s.shopname,s.address as shopaddress from drink f,user u,user_order o,shop s where f.drink_id=o.drink_id and u.user_id=o.user_id  and f.shop_id=s.shop_id and o.drink_id=? and o.comment_time is not null";
        return this.jdbcTemplate.query(sql, new Object[]{drink_id}, new OrderMapper((OrderMapper)null));
    }

    public List<Order> getAllOrder() {
        String sql = "select f.*,u.*,o.order_id,o.sum,o.num,o.ordertime,o.suggesttime,o.content,o.comment_time,s.shopname,s.address as shopaddress from drink f,user u,user_order o,shop s where f.drink_id=o.drink_id and u.user_id=o.user_id and f.shop_id=s.shop_id";
        return this.jdbcTemplate.query(sql, new OrderMapper((OrderMapper)null));
    }

    private static final class OrderMapper implements RowMapper {
        private OrderMapper(OrderMapper orderMapper) {
        }

        public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
            Order order = new Order();
            order.setOrder_id(rs.getInt("order_id"));
            order.setDrink_id(rs.getInt("drink_id"));
            order.setUser_id(rs.getInt("user_id"));
            order.setNum(rs.getInt("num"));
            order.setSum(rs.getDouble("sum"));
            order.setSuggesttime(rs.getString("suggesttime"));
            order.setOrdertime(rs.getString("ordertime"));
            order.setDrinkname(rs.getString("drinkname"));
            order.setUsername(rs.getString("username"));
            order.setShopaddress(rs.getString("shopaddress"));
            order.setShopname(rs.getString("shopname"));
            order.setPrice(rs.getDouble("price"));
            order.setContent(rs.getString("content"));
            order.setComment_time(rs.getString("comment_time"));
            return order;
        }
    }
}
