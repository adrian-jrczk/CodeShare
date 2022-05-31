package codeshare.controller;

import codeshare.code.Code;
import codeshare.code.CodeRepository;
import codeshare.code.CodeValidator;
import codeshare.user.User;
import codeshare.user.UserRepository;
import codeshare.user.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;


@Service
class AppService {

    @Autowired
    private CodeRepository codeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    Response updateCode(Code code, String principalName) {
        if (!CodeValidator.isValid(code)) {
            return new Response().setResult("Failure!").setMessage(CodeValidator.REQUIREMENTS);
        }
        Optional<Code> databaseCode = codeRepository.findById(code.getUuid());
        if (databaseCode.isEmpty()) {
            return new Response().setResult("Failure!").setMessage("Could not find given code.");
        }
        Code modified = databaseCode.get();
        if (!modified.isEditable() && !modified.getCreator().getName().equals(principalName)) {
            return new Response().setResult("Failure!").setMessage("You cannot edit this code.");
        }
        modified.setStringValue(code.getStringValue());
        modified.setLastModified(LocalDateTime.now());
        codeRepository.save(modified);
        return new Response().setResult("Success!").setMessage("This code was successfully updated.");
    }

    @Transactional
    Response getCode(String uuid, String principalName) {
        Optional<Code> databaseCode = codeRepository.findById(UUID.fromString(uuid));
        if (databaseCode.isEmpty()) {
            return new Response().setResult("Failure!").setMessage("Could not find code with given uuid");
        }
        Code code = databaseCode.get();
        if (code.isSetAsPrivate() && !code.getCreator().getName().equals(principalName)) {
            return new Response().setResult("Failure!").setMessage("You cannot access this code.");
        }
        if (code.isViewRestricted()) {
            if (code.getViewsAllowed() < 1) {
                codeRepository.delete(code);
                return new Response().setResult("Failure!").setMessage("This code has no more views allowed");
            }
            code.setViewsAllowed(code.getViewsAllowed() - 1);
        }
        if (code.isTimeRestricted()) {
            code.setMinutesAllowed(code.getMinutesAllowed() - Duration.between(code.getCreatedAt(), LocalDateTime.now()).toMinutes());
            if (code.getMinutesAllowed() < 1) {
                codeRepository.delete(code);
                return new Response().setResult("Failure!").setMessage("This code is expired");
            }
        }
        codeRepository.save(code);
        return new Response().setResult("Success!").setCode(code);
    }

    @Transactional
    Page<Code> getUserCodePaginated(int pageNum, int pageSize, String principalName) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return codeRepository.findAllByCreatorName(principalName, pageable);
    }

    @Transactional
    Map<String, String> getUserCodeList(String principalName) {
        Map<String, String> uuidNamePairs = new HashMap<>();
        for (Code code : codeRepository.findAllByCreatorName(principalName)) {
            uuidNamePairs.put(code.getUuid().toString(), code.getName());
        }
        return uuidNamePairs;
    }

    @Transactional
    Response deleteCode(String uuid, String principalName) {
        Optional<User> databaseUser = userRepository.findFirstByName(principalName);
        if (databaseUser.isEmpty()) {
            return new Response().setResult("Failure!").setMessage("Could not validate user sending request.");
        }
        User user = databaseUser.get();
        Code code = codeRepository.getReferenceById(UUID.fromString(uuid));
        if (!code.getCreator().getName().equals(user.getName())) {
            return new Response().setResult("Failure!").setMessage("You are not allowed to delete this code.");
        }
        user.getCodeList().remove(code);
        userRepository.save(user);
        return new Response().setResult("Success!").setMessage("Code was successfully deleted.");
    }

    @Transactional
    Response saveCode(Code code, String principalName) {
        if (!CodeValidator.isValid(code)) {
            return new Response().setResult("Failure!").setMessage(CodeValidator.REQUIREMENTS);
        }
        Optional<User> optionalUser = userRepository.findFirstByName(principalName);
        if (optionalUser.isEmpty()) {
            return new Response().setResult("Failure!").setMessage("Could not authenticate user");
        }
        User user = optionalUser.get();
        code.setCreator(user);
        LocalDateTime creationDate = LocalDateTime.now();
        code.setCreatedAt(creationDate);
        code.setLastModified(creationDate);
        if (code.getViewsAllowed() > 0) {
            code.setViewRestricted(true);
        }
        if (code.getMinutesAllowed() > 0) {
            code.setTimeRestricted(true);
        }
        codeRepository.save(code);
        return new Response().setResult("Success!")
                .setMessage("Code was successfully posted. Generated uuid is:")
                .setUuid(code.getUuid().toString());
    }

    @Transactional
    Response saveUser(User user) {
        if (!UserValidator.isValid(user)) {
            return new Response().setResult("Failure!").setMessage(UserValidator.REQUIREMENTS);
        }
        if (userRepository.existsByName(user.getName())) {
            return new Response().setResult("Failure!").setMessage("This username is already taken.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return new Response().setResult("Success!").setMessage("Registration was completed successfully.");
    }
}
