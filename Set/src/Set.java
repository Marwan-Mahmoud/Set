import java.util.Iterator;
import java.util.LinkedList;

public class Set<T> implements Iterable<T> {
	private LinkedList<T> set;

	public Set() {
		set = new LinkedList<>();
	}

	/**
	 * Adds the specified element to this set if it is not already present.
	 * 
	 * @return {@code true} if this set did not already contain the specified
	 *         element
	 */
	public boolean add(T element) {
		if (contains(element))
			return false;

		set.add(element);
		return true;
	}

	/**
	 * Returns {@code true} if this set contains the specified element.
	 */
	public boolean contains(T element) {
		return set.contains(element);
	}

	/**
	 * Removes the specified element from this set if it is present.
	 * 
	 * @return {@code true} if this set contained the specified element
	 */
	public boolean remove(T element) {
		if (contains(element))
			return false;

		set.remove(element);
		return true;
	}

	/**
	 * Returns {@code true} if this set is subset of the specified set.
	 */
	public boolean isSubset(Set<T> set) {
		for (T element : this) {
			if (!set.contains(element))
				return false;
		}
		return true;
	}

	/**
	 * Returns the union of the two sets.
	 */
	public Set<T> union(Set<T> set) {
		Set<T> union = new Set<>();
		for (T element : this) {
			union.add(element);
		}
		for (T element : set) {
			union.add(element);
		}
		return union;
	}

	/**
	 * Returns the intersection of the two sets.
	 */
	public Set<T> intersection(Set<T> set) {
		Set<T> intersection = new Set<>();
		for (T element : this) {
			if (set.contains(element))
				intersection.add(element);
		}
		return intersection;
	}

	/**
	 * Returns the complement of the this set.
	 */
	public Set<T> complement(Set<T> universe) {
		Set<T> complement = new Set<>();
		for (T element : universe) {
			if (!this.contains(element))
				complement.add(element);
		}
		return complement;
	}

	/**
	 * Returns the difference of the two sets.
	 */
	public Set<T> difference(Set<T> set) {
		Set<T> difference = new Set<>();
		for (T element : this) {
			if (!set.contains(element))
				difference.add(element);
		}
		return difference;
	}

	@Override
	public Iterator<T> iterator() {
		return set.iterator();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("{");
		if (set.size() > 0) {
			for (T element : this)
				builder.append(element + ", ");
			builder.delete(builder.length() - 2, builder.length());
		}
		builder.append("}");
		return builder.toString();
	}
}
