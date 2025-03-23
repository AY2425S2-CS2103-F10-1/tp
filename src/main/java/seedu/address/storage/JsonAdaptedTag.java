package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Project;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Tag}.
 */
class JsonAdaptedTag {

    private final String tagName;
    private final String tagType;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName} and {@code tagType}.
     * @param tagName name of the tag
     * @param tagType type of the tag
     */
    @JsonCreator
    JsonAdaptedTag(String tagName, String tagType) {
        this.tagName = tagName;
        this.tagType = tagType;
    }

    /**
     * Check given source and label tag type.
     */
    public JsonAdaptedTag(Tag source) {
        tagName = source.tagName;

        if (source instanceof Project) {
            tagType = "PROJ";
        } else {
            tagType = "TAG";
        }
    }

    @JsonValue
    public String getTagName() {
        return tagName;
    }

    public String getTagType() {
        return tagType;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Tag toModelType() throws IllegalValueException {
        if (!Tag.isValidTagName(tagName)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }

        if ("PROJ".equals(tagType)) {
            return new Project(tagName);
        }

        return new Tag(tagName);
    }

}
