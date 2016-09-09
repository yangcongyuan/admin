package com.etnlgravtnl.common.tree.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement(name="trees")
public class GenTrees {
    private List<TreeDMLDBConfig> treeDMLDBConfig;

    //@XmlElementWrapper(name = "treenode")
    @XmlElement(name = "treeDMLDBConfig")
    public List<TreeDMLDBConfig> getTreeDMLDBConfig() {
        return treeDMLDBConfig;
    }

    public void setTreeDMLDBConfig(List<TreeDMLDBConfig> treeDMLDBConfig) {
        this.treeDMLDBConfig = treeDMLDBConfig;
    }
    public  TreeDMLDBConfig getTreeDMLDBConfig(String id)
    {
        for (int i = 0; i < treeDMLDBConfig.size(); i++)
        {
            TreeDMLDBConfig treeDMLDBConfig=this.treeDMLDBConfig.get(i);
            if(treeDMLDBConfig.getId().equals(id))
            {
                return treeDMLDBConfig;
            }
        }
        return null;
    }
}
