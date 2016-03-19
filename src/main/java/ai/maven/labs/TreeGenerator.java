package ai.maven.labs;

import ai.maven.util.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author Pelumi<pelumi@maven.ai>
 *         Created on 18/03/16 at 18:48.
 */
public class TreeGenerator {

    private static final Logger log = LoggerFactory.getLogger(TreeGenerator.class);

    private static final String ROOT_DIR = "/";
    private static String OUTPUT_FILE = "paths_output.json";

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            log.error("Missing runtime arguments. Execution format is: ./run file_with_paths output_filename");
            log.info("A sample input file is a file with the following lines: \n/foo/par/tar\n/foo/tar/bar\n/root/dev/bar");
            log.info("Exiting...");
            System.exit(0);
        }
        //check if input file is available
        Path p = Paths.get(args[0]);
        if (!Files.exists(p) || !Files.isRegularFile(p)) {
            log.error("Invalid input file [{}] provided.", args[0]);
            log.info("Exiting...");
            System.exit(0);
        }

        //check if output file is provided. If it's not use the default
        if (args.length < 2) {
            log.warn("Output file not provided. Output will be written to: {}", OUTPUT_FILE);
        } else
            OUTPUT_FILE = args[1].endsWith(".json") ? args[1] : args[1] + ".json";

        DirTreeNode treeNode = new DirTreeNode(ROOT_DIR);

        log.info("Processing paths in file: {}", args);
        int currentLine = 0;
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        for (String path; (path = br.readLine()) != null; ) {
            currentLine++;
            path = path.trim();
            if (path.isEmpty()) {
                log.warn("Null or empty path found at line: {}", currentLine);
                continue;
            }

            log.debug("Processing line: [{}] with path: [{}].", currentLine, path);

            if (!path.startsWith("/"))
                log.warn("Path provided was not absolute path. It's assumed to be a mistake: {}", path);
            else {
                //discard first / to prevent having empty directories after tokenization
                path = (path.startsWith("/")) ? path.substring(1) : path;
                String[] directories = path.split("/");
                DirTreeNode node = treeNode.addOrGetNodeWithName(directories[0]);
                for (int i = 1; i < directories.length; i++) {
                    //get the node from existing directory structure by adding or retrieving
                    node = node.addOrGetNodeWithName(directories[i]);
                }
            }
        }

        String output = JSONUtils.toJSON(treeNode);
        log.debug(output);
        Files.write(Paths.get(OUTPUT_FILE), output.getBytes(), StandardOpenOption.WRITE);

        log.info("All paths processed. Output written to file: {}. Total lines processed: {}.", OUTPUT_FILE, currentLine);
    }

}