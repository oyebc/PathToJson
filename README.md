# PathToJson
Generate a Json object representing a directory structure of a file system from a list of paths.

## Building
To build the code, after checkout use maven to clean and package. `cd` to the root directory and execute the following:

`mvn clean`

`mvn package` (this generates a directory called runtime at the the same level with the project)

## Running
`cd` into runtime. There is a script run.sh (make it executable with `chmod +x run.sh`)

Execute the application with the following:

`./run.sh name_of_file_with_paths name_of_output_file(optional)`

The output is generated in the same directory and logs are displayed on the screen when it's done.

### Notes
To tweak the output Json structure format, play around with the CustomSerializer class.

A sample input file will be of the form:

> /foo/par/tar

> /foo/tar/bar

> /root/dev/bar
