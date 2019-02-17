package meTube.util;

public interface PasswordHasher {
    String hash(byte[] password);

    boolean matches(String encodedHash, byte[] password);
}
