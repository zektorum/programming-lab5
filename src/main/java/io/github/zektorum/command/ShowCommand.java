package io.github.zektorum.command;

import io.github.zektorum.data.PeopleCollection;

public class ShowCommand extends BaseCommand {
    public ShowCommand() {
        super(
                "show",
                "show",
                "вывести в стандартный поток вывода все элементы коллекции в строковом представлении"
        );
    }

    public void execute(PeopleCollection peopleCollection, String arg1, String arg2, String arg3) {
        peopleCollection.showAll();
    }
}
