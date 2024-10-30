package data_providers;

import dto.UserDto;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderUser {

    @DataProvider
    public UserDto[] loginTestDataProvider() {
        UserDto userDto1 = UserDto.builder()
                .email("mail@gamail.com")
                .password("Qwerty123!")
                .build();
        UserDto userDto2 = UserDto.builder()
                .email("mail2@gamail.com")
                .password("Qwerty123!")
                .build();
        UserDto userDto3 = UserDto.builder()
                .email("mail3@gamail.com")
                .password("Qwerty123!")
                .build();
        return new UserDto[]{userDto1, userDto2, userDto3};
    }

    @DataProvider
    public Iterator<UserDto> loginTestDataProviderFromCswFile() {
        List<UserDto> userList = new ArrayList<>();
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader("src/test/resources/data/list_of_user.csv"));
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] splitArray = line.split(",");
                userList.add(UserDto.builder()
                        .email(splitArray[0])
                        .password(splitArray[1])
                        .build());
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return userList.listIterator();
    }
}
