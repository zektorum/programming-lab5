package io.github.zektorum.command;

import io.github.zektorum.data.collection.PeopleCollection;

/**
 * Реализация команды average_of_height.
 */
public class AverageOfHeightCommand extends BaseCommand {
    public AverageOfHeightCommand() {
        super(
                "average_of_height",
                "average_of_height",
                "вывести среднее значение поля height для всех элементов коллекции"
        );
    }

    public void execute(PeopleCollection peopleCollection, String arg1, String arg2, String arg3) {
        if (!(arg1.equals("") && arg2.equals("") && arg3.equals(""))) {
            System.out.println("Некорректные аргументы!");
            return;
        }
        System.out.printf("%.4f\n\n", peopleCollection.averageOfHeight());
    }
}
