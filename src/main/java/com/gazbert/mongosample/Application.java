package com.gazbert.mongosample;

import com.gazbert.mongosample.services.RegistrationService;
import com.gazbert.mongosample.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * A simple application that uses MongoDB to store and manage User details.
 *
 * @author gazbert
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

  private UserService userService;
  private RegistrationService registrationService;

  @Autowired
  public Application(UserService userService, RegistrationService registrationService) {
    this.userService = userService;
    this.registrationService = registrationService;
  }

  /**
   * Starts the app.
   *
   * @param args no args required.
   */
  public static void main(String[] args) {
    SpringApplication.run(Application.class);
  }

  /**
   * Runs the sample app.
   *
   * @param args no args passed.
   */
  @Override
  public void run(String... args) {
    userService.createUsers();
    userService.fetchSomeUsers();

    registrationService.createRegistrations();
    registrationService.fetchSomeRegistrations();
  }
}
