package io.github.zektorum.command;

import io.github.zektorum.data.PeopleCollection;

public class RemoveKeyCommand extends ParentCommand {
    public RemoveKeyCommand() {
        super(
                "remove_key",
                "remove_key key",
                "удалить элемент из коллекции по его ключу"
        );
    }

    @Override
    public void execute(PeopleCollection peopleCollection, String arg1, String arg2, String arg3) {
        try {
            Integer.parseInt(arg1);
        } catch (NumberFormatException e) {
            System.out.println("Некорректные аргументы!\n");
            return;
        }
        peopleCollection.getPeopleCollection().remove(Integer.parseInt(arg1));
        System.out.println();
    }
}