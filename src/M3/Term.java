package M3;

import java.util.Comparator;

/**
 * Autocomplete term representing a (query, weight) pair.
 * 
 */
public class Term implements Comparable<Term> {

    private String query;
    private long weight;

    /**
     * Initialize a term with the given query and weight.
     * This method throws a NullPointerException if query is null,
     * and an IllegalArgumentException if weight is negative.
     */
    public Term(String query, long weight) {
        if (query == null){
            throw new NullPointerException("query is null");
        }
        if (weight < 0){
            throw new IllegalArgumentException("weight is negative");
        }

        this.query = query;
        this.weight = weight;
    }

    /**
     * Compares the two terms in descending order of weight.
     */
    public static Comparator<Term> byDescendingWeightOrder() {

        return null;
    }

    /**
     * Compares the two terms in ascending lexicographic order of query,
     * but using only the first length characters of query. This method
     * throws an IllegalArgumentException if length is less than or equal
     * to zero.
     */
    public static Comparator<Term> byPrefixOrder(int length) {

        if (length < 0) {
            throw new IllegalArgumentException("length is null");
        }
        return new Comparator<Term>() {
            @Override
            public int compare(Term t1, Term t2) {

                int tlength = Math.min(t2.query.length(), Math.min(t1.query.length(), length));

                String s1 = t1.query.substring(0, tlength);
                String s2 = t2.query.substring(0, tlength);

                return s1.compareTo(s2);

            }

        };


    }

    /**
     * Compares this term with the other term in ascending lexicographic order
     * of query.
     */
    @Override
    public int compareTo(Term other) {
        return query.compareTo(other.query);
    }

    /**
     * Returns a string representation of this term in the following format:
     * query followed by a tab followed by weight
     */
    @Override
    public String toString(){
        return weight + "\t" + query;
    }

}

