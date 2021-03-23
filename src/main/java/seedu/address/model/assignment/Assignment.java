package seedu.address.model.assignment;

import seedu.address.model.person.Name;
import seedu.address.model.task.Title;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Represents the assignments between tasks and members.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Assignment {
    private static HashMap<Title, List<Name>> assignments;

    /**
     * Every field must be present and not null.
     */
    public Assignment(HashMap<Title, List<Name>> assignments) {
        this.assignments = assignments;
    }

    public void assignTask(Title taskTitle, Name memberName) {
        if (assignments.containsKey(taskTitle)) {
            assignments.get(taskTitle).add(memberName);
        } else {
            assignments.put(taskTitle, new ArrayList<Name>());
            assignments.get(taskTitle).add(memberName);
        }
    }

    public static List<Name> getAssigneesList(Title taskTitle) {
        return assignments.get(taskTitle);
    }

    public static String getAssignees(Title taskTitle) {
        List<Name> assigneeList = assignments.get(taskTitle);
        StringBuilder stringBuilder = new StringBuilder();

        if (assigneeList == null) {
            return "none";
        }

        for (Name currentName : assigneeList) {
            if (stringBuilder.length() == 0) {
                stringBuilder.append(currentName.toString());
            } else {
                stringBuilder.append(", ").append(currentName.toString());
            }
        }

        return stringBuilder.toString();
    }
}
