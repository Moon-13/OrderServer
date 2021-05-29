package cn.edu.jsnu.action;

import cn.edu.jsnu.service.UserServiceImp;
import java.util.HashMap;
import java.util.Map;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public class LoginAction {
    private String successView;
    private String failView;
    private ApplicationContext ctx = null;
    private UserServiceImp adminService = null;

    public LoginAction() {
    }

    public void init() {
        this.ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        this.adminService = (UserServiceImp)this.ctx.getBean("userService");
    }

    public String getSuccessView() {
        return this.successView;
    }

    public void setSuccessView(String successView) {
        this.successView = successView;
    }

    public String getFailView() {
        return this.failView;
    }

    public void setFailView(String failView) {
        this.failView = failView;
    }

    @RequestMapping({"/adminlogin"})
    public ModelAndView getAdminLogin(@RequestParam("adminid") String adminid,@RequestParam("adminpass") String adminpass) {
        this.init();
        Map<String, Object> model = new HashMap();
        boolean result = false;
        if ("admin".equals(adminid) && "11".equals(adminpass)) {
            return new ModelAndView(this.getSuccessView(), (Map)null);
        } else {
            model.put("error", "用戶名和密码不正确");
            return new ModelAndView(this.getFailView(), model);
        }
    }
}
