package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProjectTest {

    private Project validProject;
    private Project validProjectTwo;
    @BeforeEach
    public void setUp() {
        validProject = new Project("valid_project", "complete", "paid", "01 apr 2025 2359");
        validProjectTwo = new Project("valid_project_two"); // isComplete: false, isPaid: false
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
    public void test_getProjectProgress() {
        assertEquals(validProject.getProgress(), true);
        assertEquals(validProjectTwo.getProgress(), false);
    }

    @Test
    public void test_setProjectProgress() {
        Project testProject = new Project("test"); // isComplete: false
        assertEquals(testProject.getProgress(), false);
        testProject.setProgress(true);
        assertEquals(testProject.getProgress(), true);
    }

    @Test
    public void test_getProjectPaymentString() {
        assertEquals(validProject.getPaymentString(), "Paid");
        assertNotEquals(validProject.getPaymentString(), "Unpaid");
    }

    @Test
    public void test_getProjectPayment() {
        assertEquals(validProject.getPayment(), true);
        assertEquals(validProjectTwo.getPayment(), false);
    }

    @Test
    public void test_setProjectPayment() {
        Project testProject = new Project("test"); // isPaid: false
        assertEquals(testProject.getPayment(), false);
        testProject.setPayment(true);
        assertEquals(testProject.getPayment(), true);
    }

    @Test
    public void test_getProjectDeadlineString() {
        assertEquals(validProject.getDeadlineString(), "01 Apr 2025 2359");
    }

    @Test
    public void test_getProjectDeadline() {
        LocalDateTime deadline = Project.dateTimeStringToLocalDateTime("01 Apr 2025 2359");
        assertEquals(validProject.getDeadline(), deadline);
    }

    @Test
    public void test_setProjectDeadline() {
        Project testProject = new Project("test");
        assertNotEquals(testProject.getDeadlineString(), "01 Jan 1970 0001");
        testProject.setDeadline(Project.dateTimeStringToLocalDateTime("01 Jan 1970 0001"));
        assertEquals(testProject.getDeadlineString(), "01 Jan 1970 0001");
    }

    @Test
    public void test_toString() {
        String expectedString = '[' + validProject.getTagName()
                + " | Deadline: " + validProject.getDeadlineString() + "H | "
                + validProject.getProgressString()
                + " | " + validProject.getPaymentString() + ']';
        assertEquals(validProject.toString(), expectedString);
        assertNotEquals(validProject.toString(), validProjectTwo.toString());
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
