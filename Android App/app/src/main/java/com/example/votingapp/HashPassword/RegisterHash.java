package com.example.votingapp.HashPassword;

import java.nio.Buffer;

public class RegisterHash {
    /*
    private static final int SaltByteSize = 24;
    private static final int HashByteSize = 24;
    private static final int HasingIterationsCount = 10101;

    public static String HashPassword(String password)
    {

        byte[] salt;
        byte[] buffer2;
        if (password == null)
        {
            throw new NullPointerException("password");
        }
        try (Rfc2898DeriveBytes bytes = new Rfc2898DeriveBytes(password, SaltByteSize, HasingIterationsCount))
        {
            salt = bytes.Salt;
            buffer2 = bytes.GetBytes(HashByteSize);
        }

        byte[] dst = new byte[(SaltByteSize + HashByteSize) + 1];
        Buffer.BlockCopy(salt, 0, dst, 1, SaltByteSize);
        Buffer.BlockCopy(buffer2, 0, dst, SaltByteSize + 1, HashByteSize);
        return Convert.ToBase64String(dst);
    }
    */


}
