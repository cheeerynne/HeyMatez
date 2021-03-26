package seedu.address.model.task;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;

import java.util.ArrayList;
import java.util.List;


public class Assignees {
    public static final String MESSAGE_CONSTRAINTS =
            "Assignees should only contain names of members in the displayed member list.";

    public List<Name> assigneesList;

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
     * @param listOfAssignees A list of assignees' name to be added to the assignee's list.
     */
    public Assignees(String listOfAssignees) throws ParseException {
        this.assigneesList = new ArrayList<>();
        String[] assigneesArray = listOfAssignees.split(", ");

        for (String strName : assigneesArray) {
            Name name = ParserUtil.parseName(strName);
            this.assigneesList.add(name);
        }
    }

    public String getAssigneesString() {
        StringBuilder stringBuilder = new StringBuilder("");

        if (assigneesList.size() == 0) {
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

//    public List<Name> getAssigneesList() {
//        return assigneesList;
//    }

    @Override
    public String toString() {
        return this.getAssigneesString();
    }

//    public static boolean isValidAssignee(String assigneesList, Model model) {
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
}
