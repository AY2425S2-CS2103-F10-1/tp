package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    public static final String MESSAGE_CONSTRAINTS = "Tag names should be alphanumeric with underscore and hyphens. "
            + "allowed, and be between 1 and 20 characters long.";
    public static final String VALIDATION_REGEX = "[a-zA-Z0-9_-]{1,20}";
    public final String tagName;
    private Boolean tagPaid;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public Tag(String tagName) {
        requireNonNull(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        this.tagName = tagName;
        this.tagPaid = false;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if tag has tagPaid attribute of true, returns false otherwise
     */
    public boolean isPaid() {
        return this.tagPaid;
    }

    /**
     * Set the tagPaid attribute to true, to reflect successful payment
     */
    public void pay() {
        this.tagPaid = true;
    }

    @Override
    public boolean equals(Object other) {
        //checks if the object is the same
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Tag)) {
            return false;
        }
        Tag otherTag = (Tag) other;
        return tagName.equalsIgnoreCase(otherTag.tagName);
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + tagName + ']';
    }

}
