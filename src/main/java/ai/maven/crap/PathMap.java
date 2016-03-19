package ai.maven.crap;

import java.util.*;

/**
 * @author Pelumi Aboluwarin
 *         <p/>
 *         WIP: Initial probably dumb attempt to solve the problem, a part of my mind still thinks there is a
 *         genius recursion i can appply to the insane data structure that will render the desired output
 */
public class PathMap {

    public static void main(String[] args) {
        Map<Integer, Map<String, Set<String>>> dirStructure = new HashMap<>();


        List<String> paths = Arrays.asList("/foo/par/tar", "/foo/tar/bar", "/root/dev/bar");

        //for each path in the list of paths
        for (String path : paths) {
            if (!path.startsWith("/")) //ensure every path is a valid absolute path or decide what to do if it's not e.g. append it
                throw new UnsupportedOperationException("Invalid path provided: " + path);
            else {
                //discard first / to prevent having empty directories
                String[] directories = path.substring(1).split("/");
                int totalDirsInPath = directories.length;
                for (int i = 0; i < totalDirsInPath; i++) {
                    if (dirStructure.containsKey((Integer.valueOf(i + 1)))) {
                        Map<String, Set<String>> existingDir = dirStructure.get(Integer.valueOf(i + 1));
                        if (existingDir.containsKey(directories[i])) { //TODO remove this if block
                            //do nothing or think of what to do later, looks like there is nothing to be one
                        } else {
                            Set<String> dirContent = new HashSet<>();
                            existingDir.put(directories[i], dirContent);

                        }
                    } else {
                        Map<String, Set<String>> newDir = new HashMap<>();
                        Set<String> dirFiles = new HashSet<>();
                        newDir.put(directories[i], dirFiles);
                        dirStructure.put((Integer.valueOf(i + 1)), newDir);

                    }


                    //add as child of previous directory
                    Map<String, Set<String>> existingDir = dirStructure.get(Integer.valueOf(i));
                    if (existingDir != null && i != 0) {
                        if (existingDir.containsKey(directories[i - 1])) {
                            Set<String> dirFiles = existingDir.get(directories[i - 1]);
                            dirFiles.add(directories[i]);
                            existingDir.put(directories[i - 1], dirFiles);
                            dirStructure.put(Integer.valueOf(i), existingDir);
                        }
                    }
                }
            }


        }
        // System.out.println(dirStructure.get(Integer.valueOf(1)));
        System.out.println(dirStructure);

    }

    /* The JSon output logic is this

      Get all dirs in the first level i.e. get he key 1 from the map
      for each directory,
      - go through it's list of subdirectories
      - check if it each subdir has directories by going to the next level (e.g. 2 - or whatever level is next based on the current level)
      - get it's children and repeat step above till you're in a leaf directory
        */


//    WIP,
    //private Map<String, Set<String>> directoryFiles(int level){

    //}
    /*
    private static void renderJson(Map<Integer, Map<String, Set<String>>> dirStructure, int level) {
        //for each key in the map
        //get a list of it's children
        //for each of it's children, get a list of their children, the list of their children is a level below
        JSONObject jsonObject = new JSONObject();
        JSONArray fullArray = new JSONArray();
        //retrieve directories in base directory
        Map<String, Set<String>> rootDirectoryFiles = dirStructure.get(1);

        for (String s : rootDirectoryFiles.keySet()) {
      //      fullArray.put(s, dirStructure.get)

        }
        

        for (Integer integer : dirStructure.keySet()) {
            Map<String, Set<String>> directoryFiles = dirStructure.get(integer);
            //list it's children
            for (String dirName : directoryFiles.keySet()) {
                //print all files in each directory, they will all be baseline json keys
             //   jsonObject.put(dirName, )

                Set<String> currentDirectoryFiles = directoryFiles.get(dirName);

            }
        }

    }

    private static void buildDirJson(String dirName,
                                           JSONObject object,
                                           Map<Integer, Map<String, Set<String>>> dirStructure,
                                           int level) throws JSONException {

        Map<String, Set<String>> directoryFiles = dirStructure.get(level);
        if (directoryFiles != null) {
            //dir has no files
            if (directoryFiles.isEmpty()){
                JSONObject obj = new JSONObject();
          //      obj.put(dirName)
            }
            for (String dirFile : directoryFiles.keySet()) {
                buildDirJson(dirFile, object, dirStructure, level + 1);
            }
        } else{
            JSONObject obj = new JSONObject();
            System.out.println(dirName);
            obj.put(dirName, new JSONArray());
        }
        if(object == null)
            object = new JSONObject();

        JSONArray jsonArray = new JSONArray();

        //go to the child directory
        directoryFiles = dirStructure.get(level + 1);
        if (directoryFiles != null && directoryFiles.containsKey(dirName)){

        }



    }*/
}