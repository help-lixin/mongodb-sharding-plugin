package help.lixin.mongodb.controller;

import help.lixin.mongodb.ctx.TenantContext;
import help.lixin.mongodb.entity.User;
import help.lixin.mongodb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class HelloController {

    @Autowired
    private IUserService userService;

    @GetMapping("/all")
    public List<User> findAll() {
        TenantContext.setTenantCode("00007");
        return userService.findAll();
    }

    @GetMapping("/update")
    public String update() throws Exception {
        TenantContext.setTenantCode("00007");
        User user = new User();
        user.setId("a4433f56056d48a2a8ba7d41c2af69bb");
        user.setName("李四");
        user.setAge(100);
        user.setGender("男");
        userService.update(user);
        return "SUCCESS";
    }

    @GetMapping("/save")
    public String save() {
        TenantContext.setTenantCode("00007");
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(UUID.randomUUID().toString().replace("-", ""));
            user.setName("张三_" + i);
            user.setAge(25 + i);
            user.setGender("男");
            userService.save(user);
        }
        return "SUCCESS";
    }
}
