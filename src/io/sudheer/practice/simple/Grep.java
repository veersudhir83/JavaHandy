package io.sudheer.practice.simple;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.lang.IllegalArgumentException;
import java.io.FileNotFoundException;
import java.io.IOException;

class Grep
{
    public static final String USAGE = "Usage: grep [-r] PATTERN FILE";
    private String [] options;
    private String [] arguments;
    private Pattern pattern;
    private File file;
    private boolean recursive = false;
    private ArrayList<String> matches = new ArrayList<String>();

    /**
     * Constructor.
     *
     * @param args
     * @throws IllegalArgumentException
     * @throws FileNotFoundException
     */
    public Grep(String [] args) throws IllegalArgumentException, FileNotFoundException
    {
        if (args.length < 2) {
            throw new IllegalArgumentException("Grep requires a pattern and path to file.");
        }

        this.options = Arrays.copyOfRange(args, 0, args.length - 2);
        this.arguments = Arrays.copyOfRange(args, args.length - 2, args.length);

        for (String option : options) {
            if (!option.startsWith("-")) {
                throw new IllegalArgumentException("Grep requires a pattern and path to file.");
            }

            switch (option) {
                case "-r":
                    this.recursive = true;
                    break;
            }
        }

        for (String argument : arguments) {
            if (argument.startsWith("-")) {
                throw new IllegalArgumentException("Grep requires a pattern and path to file.");
            }
        }

        this.pattern = Pattern.compile(arguments[0]);
        this.file = new File(arguments[1]);

        if (!this.file.exists()) {
            throw new FileNotFoundException("File does not exist at: " + this.file.getAbsolutePath());
        }
    }

    /**
     * Start looking for matches.
     */
    public ArrayList<String> findMatches()
    {
        this.processNode(this.file);

        return this.matches;
    }

   /**
     * Process a file or directory, looking for the pattern within.
     *
     * @param file
     */
    private void processNode(File node)
    {
        if (node.isDirectory()) {
            if (this.recursive) {
                for (File file : node.listFiles()) {
                    this.processNode(file);
                }
            }
        } else {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(node));
                String line;
                int lineNumber = 0;
                Matcher matcher;

                while ((line = reader.readLine()) != null) {
                    lineNumber++;
                    matcher = this.pattern.matcher(line.trim());

                    while (matcher.find()) {
                        this.handleMatch(node, lineNumber, line);
                    }
                }

                reader.close();
            } catch (IOException ex) {
                // I/O error occurred, so skip this file.
            }
        }
    }

    /**
     * Handle a match for the pattern in the given file at the specified line number.
     *
     * @param file
     * @param lineNumber
     * @param line
     */
    private void handleMatch(File file, int lineNumber, String line)
    {
        this.matches.add(file.getPath() + ":" + Integer.toString(lineNumber) + ":" + line);
    }

    /**
     * Main.
     *
     * @param args
     */
    public static void main(String [] args)
    {
        try {
            Grep grep = new Grep(args);
            ArrayList<String> matches = grep.findMatches();

            for (String match : matches) {
                System.out.println(match);
            }
        } catch(IllegalArgumentException ex) {
            System.out.println(Grep.USAGE);
            System.exit(1);
        } catch(FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        }
    }
}
