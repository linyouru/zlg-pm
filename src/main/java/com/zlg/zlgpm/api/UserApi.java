package com.zlg.zlgpm.api;

import com.zlg.zlgpm.entity.User;
import com.zlg.zlgpm.service.UserServiceImpl;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserApi {

    @Resource
    private UserServiceImpl userService;

    @RequiresPermissions("user:query")
    @GetMapping("list")
    public ResponseEntity<String> userList() {
        System.out.println("查询用户列表");
        return ResponseEntity.ok().body("user list");
    }

    @RequiresPermissions("user:add")
    @PostMapping("add")
    public ResponseEntity<String> userAdd() {
        System.out.println("添加用户");
        return ResponseEntity.ok().body("add user");
    }

    @RequiresPermissions("user:delete")
    @GetMapping("delete")
    public ResponseEntity<String> userDelete() {
        System.out.println("删除用户");
        return ResponseEntity.ok().body("delete user");
    }

}
