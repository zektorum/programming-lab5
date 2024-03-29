package io.github.zektorum.data.person.creation;

import io.github.zektorum.core.Interpreter;
import io.github.zektorum.data.person.Person;
import io.github.zektorum.data.person.PersonFieldsChecker;
import io.github.zektorum.data.person.fields.Color;
import io.github.zektorum.data.person.fields.Country;
import io.github.zektorum.data.person.fields.Location;
import io.github.zektorum.data.person.fields.LocationReader;
import io.github.zektorum.data.person.fields.Coordinates;
import io.github.zektorum.data.person.fields.CoordinatesReader;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Схема создания объекта Person на основе пользовательского ввода.
 */
public class PersonBuilderFromUserInput implements PersonBuilder {
    private String name;
    private Coordinates coordinates;
    private int height;
    private Color.EyeColor eyeColor;
    private Color.HairColor hairColor;
    private Country nationality;
    private Location location;

    public PersonBuilder withName() {
        Scanner userInput = new Scanner(System.in);
        String data = "";
        while (true) {
            System.out.println("Введите имя: ");
            Interpreter.checkInput(userInput);
            data = userInput.nextLine();
            if (data.equals("")) {
                System.out.println("Некорректное значение! Строка должна быть непуста.");
            } else {
                this.name = data;
                break;
            }
        }
        return this;
    }

    public PersonBuilder withCoordinates() {
        CoordinatesReader coordinatesReader = new CoordinatesReader();
        this.coordinates = coordinatesReader.read();
        return this;
    }

    public PersonBuilder withHeight() {
        Scanner userInput = new Scanner(System.in);
        int height;
        while (true) {
            System.out.print("Введите рост: ");
            try {
                Interpreter.checkInput(userInput);
                height = userInput.nextInt();
                if (height < 1) {
                    System.out.println("Некорректное значение! Число должно быть положительным.");
                    userInput.nextLine();
                    continue;
                } else {
                    this.height = height;
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Некорректное значение! Число должно быть целым и положительным.");
                Interpreter.checkInput(userInput);
                userInput.nextLine();
                continue;
            }
        }
        return this;
    }

    public PersonBuilder withEyeColor() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Введите цвет глаз.\nДоступные варианты: RED, BLACK, BLUE, ORANGE, BROWN");
        Interpreter.checkInput(userInput);
        String eyeColor = userInput.next().toUpperCase();
        while (true) {
            try {
                this.eyeColor = Color.EyeColor.valueOf(eyeColor);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(
                        "Некорректный ввод! Повторите попытку\nДоступные варианты: RED, BLACK, BLUE, ORANGE, BROWN"
                );
                Interpreter.checkInput(userInput);
                userInput.nextLine();
                Interpreter.checkInput(userInput);
                eyeColor = userInput.next().toUpperCase();
            }
        }
        return this;
    }

    public PersonBuilder withHairColor() {
        Scanner userInput = new Scanner(System.in);
        System.out.print("Введите цвет волос.\nДоступные варианты: GREEN, BLUE, WHITE\n");
        Interpreter.checkInput(userInput);
        String hairColor = userInput.next().toUpperCase();
        while (true) {
            try {
                this.hairColor = Color.HairColor.valueOf(hairColor);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Некорректный ввод! Повторите попытку\nДоступные варианты: GREEN, BLUE, WHITE");
                Interpreter.checkInput(userInput);
                userInput.nextLine();
                Interpreter.checkInput(userInput);
                hairColor = userInput.next().toUpperCase();
            }
        }
        return this;
    }

    public PersonBuilder withNationality() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Введите национальность.\nДоступные варианты: UNITED_KINGDOM, USA, ITALY, SOUTH_KOREA, JAPAN");
        Interpreter.checkInput(userInput);
        String nationality = userInput.next().toUpperCase();
        while (true) {
            try {
                this.nationality = Country.valueOf(nationality);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Некорректный ввод! Повторите попытку");
                System.out.println("Доступные варианты: UNITED_KINGDOM, USA, ITALY, SOUTH_KOREA, JAPAN");
                Interpreter.checkInput(userInput);
                userInput.nextLine();
                Interpreter.checkInput(userInput);
                nationality = userInput.next().toUpperCase();
            }
        }
        return this;
    }

    public PersonBuilder withLocation() {
        LocationReader locationReader = new LocationReader();
        this.location = locationReader.read();
        return this;
    }

    public Person build() {
        Person person = new Person(name, height, location, coordinates, eyeColor, hairColor, nationality);
        if (PersonFieldsChecker.isValidPerson(person)) {
            return person;
        } else {
            return null;
        }
    }
}
