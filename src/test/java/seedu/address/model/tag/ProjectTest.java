package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProjectTest {

    private Project validProject;
    private Project validProjectTwo;
    @BeforeEach
    public void setUp() {
        validProject = new Project("valid_project", "complete", "paid", "01 apr 2025 2359");
        validProjectTwo = new Project("valid_project_two");
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Project(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidProjectName = "";
        assertThrows(IllegalArgumentException.class, () -> new Project(invalidProjectName));
    }

    @Test
    public void test_getProjectTagName() {
        assertEquals(validProject.getTagName(), "valid_project");
        assertEquals(validProjectTwo.getTagName(), "valid_project_two");
        assertNotEquals(validProject.getTagName(), "valid-project");
        assertNotEquals(validProjectTwo.getTagName(), "valid-project");
    }

    @Test
    public void test_getProjectProgressString() {
        assertEquals(validProject.getProgressString(), "Complete");
        assertNotEquals(validProject.getProgressString(), "Incomplete");
    }



    @Test
    public void test_isValidProjectTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Project.isValidTagName(null));

        //more than 20 characters
        assertFalse(Project.isValidTagName("aaaaaaaaaaaaaaaaaaaaa"));

        //less than 1 character
        assertFalse(Project.isValidTagName(""));

        //hyphens and underscores allowed
        assertTrue(Project.isValidTagName("_-"));

        //other special characters not allowed
        assertFalse(Project.isValidTagName("*!@#$%^&*"));
    }
}
