import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInfoApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(
                "Введите (Фамилия Имя Отчество датарождения номертелефона пол):");
        String userData = scanner.nextLine();

        try {
            UserInfo userInfo = parseUserData(userData);
            saveToFile(userInfo);
            System.out.println("Данные успешно сохранены в файл.");
        } catch (IllegalArgumentException | InputMismatchException | IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static UserInfo parseUserData(String userData) {
        String[] dataParts = userData.split("\\s+");
        if (dataParts.length != 6) {
            throw new IllegalArgumentException(
                    "Неверное количество данных. Должно быть 6 ");
        }

        String lastName = dataParts[0];
        String firstName = dataParts[1];
        String middleName = dataParts[2];
        String birthDate = dataParts[3];
        long phoneNumber = Long.parseLong(dataParts[4]);
        char gender = dataParts[5].charAt(0);

        return new UserInfo(lastName, firstName, middleName, birthDate, phoneNumber, gender);
    }

    private static void saveToFile(UserInfo userInfo) throws IOException {
        String fileName = userInfo.getLastName() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(userInfo.toString() + "\n");
        }
    }
}

class UserInfo {
    private String lastName;
    private String firstName;
    private String middleName;
    private String birthDate;
    private long phoneNumber;
    private char gender;

    public UserInfo(String lastName, String firstName, String middleName, String birthDate, long phoneNumber,
            char gender) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return lastName + firstName + middleName + birthDate + " " + phoneNumber + gender;
    }
}
