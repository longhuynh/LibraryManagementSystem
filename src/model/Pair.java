package model;

import java.io.Serializable;

public final class Pair<S, T> implements Serializable {
	private static final long serialVersionUID = 1L;
	S first;
	T second;

	public Pair(S s, T t) {
		first = s;
		second = t;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (obj.getClass() != getClass())
			return false;
		@SuppressWarnings("unchecked")
		Pair<S, T> p = (Pair<S, T>) obj;
		return p.first.equals(first) && p.second.equals(second);
	}

	@Override
	public int hashCode() {
		return first.hashCode() + 5 * second.hashCode();
	}

	@Override
	public String toString() {
		return "(" + first.toString() + ", " + second.toString() + ")";
	}

}