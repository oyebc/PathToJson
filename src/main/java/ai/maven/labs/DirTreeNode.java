package ai.maven.labs;

import ai.maven.util.JSONUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Pelumi<pelumi@maven.ai>
 *         Created on 18/03/16 at 18:43.
 *
 * A directory node represents a directory containing a list of other directories
 */

@JsonSerialize(using = CustomSerializer.class)
public class DirTreeNode implements Serializable {

    private String name;
    //the map of the node to node is an hack to help retrieve an existing node in O(1) time.
    //there is an interesting conversation around it here: http://stackoverflow.com/questions/7283338/getting-an-element-from-a-set
    //private Map<DirTreeNode, DirTreeNode> children;

    private List <DirTreeNode> children;
    public DirTreeNode(String name) {
        this.name = name;
       // this.children = new HashMap<>();
        this.children = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Collection<DirTreeNode> getChildren() {
        //return children.values();
        return this.children;
    }

    /**
     * This is the crux of this code. Add a new node to the tree or retrieve node head if existing.
//     */
//    public DirTreeNode addOrGetNodeWithName(String child){
//        DirTreeNode childNode = null;
//        DirTreeNode keyNode = new DirTreeNode(child);
//
//        if (this.children.containsKey(keyNode)){
//           childNode = this.children.get(keyNode);
//        } else {
//            childNode = new DirTreeNode(child);
//            this.children.put(keyNode, childNode);
//        }
//        return childNode;
//    }

    public DirTreeNode addOrGetNodeWithName(String child){
    
        DirTreeNode childNode=null;
        for (DirTreeNode currentNode: this.children)
        {
            if(currentNode.name.equals(child))
            {
                childNode = currentNode;
                break;
            }
        }
        if(childNode==null)
        {
             childNode = new DirTreeNode(child);
             this.children.add(childNode);
        }
        return childNode;
    }
    /** For the purpose of identifying directories that have already been found in a level,
     * Directories are equal if they have the same name. We naively ignore their content
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DirTreeNode dirTreeNode = (DirTreeNode) o;

        return name.equals(dirTreeNode.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return JSONUtils.toJSON(this);
    }

}
