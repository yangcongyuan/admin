package com.etnlgravtnl.system.service.impl;

import com.etnlgravtnl.common.service.impl.CrudServiceImpl;
import com.etnlgravtnl.common.tree.TreeHelper;
import com.etnlgravtnl.common.tree.TreeNode;
import com.etnlgravtnl.common.tree.entity.TreeDMLDBConfig;
import com.etnlgravtnl.system.dao.MenuDao;
import com.etnlgravtnl.system.entity.Menu;
import com.etnlgravtnl.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/29.
 */
@Service("menuService")
public class MenuServiceImpl extends CrudServiceImpl<MenuDao,Menu> implements MenuService{
    @Autowired
    private MenuDao menuDao;

    @Autowired
    TreeHelper treeHelper;
    /**
     * @Name:
     * @Author: junz（作者）
     * @Version: V1.00 （版本号）
     * @Create Date: 2016-06-19（创建日期）
     * @Description:
     *根据用户所有拥有的权限菜单。递归出菜单大类
     *
     */
    @Override
    public List<Menu> generateMenuTreeList(List<Menu> menuList4User) {
        if(menuList4User!=null&&menuList4User.size()>0)
        {
            for (int i = 0; i < menuList4User.size(); i++) {
                int parentId=menuList4User.get(i).getParentId();
                Menu menu=menuDao.get(String.valueOf(parentId));
                if(menu==null)
                {
                   continue;
                }else
                {
                    //判断是否已经插入到集合中
                    boolean inertFlag=true;
                    for (int j = 0; j < menuList4User.size(); j++)
                    {
                        if(menuList4User.get(j).getId()==parentId)
                        {
                            inertFlag=false;
                        }
                    }
                    if(inertFlag)
                    {
                        menuList4User.add(menu);
                        generateMenuTreeList(menuList4User);
                        break;
                    }
                }

            }
            return menuList4User;
        }else {
            return null;
        }

    }

    @Override
    public String getMenuHtmlText4User(String userId) {
        /*List<Menu> menuList4User=getMenu4User(userId);
        menuList4User=generateMenuTreeList(menuList4User);
        List<TreeNode> list=TreeHelper.changeMenuEnititiesToTreeNodes(menuList4User);
        treeHelper.setTempNodeList(list);
        TreeNode root=treeHelper.generateRootNode(list);
        root=treeHelper.generateRootTreeNode(root);
        TreeDMLDBConfig treeDMLDBConfig=treeHelper.getTreeDMLConfig().getTreeDMLDBConfig("1");*/
        TreeDMLDBConfig treeDMLDBConfig=treeHelper.getTreeDMLConfig().getTreeDMLDBConfig("2");
        List<Menu> menusList4User=getMenu4User(userId);
        List<Menu> menusTreeList4User=generateMenuTreeList(menusList4User);
        List<TreeNode> list=TreeHelper.changeMenuEnititiesToTreeNodes(menusTreeList4User);
        treeHelper.setTempNodeList(list);
        TreeNode root=treeHelper.AutowiredTreeNode4NodeList(null);
        String htmlText=treeHelper.recursionJson4appendStr(treeDMLDBConfig.getHtmlNode4Trees(),root,new StringBuffer());
        return htmlText;
    }

    @Override
    public List<Menu> getMenu4User(String userId)
    {
        List<Menu> menuList4User=menuDao.selectMenu4User(userId);
        return menuList4User;
    }


    public static void main(String[] args) {
        List list=new ArrayList();
        list.add(1);
        list.add(2);
        System.out.println(list.get(list.size()-1));
    }
}
