package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;

/**
 * Assigns a task to members.
 */
public class AssignTaskCommand extends Command {

    public static final String COMMAND_WORD = "assignTask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Assigns an existing task to the member specified "
            + "by the task title shown in the task board.\n"
            + "Parameters: TASK_TITLE "
            + "[" + PREFIX_MEMBER_NAME + " MEMBER_NAME " + "] "
            + "Example: " + COMMAND_WORD + " "
            + " Script Writing "
            + PREFIX_MEMBER_NAME + " Tammy Lim";

    public static final String MESSAGE_ASSIGN_TASK_SUCCESS = "Task Assigned: %1$s";
    public static final String MESSAGE_MEMBER_ALREADY_ASSIGNED = "Member specified is already assigned to the task! ";

    private final Title taskTitle;
    private final Name name;

    /**
     * @param taskTitle of the task in the filtered task list to assign to member
     * @param name of the member to assign the task to
     */
    public AssignTaskCommand(Title taskTitle, Name name) {
        requireNonNull(taskTitle);
        requireNonNull(name);

        this.taskTitle = taskTitle;
        this.name = name;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownTaskList = model.getFilteredTaskList();
        List<Person> lastShownMemberList = model.getFilteredPersonList();
        Task taskToAssign = null;
        Name memberName = null;

        for (Task task : lastShownTaskList) {
            Title currentTaskTitle = task.getTitle();

            if (taskTitle.equals(currentTaskTitle)) {
                taskToAssign = task;
            }
        }

        if (taskToAssign == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_TITLE);
        }

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

        Title taskTitle = taskToAssign.getTitle();

        model.assignTask(taskTitle, memberName);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_ASSIGN_TASK_SUCCESS, taskToAssign));
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
        System.out.println(taskTitle);
        System.out.println(e.taskTitle);
        return taskTitle.equals(e.taskTitle);
    }
}
