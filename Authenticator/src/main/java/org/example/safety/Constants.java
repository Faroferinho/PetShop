package org.example.safety;

public class Constants {
    public static final String URL = "/api/v1/";
    public static final String LOGIN = URL + "login";

    public static final String ANIMAL = "http://localhost:8081/";

    public static final String PERSONS_URL = "http://localhost:8082/";
    public static final String CLIENTS = PERSONS_URL + LOGIN;

    public static final String SERVICES = "http://localhost:8083/";

    public static final String VALIDATE = URL + "validate";

    public static final long VALIDITY = 3600000;
}
