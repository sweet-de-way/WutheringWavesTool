package com.kuro.kujiequ.model.sign;

/**
 * @program: WutheringWavesTool
 * @description:
 * @author: Leck
 * @create: 2024-07-06 20:19
 */
public class UserInfo extends SignUserInfo {
    private Integer id;
    private Long lastSignTime;
    private Boolean isWeb;//标志该Token时web的还是app的
    private String roleName;
    private String roleUrl;
    private Long creatTime;

    //private Integer
    public UserInfo() {
    }

    public UserInfo(String userId, String roleId, String token, Boolean isMain, Boolean isWeb) {
        super(userId, roleId, token, isMain);
        this.isWeb = isWeb;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getLastSignTime() {
        return lastSignTime;
    }

    public void setLastSignTime(Long lastSignTime) {
        this.lastSignTime = lastSignTime;
    }

    public Boolean getWeb() {
        return isWeb;
    }

    public void setWeb(Boolean web) {
        isWeb = web;
    }

    public Boolean getIsWeb() {
        return isWeb;
    }

    public void setIsWeb(Boolean web) {
        isWeb = web;
    }


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleUrl() {
        return roleUrl;
    }

    public void setRoleUrl(String roleUrl) {
        this.roleUrl = roleUrl;
    }

    public Long getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Long creatTime) {
        this.creatTime = creatTime;
    }
}
