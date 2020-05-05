package reader.api;

import exception.ReaderException;


public interface IReader<Descriptor> {
    /**
     * Reads a file that contains information.
     * @param filename the name of the file.
     * @return a description with the information parsed from the file
     */
   Descriptor readFile(final String filename);

   /**
    * Check if line is valid...should be more cautious
    * @param  line current line in file
    * @return true if valid, throws exception otherwise
    * */

    default boolean isValid(String line) throws ReaderException {
        if(line.isEmpty())
            throw new ReaderException("Line not valid!");
        return true;
   }

}
