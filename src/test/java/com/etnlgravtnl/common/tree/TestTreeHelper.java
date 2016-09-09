package com.etnlgravtnl.common.tree;

import com.alibaba.fastjson.JSON;
import com.etnlgravtnl.common.cache.redis.testbase.SpringBaseTest;
import com.etnlgravtnl.common.tree.entity.TreeDMLDBConfig;
import com.etnlgravtnl.system.entity.Menu;
import com.etnlgravtnl.system.service.MenuService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/7.
 */
public class TestTreeHelper extends SpringBaseTest{
    @Autowired
    TreeHelper treeHelper;
    @Autowired
    MenuService menuService;
    @Test
    public void basicTest() {
        List<TreeNode> list=treeHelper.withAs4Java("1","treeVariableWithAS","SELECT ID FROM treenodes WHERE ID IN ('12')");
        treeHelper.setTempNodeList(list);
        TreeNode root=treeHelper.generateRootNode(list);
        root=treeHelper.generateRootTreeNode(root);
        TreeDMLDBConfig treeDMLDBConfig=treeHelper.getTreeDMLConfig().getTreeDMLDBConfig("1");
        String htmlText=treeHelper.recursionJson4appendStr(treeDMLDBConfig.getHtmlNode4Trees(),root,new StringBuffer());
        System.out.println(  JSON.toJSONString(root));
    }

    @Test
    public void menuTest() {
        /*List<Menu> menusList4User=menuService.getMenu4User("1");
        List<Menu> menusTreeList4User=menuService.generateMenuTreeList(menusList4User);
        List<TreeNode> list=TreeHelper.changeMenuEnititiesToTreeNodes(menusTreeList4User);
        treeHelper.setTempNodeList(list);
        TreeNode root=treeHelper.AutowiredTreeNode4NodeList(null);*/
        String htmlText=menuService.getMenuHtmlText4User("1");
        System.out.println(  JSON.toJSONString(htmlText));
    }
}
