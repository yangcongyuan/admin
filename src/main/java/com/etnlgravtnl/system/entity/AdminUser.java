package com.etnlgravtnl.system.entity;

import com.etnlgravtnl.common.persistence.DataEntity;

import javax.security.auth.Subject;
import java.security.Principal;
import java.util.Date;
public class AdminUser extends DataEntity<AdminUser> implements Principal {

    private String loginName;

    private String email;

    private String password;

    private int userType;

    private String phone;

    private Date timeCreate;

    private Date timeLastLogin;

    private Date timeLastLogout;

    private Boolean pwdRest;

    private Boolean isAuth;

    private Boolean isLocked;

    private Date expireDate;
    private Token mainToken;
    private Token accessToken;

    public Token getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(Token accessToken) {
        this.accessToken = accessToken;
    }

    public Token getMainToken() {
        return mainToken;
    }

    public void setMainToken(Token mainToken) {
        this.mainToken = mainToken;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Date getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Date timeCreate) {
        this.timeCreate = timeCreate;
    }

    public Date getTimeLastLogin() {
        return timeLastLogin;
    }

    public void setTimeLastLogin(Date timeLastLogin) {
        this.timeLastLogin = timeLastLogin;
    }

    public Date getTimeLastLogout() {
        return timeLastLogout;
    }

    public void setTimeLastLogout(Date timeLastLogout) {
        this.timeLastLogout = timeLastLogout;
    }

    public Boolean getPwdRest() {
        return pwdRest;
    }

    public void setPwdRest(Boolean pwdRest) {
        this.pwdRest = pwdRest;
    }

    public Boolean getIsAuth() {
        return isAuth;
    }

    public void setIsAuth(Boolean isAuth) {
        this.isAuth = isAuth;
    }

    public Boolean getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Boolean isLocked) {
        this.isLocked = isLocked;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean implies(Subject subject) {
        return false;
    }
}