package seedu.address.model.task;

import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Assignees {
    public static final String MESSAGE_CONSTRAINTS =
            "Assignees should only contain names of members in the displayed member list.";

    public static List<Name> assigneesList;

    //C1: empty list
    //C2: constructor(List<Name> toAdd)
    // add method
    // remove method (change assignee)
    // personAlreadyExist(Name name)
    // isValidAssigneeList

    // Command Parser
    //  1. Check if task is valid (exists)
    //  2. Check if person is valid

    /**
     * Constructs an empty list of {@code Assignees}.
     */
    public Assignees() {
        this.assigneesList = new ArrayList<>();
    }

    /**
     * Constructs a list of {@code Assignees}.
     *
     * @param assigneesList A list of assignees' name to be added to the assignee's list.
     */
    public Assignees(List<Name> assigneesList, Model model) {
        checkArgument(isValidAssigneeList(assigneesList, model), MESSAGE_CONSTRAINTS);
        this.assigneesList = assigneesList;
    }

    public addAssignees(String assigneesList) {

    }

    public static boolean isValidAssigneeList(String assigneesList, Model model) {
        List<Person> lastShownMemberList = model.getFilteredPersonList();

        for (Name assigneeName : assigneesList) {
            for (Person person : lastShownMemberList) {
                Name currentName = person.getName();

                if (assigneeName.equals(currentName)) {
                    return true;
                }
            }
        }

        return false;
    }

//    public static boolean isValidAssigneeList(List<Name> assigneesList, Model model) {
//        List<Person> lastShownMemberList = model.getFilteredPersonList();
//
//        for (Name assigneeName : assigneesList) {
//            for (Person person : lastShownMemberList) {
//                Name currentName = person.getName();
//
//                if (assigneeName.equals(currentName)) {
//                    return true;
//                }
//            }
//        }
//
//        return false;
//    }

//    public static boolean isValidAssignee(Model model) {
//        List<Person> lastShownMemberList = model.getFilteredPersonList();
//
//        for (Name assigneeName : assigneesList) {
//            for (Person person : lastShownMemberList) {
//                Name currentName = person.getName();
//
//                if (assigneeName.equals(currentName)) {
//                    return true;
//                }
//            }
//        }
//
//        return false;
//    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        if (assigneesList == null) {
            return "none";
        }

        for (Name currentName : assigneesList) {
            if (stringBuilder.length() == 0) {
                stringBuilder.append(currentName.toString());
            } else {
                stringBuilder.append(", ").append(currentName.toString());
            }
        }

        return stringBuilder.toString();
    }
}
