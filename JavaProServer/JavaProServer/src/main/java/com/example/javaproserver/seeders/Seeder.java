package com.example.javaproserver.seeders;

import com.example.javaproserver.enums.InputTypeEnum;
import com.example.javaproserver.enums.UserRole;
import com.example.javaproserver.models.DataFromJson.ImageDecode;
import com.example.javaproserver.models.entities.Answer;
import com.example.javaproserver.models.entities.Question;
import com.example.javaproserver.models.entities.Quiz;
import com.example.javaproserver.models.entities.User;
import com.example.javaproserver.repositories.QuizRepository;
import com.example.javaproserver.repositories.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class Seeder implements ApplicationRunner {
    private final QuizRepository quizRepository;
    private final UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(quizRepository.count() == 0) {
            seedQuiz();
        }
        if(userRepository.count() == 0) {
            seedUsers();
        }
    }

    private void seedQuiz(){
        ImageDecode image = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            InputStream inputStream = new ClassPathResource("data/imageBase64.json").getInputStream();
            image = objectMapper.readValue(inputStream, ImageDecode.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Question> questions = Arrays.asList(
                new Question(
                        InputTypeEnum.CHECKBOX,
                        "W wyniku kompilacji kodu źródłowego Java otrzymano między innymi pliki Class$1.class, Class$2.class. Dlaczego w nazwach plików klasowych po znaku $ są liczby 1 i 2:",
                        null,
                        null,
                        Arrays.asList(
                                new Answer("Ponieważ klasa zawierała dwie klasy wewnętrzne o nazwach 1 i 2", false),
                                new Answer("Ponieważ klasa zawierała dwie anonimowe klasy wewnętrzne", true),
                                new Answer("Ponieważ klasa zawierała dwie nazwane klasy abstrakcyjne", false),
                                new Answer("Ponieważ klasa zawierała dwie nazwane klasy adaptacyjne", false)
                        )
                ),
                new Question(
                        InputTypeEnum.CHECKBOX,
                        "W języku Java rozkład komponentów (przycisków) dla biblioteki Swing w oknie aplikacji jak na rysunku niżej realizuje klasa:",
                        null,
                        Base64.getDecoder().decode(image.getCode()),
                        Arrays.asList(
                                new Answer("FlowLayout", false),
                                new Answer("GridLayout", true),
                                new Answer("BorderLayout", false),
                                new Answer("BoxLayout", false)
                        )
                ),
                new Question(
                        InputTypeEnum.CHECKBOX,
                        "Wykonanie poniższego programu spowoduje wyświetlenie:",
                        "import java.util.List; import java.util.Arrays;\n public class A {\n public static void main(String[] args) {\n List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);\n list.forEach((int n) -> System.out.print(\"List:\"+n));\n}}",
                        null,
                        Arrays.asList(
                                new Answer("List:1List:2List:3List:4List:5List:6", true),
                                new Answer("List:123456", false),
                                new Answer("error", false),
                                new Answer("List:{123456}", false)
                        )
                ),
                new Question(
                        InputTypeEnum.CHECKBOX,
                        "W języku Java w klasie serwletów javax.servlet.http.HttpServlet za obsługę żądań typu GET odpowiada metoda:",
                        null,
                        null,
                        Arrays.asList(
                                new Answer("processRequest()", false),
                                new Answer("doGet()", true),
                                new Answer("doPost()", false),
                                new Answer("Język Java nie obsługuje żądań typu GET", false)
                        )
                ),
                new Question(
                        InputTypeEnum.CHECKBOX,
                        "Wykonanie poniższego kodu języka Java powoduje wyświetlenie na konsoli:",
                        "class MyClass {\n" +
                                " public static void main(String args[]){\n" +
                                " String s1 = new String(\"Java\");\n" +
                                " String s2 = new String(\"Java\");\n" +
                                " if (s1==s2) System.out.print(\"A\");\n" +
                                " else System.out.print(\"B\");\n" +
                                " if (s1.equals(s2)) System.out.print(\"C\");\n" +
                                " else System.out.print(\"D\");\n" +
                                " }\n" +
                                "}",
                        null,
                        Arrays.asList(
                                new Answer("AC", false),
                                new Answer("BC", true),
                                new Answer("BD", false),
                                new Answer("AD", false)
                        )
                ),
                new Question(
                        InputTypeEnum.CHECKBOX,
                        "Wykonanie poniższego programu spowoduje wyświetlenie:",
                        "import java.util.List; import java.util.Arrays;\n public class A {\n public static void main(String[] args) {\n List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);\n list.forEach((int n) -> System.out.print(\"List:\"+n));\n}}",
                        null,
                        Arrays.asList(
                                new Answer("List:1List:2List:3List:4List:5List:6", true),
                                new Answer("List:123456", false),
                                new Answer("error", false),
                                new Answer("List:{123456}", false)
                        )
                ),
                new Question(
                        InputTypeEnum.CHECKBOX,
                        "Które zapisy referencji do metody i wyrażenia Lambda są równoważne?",
                        null,
                        null,
                        Arrays.asList(
                                new Answer("System.out::println\n" +
                                        "x->System.out.println()", true),
                                new Answer("JButton::new\n" +
                                        "() -> new JButton()", true),
                                new Answer("int[]::new\n" +
                                        "x->new int[x]", true),
                                new Answer("String::valueOf\n" +
                                        "x -> String.valueOf(x)", true)
                        )
                )
        );

        Quiz quiz = new Quiz(
                "Java Quiz",
                60,
                "Proszę zaznaczać tylko prawidłowe odpowiedzi",
                questions);

        quizRepository.save(quiz);
    }

    @Transactional
    protected void seedUsers(){
        String encryptedAdminPassword = new BCryptPasswordEncoder().encode("admin");
        String encryptedUserPassword = new BCryptPasswordEncoder().encode("169572");
        User newAdmin = new User(
                "admin",
                encryptedAdminPassword,
                UserRole.ADMIN,
                "admin",
                "admin",
                "admin"
        );

        User newUser = new User(
                "169572",
                encryptedUserPassword,
                UserRole.USER,
                "Mikołaj",
                "Kuszowski",
                "169572"
        );

        userRepository.save(newAdmin);
        userRepository.save(newUser);
    }
}
