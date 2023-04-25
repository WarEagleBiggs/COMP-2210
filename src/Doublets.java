import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Provides an implementation of the WordLadderGame interface.
 *
 * @author Adam Biggs
 */
public class Doublets implements WordLadderGame {
    // The word list used to validate words.
    // Must be instantiated and populated in the constructor.
    /////////////////////////////////////////////////////////////////////////////
    // DECLARE A FIELD NAMED lexicon HERE. THIS FIELD IS USED TO STORE ALL THE //
    // WORDS IN THE WORD LIST. YOU CAN CREATE YOUR OWN COLLECTION FOR THIS     //
    // PURPOSE OF YOU CAN USE ONE OF THE JCF COLLECTIONS. SUGGESTED CHOICES    //
    // ARE TreeSet (a red-black tree) OR HashSet (a closed addressed hash      //
    // table with chaining).
    /////////////////////////////////////////////////////////////////////////////
    List<String> empty = new ArrayList<>();
    TreeSet<String> lexicon;
    /**
     * Instantiates a new instance of Doublets with the lexicon populated with
     * the strings in the provided InputStream. The InputStream can be formatted
     * in different ways as long as the first string on each line is a word to be
     * stored in the lexicon.
     */

    public Doublets(InputStream in) {
        try {
            lexicon = new TreeSet<>();
            Scanner s = new Scanner(new BufferedReader(new InputStreamReader(in)));
            while (s.hasNext()) {
                String str = s.next();
                lexicon.add(str);
                s.nextLine();
            }
            in.close();
        } catch (java.io.IOException e) {
            System.err.println("Error reading from InputStream.");
            System.exit(1);
        }
    }

    @Override
    public int getWordCount() {
        return lexicon.size();
    }

    @Override
    public boolean isWord(String str) {
        return lexicon.contains(str);
    }

    @Override
    public int getHammingDistance(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return -1;
        }
        int distance = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                distance++;
            }
        }
        return distance;
    }

    @Override
    public List<String> getNeighbors(String word) {
        List<String> neighbors = new ArrayList<>();
        for (String candidate : lexicon) {
            if (getHammingDistance(word, candidate) == 1) {
                neighbors.add(candidate);
            }
        }
        return neighbors;
    }

    @Override
    public boolean isWordLadder(List<String> sequence) {
        if (sequence == null || sequence.isEmpty()) {
            return false;
        }

        for (int i = 0; i < sequence.size() - 1; i++) {
            String word1 = sequence.get(i).toUpperCase();
            String word2 = sequence.get(i + 1).toUpperCase();

            if (!isWord(word1) || !isWord(word2) || getHammingDistance(word1, word2) != 1) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<String> getMinLadder(String start, String end) {

        start = start.toUpperCase();
        end = end.toUpperCase();

        if (start.equals(end)) {
            return new ArrayList<>(Arrays.asList(start));
        }

        if (!isWord(start) || !isWord(end) || start.length() != end.length()) {
            return empty;
        }

        HashSet<String> visited = new HashSet<>();
        List<List<String>> queue = new ArrayList<>();
        List<String> initial = new ArrayList<>();
        initial.add(start);
        queue.add(initial);

        while (!queue.isEmpty()) {
            List<String> ladder = queue.remove(0);
            String lastWord = ladder.get(ladder.size() - 1);

            if (lastWord.equals(end)) {
                return ladder;
            }

            visited.add(lastWord);
            List<String> neighbors = getNeighbors(lastWord);

            for (String neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    List<String> nextLadder = new ArrayList<>(ladder);
                    nextLadder.add(neighbor);
                    queue.add(nextLadder);
                }
            }
        }

        return empty;
    }
}