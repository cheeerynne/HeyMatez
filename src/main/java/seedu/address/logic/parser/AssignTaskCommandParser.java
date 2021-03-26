package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AssignTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

public class AssignTaskCommandParser implements Parser<AssignTaskCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AssignTaskCommand
     * and returns an AssignTask object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AssignTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MEMBER_NAME);

        Index index;
        Name memberName = null;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AssignTaskCommand.MESSAGE_USAGE), pe);
        }

        if (argMultimap.getValue(PREFIX_MEMBER_NAME).isPresent()) {
            memberName = ParserUtil.parseName(argMultimap.getValue(PREFIX_MEMBER_NAME).get());
        }

        return new AssignTaskCommand(index, memberName);
    }
}
