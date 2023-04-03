import java.io.IOException;
import java.util.*;
import java.io.FileReader;

/**
 * @author Adam Biggs
 * @version 4/1/2023
 */
public class SolutionClass implements WordSearchGame {

    //vars
    private String[][] board;
    private TreeSet<String> lexicon;
    private Boolean[][] visited;
    private int length;
    private boolean loadLexicon;

    public SolutionClass() {
        lexicon = new TreeSet<>();
        loadLexicon = false;
    }

    //attempt 4
    public void loadLexicon(String fileName) {

        if(fileName == null) {
            throw new IllegalArgumentException();
        }
        Scanner scanFile;
        Scanner scanLine;
        String line;

        try {

            scanFile = new Scanner(new FileReader(fileName));

            while (scanFile.hasNext()) {

                line = scanFile.nextLine();

                scanLine = new Scanner(line);

                scanLine.useDelimiter(" ");

                while (scanLine.hasNext()) {
                    lexicon.add(scanLine.next().toLowerCase());
                }

            }

        }
        catch (Exception e) {

            throw new IllegalArgumentException();
        }

        loadLexicon = true;
    }

    public void setBoard(String[] letterArray) throws IllegalArgumentException {

        if (letterArray == null) {

            throw new IllegalArgumentException();
        }

        int length = letterArray.length;

        double sqrt = Math.sqrt(length);

        if (sqrt != (int)sqrt) {

            throw new IllegalArgumentException();

        }
        if (sqrt % 1 > 0) {

            throw new IllegalArgumentException();
        }

        else {

            length = (int) sqrt;
            board = new String[length][length];
            visited = new Boolean[length][length];
            int count = 0;
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < length; j++) {
                    visited[i][j] = false;
                    board[i][j] = letterArray[count].toLowerCase();
                    count++;
                }
            }
        }
    }

    public String getBoard() {
        String result = "";

        for (String[] s: board) {

            for (String string: s) {

                result = result + string;
            }
        }

        return result;
    }
    //TODO
    @Override
    public SortedSet<String> getAllScorableWords(int minimumWordLength) {

        if (minimumWordLength < 1) {
            throw new IllegalArgumentException();
        }

        if (!loadLexicon) {
            throw new IllegalStateException();
        }

        SortedSet<String> scorableWords = new TreeSet<>();

        for (String word : lexicon) {
            if (word.length() >= minimumWordLength && isOnBoard(word).size() > 0) {
                scorableWords.add(word);
            }
        }

        return scorableWords;
    }

    public int getScoreForWords(SortedSet<String> words, int minimumWordLength) {



        if (minimumWordLength < 1) {
            throw new IllegalArgumentException();
        }

        if (!loadLexicon) {
            throw new IllegalStateException();
        }

        int score = 0;

        for (String word: words) {

            int total = word.length();

            score += 1 + (total - minimumWordLength);
        }

        return score;
    }


    public boolean isValidWord(String wordToCheck) {
        if (wordToCheck == null) {
            throw new IllegalArgumentException();
        }

        return lexicon.contains(wordToCheck.toLowerCase());
    }

    public boolean isValidPrefix(String prefixToCheck) {


        if (!loadLexicon) {
            throw new IllegalStateException();
        }

        if (prefixToCheck == null) {
            throw new IllegalArgumentException();
        }

        return lexicon.ceiling(prefixToCheck).startsWith(prefixToCheck);
    }

    //TODO
    @Override
    public List<Integer> isOnBoard(String wordToCheck) {
        if (wordToCheck == null) {
            throw new IllegalArgumentException();
        }

        if (!loadLexicon) {
            throw new IllegalStateException();
        }

        List<Integer> locations = new ArrayList<>();

        for (int row = 0; row < length; row++) {
            for (int col = 0; col < length; col++) {
                if (board[row][col].equals(String.valueOf(wordToCheck.charAt(0)))) {
                    visited[row][col] = true;
                    List<Integer> path = new ArrayList<>();
                    if (searchBoard(wordToCheck.substring(1), row, col)) {
                        path.add(0, row * length + col);
                        return path;
                    }
                    visited[row][col] = false;
                }
            }
        }

        return locations;
    }
    private boolean searchBoard(String remainingLetters, int row, int col) {

        if (remainingLetters.length() == 0) {

            return true;
        }

        for (int r = row - 1; r <= row + 1; r++) {

            for (int c = col - 1; c <= col + 1; c++) {

                if (r >= 0 && r < length && c >= 0 && c < length && !visited[r][c]
                        && board[r][c].equals(String.valueOf(remainingLetters.charAt(0)))) {

                    visited[r][c] = true;
                    if (searchBoard(remainingLetters.substring(1), r, c)) {

                        visited[r][c] = false;

                        return true;
                    }

                    visited[r][c] = false;
                }
            }
        }

        return false;
    }




}