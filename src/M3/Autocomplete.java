package M3;

import java.util.Arrays;


/**
 * Autocomplete.
 */
public class Autocomplete {

	private Term[] terms;

	/**
	 * Initializes a data structure from the given array of terms.
	 * This method throws a NullPointerException if terms is null.
	 */
	public Autocomplete(Term[] terms) {

		//throw
		if (terms == null) {
			throw new NullPointerException("null");
		}
		//check each
		for (Term i : terms) {

			if (i == null) {
				throw new NullPointerException("None");
			}
		}

		this.terms = terms;

		//sort
		Arrays.sort(this.terms);
    }

	/**
	 * Returns all terms that start with the given prefix, in descending order of weight.
	 * This method throws a NullPointerException if prefix is null.
	 */
	public Term[] allMatches(String prefix) {

		//throw
		if (prefix == null) {
			throw new NullPointerException("null");
		}

		int start = BinarySearch.firstIndexOf(terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
		int end = BinarySearch.lastIndexOf(terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));

		if(start == -1 || end == -1) {

			return new Term[0];
		}

		Term[] matches = Arrays.copyOfRange(terms, start, end + 1);

		return matches;
    }

}

