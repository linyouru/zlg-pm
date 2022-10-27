package com.zlg.zlgpm.config.shiro;

import com.zlg.zlgpm.dao.PermissionMapper;
import com.zlg.zlgpm.dao.RoleMapper;
import com.zlg.zlgpm.dao.UserMapper;
import com.zlg.zlgpm.entity.Permission;
import com.zlg.zlgpm.entity.Role;
import com.zlg.zlgpm.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class ShiroRealm extends AuthorizingRealm {

    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;

    @Resource
    private PermissionMapper permissionMapper;

    /**
     * 用户授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        String userName = user.getUserName();

        System.out.println("用户" + userName + "获取权限-----ShiroRealm.doGetAuthorizationInfo");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        //获取用户角色集
        List<Role> roleList = roleMapper.findByUserName(userName);
        HashSet<String> roleSet = new HashSet<>();
        for (Role role : roleList) {
            roleSet.add(role.getName());
        }
        simpleAuthorizationInfo.setRoles(roleSet);
        //获取用户权限集(权限暂时不启用,现在有角色就够用了)
//        List<Permission> permissionList = permissionMapper.findByUserName(userName);
//        HashSet<String> permissionSet = new HashSet<>();
//        for (Permission p : permissionList) {
//            permissionSet.add(p.getName());
//        }
//        simpleAuthorizationInfo.setStringPermissions(permissionSet);
        return simpleAuthorizationInfo;
    }

    /**
     * 用户认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());
        String passwordMd5 = DigestUtils.md5DigestAsHex(password.getBytes());
        Optional<User> optionalUser = userMapper.selectOne(User.builder().userName(userName).build());
        if(!optionalUser.isPresent()){
            throw new UnknownAccountException("用户不存在");
        }
        User user = optionalUser.get();
        System.out.println("用户" + userName + "认证-----ShiroRealm.doGetAuthenticationInfo");

//        if (!userName.equals(user.getUserName())) {
//            throw new UnknownAccountException("用户不存在");
//        }
        if (!passwordMd5.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("密码错误");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
    }
}
