package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Removes Tag(s) from a person in the address book.
 */
public class UnTagCommand extends Command {
    public static final String COMMAND_WORD = "untag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Remove tags or projects from a contact. "
            + "Parameters: "
            + PREFIX_PHONE + "PHONE "
            + "[" + PREFIX_TAG + "TAG]..."
            + "[" + PREFIX_PROJECT + "PROJECT]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_TAG + "project-x";

    public static final String MESSAGE_SUCCESS = "Tags and/or projects removed from %1$s";
    private final Phone phone;
    private final Set<Tag> tags;

    /**
     * @param phone number of the person in the filtered person list to edit
     * @param tags to remove
     */
    public UnTagCommand(Phone phone, Set<Tag> tags) {
        requireNonNull(phone);
        this.phone = phone;
        this.tags = tags;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person personToUnTag = model.getFilteredPersonList()
                .stream()
                .filter(x -> x.getPhone().equals(phone))
                .findFirst()
                .orElseThrow(() -> new CommandException(Messages.MESSAGE_ABSENT_PHONE_NUMBER));

        Set<Tag> checktags = new LinkedHashSet<>(personToUnTag.getTags());
        checktags.addAll(personToUnTag.getProjects());
        String check = checkForTagInExistingTags(checktags, this.tags);
        if (!check.equals("")) {
            throw new CommandException(String.format(Messages.MESSAGE_ABSENT_TAG_PROJECT,
                    check, personToUnTag.getName(), personToUnTag.getPhone()));
        }

        Person taggedPerson = unTagProjectFromPerson(personToUnTag, this.tags);

        model.setPerson(personToUnTag, taggedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, taggedPerson.getName()));
    }

    /**
     * Create an edited person with the refreshed tag set
     * @param personToEdit current person to edit
     * @param tagsToRemove tags to be removed
     */
    public static Person unTagProjectFromPerson(Person personToEdit, Set<Tag> tagsToRemove) {
        assert personToEdit != null;

        Name name = personToEdit.getName();
        Phone phone = personToEdit.getPhone();
        Set<Tag> currentTags = personToEdit.getTags();
        Set<Tag> currentProjects = personToEdit.getProjects();
        Optional<Email> email = personToEdit.getEmail();

        // Remove tagsToRemove from current Tags
        Set<Tag> newTags = new LinkedHashSet<>(currentTags);
        newTags.addAll(currentProjects);
        newTags.removeAll(tagsToRemove);

        // Return new Person
        return new Person(name, phone, email, newTags);
    }

    /**
     * Checks if tagsToCheck exists within the Set of Tags.
     * Returns true if found within, false otherwise.
     * @param tags set
     * @param tagsToCheck set of tags to check for
     */
    public static String checkForTagInExistingTags(Set<Tag> tags, Set<Tag> tagsToCheck) {
        for (Tag t : tagsToCheck) {
            if (!(tags.contains(t))) {
                return t.getTagName();
            }
        }
        return "";
    }
}
