package seedu.address.logic.parser;

/**
 * A prefix that marks the beginning of an argument in an arguments string.
 * E.g. 't/' in 'add James t/ friend'.
 */
public record Prefix(String prefix) {

    @Override
    public String toString() {
        return prefix();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof Prefix otherPrefix) {
            return prefix.equals(otherPrefix.prefix);
        }
        return false;
    }
}
