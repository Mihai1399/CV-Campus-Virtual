package org.loose.fis.registration.example.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.loose.fis.registration.example.exceptions.CouldNotWriteUsersException;
import org.loose.fis.registration.example.exceptions.EmailAlreadyExistsException;

import org.loose.fis.registration.example.exceptions.UserNameOrPasswordAreIncorrect;
import org.loose.fis.registration.example.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.registration.example.model.User;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserService {

    private static List<User> users=new ArrayList<User>();
    private static final Path USERS_PATH = Paths.get(System.getProperty("user.dir").toString()+"\\src\\main\\resources\\users.json");
    private static String Mail;

    public static void loadUsersFromFile() throws IOException {

        if (!Files.exists(USERS_PATH)) {

            FileUtils.copyURLToFile(UserService.class.getClassLoader().getResource("users.json"), USERS_PATH.toFile());
        }

        if(Files.readAllLines(USERS_PATH).isEmpty())
            return;

        ObjectMapper objectMapper = new ObjectMapper();

        users = objectMapper.readValue(USERS_PATH.toFile(), new TypeReference<List<User>>() {
        });
    }
    public static void addUser(String username, String password, String role) throws UsernameAlreadyExistsException, EmailAlreadyExistsException {
        checkUserDoesNotAlreadyExist(username);
        checkEmailDoesNotAlreadyExistsException(Mail);
        users.add(new User(username, encodePassword(username, password), role,FullName,Adress,PhoneNumber,Mail));
        persistUsers();
    }

    public static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        if(users.isEmpty())
            return;
        for (User user : users) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }
    private static void checkEmailDoesNotAlreadyExistsException(String Email) throws EmailAlreadyExistsException {
        if(users.isEmpty())
            return;
        for (User user : users) {
            if (Email.equals(user.getMail()))
                throw new EmailAlreadyExistsException(Email);
        }
    }


    private static void persistUsers() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(USERS_PATH.toFile(), users);
        } catch (IOException e) {
            throw new CouldNotWriteUsersException();
        }
    }

    public static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }

    public static List<User> getUsers() {
        return users;
    }
    public static void checkIfUserNameOrPasswordAreCorrect(String username,String password) throws UserNameOrPasswordAreIncorrect {
        boolean ok=true;
        for (User user : users) {
            if (username.equals(user.getUsername())&&encodePassword(username, password).equals(user.getPassword()))
                ok=false;
        }
        if(ok==true){
            throw new UserNameOrPasswordAreIncorrect(username, password);
        }
    }
}
