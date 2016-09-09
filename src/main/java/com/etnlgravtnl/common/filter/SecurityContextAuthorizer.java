package com.etnlgravtnl.common.filter;

import com.google.gson.Gson;

import javax.security.auth.Subject;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import java.security.Principal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Name:
 * @Author: junz（作者）
 * @Version: V1.00 （版本号）
 * @Create Date: 2016-06-19（创建日期）
 * @Description:
 * 安全上下文认证的创建
 */
public class SecurityContextAuthorizer implements SecurityContext {
    private Principal principal;//表达一个主体的抽象的作用，这里是登录账号
    private javax.inject.Provider<UriInfo> uriInfo;
    private Set<String> roles;
    public SecurityContextAuthorizer(final javax.inject.Provider<UriInfo> uriInfo, final Principal principal, final String[] roles) {
        this.principal = principal;
        if (principal == null) {
            this.principal = new Principal() {
                @Override
                public String getName() {
                    return "anonymous";
                }

                public boolean implies(Subject subject) {
                    return true;
                }
            };
        }
        this.uriInfo = uriInfo;
        this.roles = new HashSet<>(Arrays.asList((roles != null) ? roles : new String[]{}));
    }

    @Override
    public Principal getUserPrincipal() {
        return this.principal;
    }

    @Override
    public boolean isUserInRole(String role) {
        //return user.getRole().equals(role);
        return true;
    }

    @Override
    public boolean isSecure() {
        return true;
    }

    @Override
    public String getAuthenticationScheme() {
        return SecurityContext.DIGEST_AUTH;
    }

    @Override
    public String toString() {
        Gson gson=new Gson();
        return gson.toJson(this);
    }

}
