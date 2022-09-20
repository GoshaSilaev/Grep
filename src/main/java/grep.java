
import org.jetbrains.annotations.NotNull;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class grep {

    @Option(name = "-r")
    private Boolean regex = false;

    @Option(name = "-v")
    private Boolean invert = false;

    @Option(name = "-i")
    private Boolean ignore = false;

    @Argument
    private List<String> arguments = new LinkedList<String>();

    public void parseArguments(String[] args) throws IOException {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
            if (arguments.isEmpty()) {
                throw new IllegalArgumentException("Arguments is Empty");
            }

        } catch (Exception CmdLineException) {
            throw new IllegalArgumentException("Error");
        }

        String wordsToFind = arguments.get(0);
        if (regex && arguments.size() > 2) {
            for (int i = 1; i < arguments.size() - 1; i++) {
                wordsToFind += " " + arguments.get(i);
            }
        }

        String fileName = arguments.get(arguments.size() - 1);
        grepWork(wordsToFind, ignore, invert, fileName);
    }

    public void grepWork(String word, @NotNull Boolean ign, Boolean invert, String file) throws IOException {
        StringBuilder res = new StringBuilder();
        Pattern pattern = (ign) ? Pattern.compile(word.toLowerCase(Locale.ROOT)) : Pattern.compile(word);


        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String str = reader.readLine();
            while (str != null) {
                Matcher matcher = (ign) ? pattern.matcher(str.toLowerCase(Locale.ROOT)) : pattern.matcher(str);

                if ((invert && !matcher.find()) || (matcher.find())) {
                    res.append(str).append("\n");
                }
                str = reader.readLine();
            }
        }

        String strRes = res.toString();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Input/GrepRes.txt"))) {
            writer.write(strRes);
        }
    }

    public static void main(String[] args) throws IOException {
        new grep().parseArguments(args);
    }
}







