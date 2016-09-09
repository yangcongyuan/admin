package com.etnlgravtnl.common.tree;

import com.alibaba.fastjson.JSONObject;
import com.etnlgravtnl.common.exception.Constant.WebExceptionType;
import com.etnlgravtnl.common.exception.MapperSupport.WebActionException;
import com.etnlgravtnl.common.mapper.JaxbMapper;
import com.etnlgravtnl.common.tree.entity.*;
import com.etnlgravtnl.common.utils.FreeMarkers;
import com.etnlgravtnl.common.utils.Reflections;
import com.etnlgravtnl.system.entity.Menu;
import org.apache.ibatis.session.SqlSession;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
@Component("treeHelper")
public class TreeHelper {
    @Resource
    private SqlSession sqlSession;
    private TreeNode root;
    private HashMap nodeMap;
    private List<TreeNode> tempNodeList;
    private boolean isValidTree = true;

    public TreeHelper() {

    }

    public TreeHelper(List<TreeNode> treeNodeList) {
        tempNodeList = treeNodeList;
        //generateTree();
    }


    public static TreeNode getTreeNodeById(TreeNode tree, String id) {
        if (tree == null)
            return null;
        TreeNode treeNode = tree.findTreeNodeById(id);
        return treeNode;
    }

    /** generate a tree from the given treeNode or entity list */
/*    public Map generateTree() {
        nodeMap = putNodesIntoMap();
        //treeNode 进来，先去判断
    }*/

    /** generate a tree from the given treeNode or entity list */
    public TreeNode generateRootTreeNode(TreeNode treeNode)
    {
       /* if(treeNode==null)
        {
            return treeNode;
        }else
        {*/
            String selfId = treeNode.getSelfId();
            Iterator it = tempNodeList.iterator();
            boolean insertFlag=false;
            while (it.hasNext()) {
                TreeNode childNode = (TreeNode) it.next();
                if(selfId.equals(childNode.getParentId()))
                {
                    childNode=generateRootTreeNode(childNode);
                    treeNode.addChildNode(childNode);
                    insertFlag=true;
                }
           }
         /*
           if(!insertFlag)
            {
                return null;
            }

        }*/
        return treeNode;
    }
    /**
     * @Name:
     * @Author: junz（作者）
     * @Version: V1.00 （版本号）
     * @Create Date: 2016-06-19（创建日期）
     * @Description:
     *根据node List 自动装配成node 树
     *
     */

    public TreeNode AutowiredTreeNode4NodeList(TreeNode treeNode)
    {
        //初次递归，检索出list中的根节点，放置在根对象中
        if(treeNode==null)
        {
            treeNode=new TreeNode();
            treeNode.setLevel(0);
            treeNode.setNodeName("根节点");
            treeNode.setSelfId("root");
            treeNode.setParentId("########");
            List<TreeNode> removeIndexList=new ArrayList<TreeNode>();
            for (int j = 0; j < tempNodeList.size(); j++)
            {
                boolean insertFlag=true;
                TreeNode childNode =tempNodeList.get(j);
                for (int i = 0; i < tempNodeList.size(); i++) {
                    if(childNode.getParentId().equals(tempNodeList.get(i).getSelfId()))
                    {
                        insertFlag=false;
                        break;
                    }
                }
                if(insertFlag)
                {
                    childNode.setLevel(1);
                    treeNode.addChildNode(childNode);
                    removeIndexList.add(childNode);
                }
            }
            for (int i = 0; i < removeIndexList.size(); i++) {
                tempNodeList.remove(removeIndexList.get(i));
            }
        }
        for (int i = 0; i < tempNodeList.size(); i++) {
            TreeNode childNode = tempNodeList.get(i);
            boolean insertFlag= treeNode.insertJuniorNode(childNode);
            if(insertFlag)
            {
                tempNodeList.remove(i);
                i=i-1;
            }
        }
        if(tempNodeList.size()!=0)
        {
            AutowiredTreeNode4NodeList(treeNode);
        }
        return treeNode;
    }


    /**
     * put all the treeNodes into a hash table by its id as the key
     *
     * @return hashmap that contains the treenodes
     */


    /**
     * set the parent nodes point to the child nodes
     *
     * @param nodeMap
     *            a hashmap that contains all the treenodes by its id as the key
     */
    protected void putChildIntoParent(HashMap nodeMap) {
        Iterator it = nodeMap.values().iterator();
        while (it.hasNext()) {
            TreeNode treeNode = (TreeNode) it.next();
            String parentId = treeNode.getParentId();
            String parentKeyId = String.valueOf(parentId);
            if (nodeMap.containsKey(parentKeyId)) {
                TreeNode parentNode = (TreeNode) nodeMap.get(parentKeyId);
                if (parentNode == null) {
                    this.isValidTree = false;
                    return;
                } else {
                    parentNode.addChildNode(treeNode);
                    // System.out.println("childId: " +treeNode.getSelfId()+" parentId: "+parentNode.getSelfId());
                }
            }
        }
    }



    /** initialize the tempNodeList property */
    protected void initTempNodeList() {
        if (this.tempNodeList == null) {
            this.tempNodeList = new ArrayList<TreeNode>();
        }
    }

    /** add a tree node to the tempNodeList */
    public void addTreeNode(TreeNode treeNode) {
        initTempNodeList();
        this.tempNodeList.add(treeNode);
    }

    /**
     * insert a tree node to the tree generated already
     *
     * @return show the insert operation is ok or not
     */
    public boolean insertTreeNode(TreeNode treeNode) {
        boolean insertFlag = root.insertJuniorNode(treeNode);
        return insertFlag;
    }

    /**
     * adapt the entities to the corresponding treeNode
     *
     * @param entityList
     *            list that contains the entities
     *@return the list containg the corresponding treeNodes of the entities
     */
    /*public static List<TreeNode> changeEnititiesToTreeNodes(List entityList) {
        OrganizationEntity orgEntity = new OrganizationEntity();
        List<TreeNode> tempNodeList = new ArrayList<TreeNode>();
        TreeNode treeNode;

        Iterator it = entityList.iterator();
        while (it.hasNext()) {
            orgEntity = (OrganizationEntity) it.next();
            treeNode = new TreeNode();
            treeNode.setObj(orgEntity);
            treeNode.setParentId(orgEntity.getParentId());
            treeNode.setSelfId(orgEntity.getOrgId());
            treeNode.setNodeName(orgEntity.getOrgName());
            tempNodeList.add(treeNode);
        }
        return tempNodeList;
    }*/

    public static List<TreeNode> changeMenuEnititiesToTreeNodes(List<Menu> entityList) {
        if(entityList!=null&&entityList.size()>0)
        {
            Menu menu = new Menu();
            List<TreeNode> tempNodeList = new ArrayList<TreeNode>();
            TreeNode treeNode;

            Iterator it = entityList.iterator();
            while (it.hasNext()) {
                menu =  (Menu)it.next();
                treeNode = new TreeNode();
                treeNode.setObj(menu);
                treeNode.setParentId(String.valueOf(menu.getParentId()));
                treeNode.setSelfId(String.valueOf(menu.getId()));
                treeNode.setNodeName(menu.getName());
                treeNode.setHref(menu.getHref());
                treeNode.setIcon(menu.getIcon());
                tempNodeList.add(treeNode);
            }
            return tempNodeList;
        }else
        {
            return null;
        }

    }
    /**
     * XML文件转换为对象
     * @param fileName
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T fileToObject(String fileName, Class<?> clazz){
        try {
            String pathName =  fileName;
//			logger.debug("File to object: {}", pathName);
            ClassPathResource resource = new ClassPathResource(pathName);
            InputStream is = resource.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = br.readLine();
                if (line == null){
                    break;
                }
                sb.append(line).append("\r\n");
            }
            if (is != null) {
                is.close();
            }
            if (br != null) {
                br.close();
            }
//			logger.debug("Read file content: {}", sb.toString());
            return (T) JaxbMapper.fromXml(sb.toString(), clazz);
        } catch (IOException e) {
            //logger.warn("Error file convert: {}", e.getMessage());
            throw  new WebActionException(e, WebExceptionType.XMLRESOLVEEXCEPTION,fileName);
        }

    }
    /**
     * 获取树生成配置对象
     * @return
     */
    public static GenTrees getTreeDMLConfig(){
        return fileToObject("/treeDMLConfig.xml", GenTrees.class);
    }

    /**
     * 获取树生成配置对象
     * @return
     */
    public static GenSqlTemplates getSqlTemplates(){
        return fileToObject("/templates/commonSqlTemplate.xml", GenSqlTemplates.class);
    }


    public  List withAs4Java(String treeConfigId,String sqlTemplatesId,String initParentIdPoolSql)
    {
        SqlTemplate sqlTemplate=getSqlTemplates().getSqlTemplate(sqlTemplatesId);
        TreeDMLDBConfig treeDMLDBConfig=getTreeDMLConfig().getTreeDMLDBConfig(treeConfigId);
        Map<String, Object> variableMap=Reflections.transBean2Map(treeDMLDBConfig);
        variableMap.put("initParentIdPoolSql",initParentIdPoolSql);
        String sql=FreeMarkers.renderString(sqlTemplate.getSql(),variableMap);
        List<TreeNode> treeListFromDB=sqlSession.selectList("com.etnlgravtnl.system.dao.AdminUserDao.findRecords",sql);
        this.tempNodeList=treeListFromDB;
        return treeListFromDB;
    }

    public String recursionJson4appendStr(List<HtmlNode4Tree> htmlNode4Trees, TreeNode treeNode,StringBuffer htmlText)
    {
        int level=treeNode.getLevel();
        if(!treeNode.getSelfId().equals("root"))
        {
            HtmlNode4Tree htmlNode4Tree= getHtmlNode4Tree(level,htmlNode4Trees);
            Map<String,Object> htmlVariable =Reflections.transBean2Map(treeNode);
            String htmlHeadText=FreeMarkers.renderString(htmlNode4Tree.getHtmlHeadText(),htmlVariable);
            if( treeNode.getChildList().size()==0)
            {
                String leafNodeText=htmlNode4Tree.getLeafNodeText().trim();
                htmlHeadText= htmlHeadText.replace(leafNodeText,"");
            }
            htmlText.append(htmlHeadText);
            for (int i = 0; i < treeNode.getChildList().size(); i++)
            {
                htmlHeadText=recursionJson4appendStr(htmlNode4Trees, treeNode.getChildList().get(i),htmlText);
               //htmlText.append(htmlHeadText);
            }

            String htmlFootText=FreeMarkers.renderString(htmlNode4Tree.getHtmlFootText(),htmlVariable);
            htmlText.append(htmlFootText);
        }else
        {
            for (int i = 0; i < treeNode.getChildList().size(); i++) {
                recursionJson4appendStr(htmlNode4Trees,  treeNode.getChildList().get(i),htmlText);
            }

        }
        return htmlText.toString();
    }

    public HtmlNode4Tree getHtmlNode4Tree(int level,List<HtmlNode4Tree> htmlNode4Trees)
    {
        if(htmlNode4Trees!=null)
        {
            for (int i = 0; i < htmlNode4Trees.size(); i++)
            {
                HtmlNode4Tree htmlNode4Tree=htmlNode4Trees.get(i);
                String levelInterval=htmlNode4Tree.getLevel();
                if(levelInterval.indexOf("-")!=-1)
                {
                    String[] minMax=levelInterval.split("-");
                    if(level>=Integer.parseInt(minMax[0])&&level<=Integer.parseInt(minMax[1]))
                    {
                        return htmlNode4Tree;
                    }
                }else
                {
                    if(String.valueOf(level).equals(levelInterval))
                    {
                        return htmlNode4Tree;
                    }
                }

            }
        }

        return null;
    }

    public TreeNode generateRootNode( List<TreeNode> list)
    {
        TreeNode root=new TreeNode();
        root.setNodeName("root");
        String rootid="0";
        int level=99999999;
        for (int i = 0; i < list.size(); i++)
        {
            TreeNode treeNode= list.get(i);
            if(level>treeNode.getLevel()){
                level=treeNode.getLevel();
                rootid=treeNode.getParentId();
            }
        }
        root.setSelfId(rootid);
        return root;
    }

    public boolean isValidTree() {
        return this.isValidTree;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public List<TreeNode> getTempNodeList() {
        return tempNodeList;
    }

    public void setTempNodeList(List<TreeNode> tempNodeList) {
        if(tempNodeList==null)
        {
            tempNodeList=new ArrayList<TreeNode>();
        }
        this.tempNodeList = tempNodeList;
    }

    public static void main(String[] args) {
        //GenTrees genTrees= TreeHelper.getConfig();
        TreeHelper tempNodeList=new TreeHelper();
        List<TreeNode> list=tempNodeList.withAs4Java("1","treeVariableWithAS","SELECT ID AS BID FROM treenodes WHERE ID IN ('1','3','5')");

        System.out.println("111");
    }

}
