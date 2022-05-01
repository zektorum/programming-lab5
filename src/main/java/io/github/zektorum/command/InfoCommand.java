package io.github.zektorum.command;

import io.github.zektorum.data.PeopleCollection;

public class InfoCommand implements Command {
    public void execute(PeopleCollection peopleCollection, String arg) {
        peopleCollection.info();
    }
}
