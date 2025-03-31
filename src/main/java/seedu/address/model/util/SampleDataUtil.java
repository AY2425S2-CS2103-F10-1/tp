package seedu.address.model.util;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Project;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"),
                    Optional.of(new Email("alexyeoh@example.com")),
                getTagSet("friends"), new LinkedHashSet<>()),
            new Person(new Name("Bernice Yu"), new Phone("99272758"),
                    Optional.empty(),
                getTagSet("colleagues", "friends"), new LinkedHashSet<>()),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                    Optional.of(new Email("charlotte@example.com")),
                getTagSet("neighbours"), new LinkedHashSet<>()),
            new Person(new Name("David Li"), new Phone("91031282"),
                    Optional.of(new Email("lidavid@example.com")),
                getTagSet("family"), new LinkedHashSet<>()),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"),
                    Optional.of(new Email("irfan@example.com")),
                getTagSet("classmates"), new LinkedHashSet<>()),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"),
                    Optional.of(new Email("royb@example.com")),
                getTagSet("colleagues"), new LinkedHashSet<>())
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a project set containing the list of strings given.
     */
    public static Set<Project> getProjectSet(String... strings) {
        return Arrays.stream(strings)
                .map(Project::new)
                .collect(Collectors.toSet());
    }

}
