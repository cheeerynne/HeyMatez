package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.task.Title;
import seedu.address.model.person.Name;

import java.util.HashMap;
import java.util.List;

/**
 * Jackson-friendly version of {@link Assignment}.
 */
class JsonAdaptedAssignment {
    private final String assignees;

    /**
     * Constructs a {@code JsonAdaptedAssignment} with the given {@code assignees}.
     */
    @JsonCreator
    public JsonAdaptedAssignment(String assignees) {
        this.assignees = assignees;
    }

    /**
     * Converts a given {@code Assignment} into this class for Jackson use.
     */
    public JsonAdaptedAssignment(Assignment assignments, Title taskTitle) {
        assignees = assignments.getAssignees(taskTitle);
    }

    /**
     * Converts this Jackson-friendly adapted assignment object into the model's {@code Assignment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted assignment.
     */
    public Assignment toModelType() throws IllegalValueException {
        HashMap<Title, List<Name>> assignments = new HashMap<>();

        return new Assignment(new HashMap<Title, List<Name>>());
    }
}
