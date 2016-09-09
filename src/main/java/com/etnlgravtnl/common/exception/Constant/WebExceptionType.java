package com.etnlgravtnl.common.exception.Constant;

/**
 * Created by admin on 2016/6/24.
 */
public enum WebExceptionType {

    JEDISOPRATOREXCEPTION("操作redis异常"),

    XMLRESOLVEEXCEPTION("xml解析异常"),
    UNKNOWNEXCEPTION("系统异常,未知异常请联系管理员"),

    FORBIDDEN("认证失败，请重新登录"),

    UNAUTHORIZED("登录失效，请重新登录"),

    LOGINNOTFUND("不存在的登录账号！"),
    REQUIRELOGINED("用户未登录！"),
    INVALIDNEWPASSWD("无效的新密码！"),
    INVALIDOLDPASSWD("原始密码不正确！"),
    LOGININVALIDPASSWD("无效的登录密码！"),
    LOGININVALIDSTATUS("用户状态异常！"),
    USERACCOUNTDUPLICATE("重复的用户信息！"),
    ROLENAMEDUPLICATE("重复的角色信息！"),

    //用户基本信息
    USERNOTFUND("用户不存在！"),

    COUNTERNOTFOUND("计数类型不存在！"),
    USERPHONEEMAIL("手机号不存在或邮箱不存在"),
    USERPASSWORD("原密码错误，修改失败"),
    //账户
    UPDATEPAYPWDACCUSERBANK("修改支付密码"),
    INSERTINVALIDACCUSERBANK("绑定银行卡失败"),
    //收货地址
    DELETEPOSTACCESS("删除收货地址失败"),
    INSERTPOSTACCESS("收货地址添加失败"),
    UPDATEPOSTACCESS("收货地址修改失败"),
    UPDATEINVALIDUSERINFO("用户信息修改异常！"),

    //项目回报设置
    DELETEINVALIDPROEJCTRETURNSET("删除项目回报异常！"),
    UPDATEINVALIDPROEJCTRETURNSET("更新项目回报异常！"),
    CROWDFUNDRETURNSETNOTFUND("项目回报不存在！"),

    POSTACCESSNOTFUND("收货地址不存在"),
    //添加
    INSERTINVALIDDYNAMIC("添加失败！"),
    //查询我的私信列表
    GETINVALIDCROWDFUNDCOMMENT("获取信息列表失败"),
    //我关注的项目列表
    GETINVALIDPROJECTCONCERNS("获取信息列表失败"),
    //我发起的项目列表
    GETINVALIDLAUNCHPROJECT("获取列表失败"),
    //查询热门项目列表
    SELECTINVALIDHOT("获取热门项目列表失败"),
    //查询我的私信列表
    GETINVALIDPROJECTLETTER("获取我的私信失败"),
    //删除私信
    DELETEINVALIDPROJECTLETTER("删除私信失败"),
    UPLOADINVALIDIMG("图片上传失败！"),
    //我支持的项目列表
    GETINVALIDPROJECTSUPPORT("获取支持项目失败");
    
    



























    private WebExceptionType(String value){
        this.type = value;
    }

    private String type;


    @Override
    public String toString() {
        return this.type;
    }
}
