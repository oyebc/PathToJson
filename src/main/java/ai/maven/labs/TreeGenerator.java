package ai.maven.labs;

import ai.maven.util.JSONUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author Pelumi<pelumi@maven.ai>
 *         Created on 18/03/16 at 18:48.
 */
public class TreeGenerator {

    private static final String ROOT_DIR = "/";

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(ROOT_DIR);

        //sample path list, a bigger one can easily be supplied as an arg
        List<String> paths = Arrays.asList("/foo/par/tar", "/foo/tar/bar", "/root/dev/bar");

        for (String path : paths) {
            if (path == null || path.isEmpty())
                continue;

            if (!path.startsWith("/"))
                //cry if there is a part that is not absolute
                //TODO alternatively change this to simply continue make the path valid by appending the leading /
                throw new UnsupportedOperationException("Invalid path provided... fart: " + path);
            else {
                //discard first / to prevent having empty directories after tokenization
                String[] directories = path.substring(1).split("/");
                TreeNode node = treeNode.addOrGetNodeWithName(directories[0]);
                for (int i = 1; i < directories.length; i++) {
                    //get the node from existing directory structure by adding or retrieving
                    node = node.addOrGetNodeWithName(directories[i]);
                }
            }
        }

        System.out.println(JSONUtils.toJSON(treeNode));
    }

}