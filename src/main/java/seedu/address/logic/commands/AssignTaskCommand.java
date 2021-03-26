package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.task.*;

/**
 * Assigns a task to members.
 */
public class AssignTaskCommand extends Command {
    public static final String COMMAND_WORD = "assignTask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Assigns an existing task to the member specified "
            + "by the task index number shown in the displayed task board.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_MEMBER_NAME + " MEMBER_NAME " + "] "
            + "Example: " + COMMAND_WORD + " "
            + " 1 "
            + PREFIX_MEMBER_NAME + " Michelle Lee";

    public static final String MESSAGE_ASSIGN_TASK_SUCCESS = "Task Assigned: %1$s";
    public static final String MESSAGE_MEMBER_ALREADY_ASSIGNED = "Member specified is already assigned to the task! ";

    private final Index index;
    private final Name name;

    /**
     * @param index of the task in the filtered task list to assign to member
     * @param name of the member to assign the task to
     */
    public AssignTaskCommand(Index index, Name name) {
        requireNonNull(index);
        requireNonNull(name);

        this.index = index;
        this.name = name;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownTaskList = model.getFilteredTaskList();
        List<Person> lastShownMemberList = model.getFilteredPersonList();
        Name memberName = null;

        if (index.getZeroBased() >= lastShownTaskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToAssign = lastShownTaskList.get(index.getZeroBased());

        for (Person person : lastShownMemberList) {
            Name currentName = person.getName();

            if (name.equals(currentName)) {
                memberName = currentName;
                break;
            }
        }

        if (memberName == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_NAME);
        }

        Task assignedTask = createAssignedTask(taskToAssign);

        model.setTask(taskToAssign, assignedTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_ASSIGN_TASK_SUCCESS, taskToAssign));
    }

    private Task createAssignedTask(Task taskToAssign) throws CommandException {
        assert taskToAssign != null;

        Title title = taskToAssign.getTitle();
        Description description = taskToAssign.getDescription();
        Deadline deadline = taskToAssign.getDeadline();
        TaskStatus taskStatus = taskToAssign.getTaskStatus();
        Priority priority = taskToAssign.getPriority();

        List<Name> currentAssignees = taskToAssign.getAssignees().assigneesList;

        for (Name currentName : currentAssignees) {
            if (name.equals(currentName)) {
                throw new CommandException(MESSAGE_MEMBER_ALREADY_ASSIGNED);
            }
        }

        taskToAssign.getAssignees().assigneesList.add(name);
        Assignees updatedAssignees = taskToAssign.getAssignees();

        return new Task(title, description, deadline, taskStatus, priority, updatedAssignees);

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditTaskCommand)) {
            return false;
        }

        // state check
        AssignTaskCommand e = (AssignTaskCommand) other;
        System.out.println(index);
        System.out.println(e.index);
        return index.equals(e.index);
    }
}
