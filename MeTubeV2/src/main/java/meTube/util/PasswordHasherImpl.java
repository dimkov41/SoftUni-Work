package meTube.util;

import static com.kosprov.jargon2.api.Jargon2.*;

public class PasswordHasherImpl implements PasswordHasher {
    private final Hasher hasher = jargon2Hasher()
            .type(Type.ARGON2d) // Data-dependent hashing
            .memoryCost(65536)  // 64MB memory cost
            .timeCost(3)        // 3 passes through memory
            .parallelism(4)     // use 4 lanes and 4 threads
            .saltLength(16)     // 16 random bytes salt
            .hashLength(16);    // 16 bytes output hash

    public PasswordHasherImpl() {
    }

    public String hash(byte[] password) {
        return hasher.password(password).encodedHash();
    }

    public boolean matches(String encodedHash, byte[] password) {
        return jargon2Verifier().hash(encodedHash).password(password).verifyEncoded();
    }
}
