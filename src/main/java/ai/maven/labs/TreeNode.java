package ai.maven.labs;

import ai.maven.util.JSONUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Pelumi<pelumi@maven.ai>
 *         Created on 18/03/16 at 18:43.
 */

@JsonSerialize(using = CustomSerializer.class)
public class TreeNode implements Serializable {

    private String name;
    private Map<TreeNode, TreeNode> children;

    public TreeNode(String name) {
        this.name = name;
        this.children = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public Collection<TreeNode> getChildren() {
        return children.values();
    }

    public TreeNode addOrGetNodeWithName(String child){
        TreeNode childNode = null;
        TreeNode keyNode = new TreeNode(child);

        if (this.children.containsKey(keyNode)){
           childNode = this.children.get(keyNode);
        } else {
            childNode = new TreeNode(child);
            this.children.put(keyNode, childNode);
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

        TreeNode treeNode = (TreeNode) o;

        return name.equals(treeNode.name);
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
