//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cn.edu.jsnu.action;

import cn.edu.jsnu.bean.User;
import cn.edu.jsnu.service.UserServiceImp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

public class UserAction {
    private ApplicationContext ctx = null;
    private UserServiceImp userService = null;

    public UserAction() {
    }

    public void init() {
        this.ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        this.userService = (UserServiceImp)this.ctx.getBean("userService");
    }

    @ResponseBody
    @RequestMapping({"/userLogin"})
    public Map<String, String> userLogin(@RequestParam("username") String username, @RequestParam("userpass") String userpass) {
        Map map = new HashMap();
        if (username.equals("") || userpass.equals("")) {
            map.put("userid", "-1");
        }

        this.init();
        User checkuser = this.userService.getValidUser(username, userpass);
        System.out.println(checkuser);
        if (checkuser == null) {
            map.put("userid", "0");
        } else {
            map.put("userid", String.valueOf(checkuser.getUser_id()));
        }

        return map;
    }

    @ResponseBody
    @RequestMapping({"/userRegister"})
    public Map<String, String> userRegister(@RequestParam("username") String username, @RequestParam("userpass") String userpass, @RequestParam("mobilenum") String mobilenum, @RequestParam("address") String address, @RequestParam("comment") String comment) {
        System.out.println(address);
        User user = new User();
        user.setUsername(username);
        user.setUserpass(userpass);
        user.setMobilenum(mobilenum);
        user.setAddress(address);
        user.setComment(comment);
        Map map = new HashMap();
        this.init();
        int result = this.userService.insertUser(user);
        map.put("success", String.valueOf(result));
        return map;
    }

    @ResponseBody
    @RequestMapping({"/getUserById"})
    public User getUserById(@RequestParam("user_id") String user_id) {
        this.init();
        return this.userService.getUserById(user_id);
    }

    @ResponseBody
    @RequestMapping({"/updateUserById"})
    public Map<String, String> updateUserById(@RequestParam("user_id") String user_id, @RequestParam("username") String username, @RequestParam("userpass") String userpass, @RequestParam("address") String address, @RequestParam("mobilenum") String mobilenum) {
        this.init();
        Map map = new HashMap();
        map.put("success", String.valueOf(this.userService.updateUserById(user_id, username, userpass, address, mobilenum)));
        return map;
    }

    @RequestMapping({"/userManager"})
    public ModelAndView userManager() {
        this.init();
        String forward = "main";
        return new ModelAndView(forward, (Map)null);
    }

    @ResponseBody
    @RequestMapping({"/getAllUsers"})
    public List<User> getAllUsers() {
        this.init();
        List<User> users = this.userService.getAllUsers();
        System.out.println(users);
        return users;
    }

    @ResponseBody
    @RequestMapping({"/resetPassword"})
    public Map<String, String> resetPassword(@RequestParam("user_id") String user_id) {
        this.init();
        Map map = new HashMap();
        map.put("success", this.userService.resetPassword(user_id));
        return map;
    }
}
