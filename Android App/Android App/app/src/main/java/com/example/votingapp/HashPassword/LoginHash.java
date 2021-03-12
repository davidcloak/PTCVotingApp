package com.example.votingapp.HashPassword;

import java.nio.Buffer;

public class LoginHash {

    /*
    private static final int SaltByteSize = 24;
    private static final int HashByteSize = 24;
    private static final int HasingIterationsCount = 10101;

    public static boolean VerifyHashedPassword(String hashedPassword, String password)
    {

        byte[] _passwordHashBytes;

        int _arrayLen = (SaltByteSize + HashByteSize) + 1;

        if (hashedPassword == null)
        {
            return false;
        }

        if (password == null)
        {
            throw new NullPointerException("password");
        }


        byte[] src = Convert.FromBase64String(hashedPassword);

        if ((src.length != _arrayLen) || (src[0] != 0))
        {
            return false;
        }


        byte[] _currentSaltBytes = new byte[SaltByteSize];
        Buffer.BlockCopy(src, 1, _currentSaltBytes, 0, SaltByteSize);


        byte[] _currentHashBytes = new byte[HashByteSize];
        Buffer.BlockCopy(src, SaltByteSize + 1, _currentHashBytes, 0, HashByteSize);

        try (Rfc2898DeriveBytes bytes = new Rfc2898DeriveBytes(password, _currentSaltBytes, HasingIterationsCount))
        {
            _passwordHashBytes = bytes.GetBytes(SaltByteSize);
        }

        return AreHashesEqual(_currentHashBytes, _passwordHashBytes);

    }


    private static boolean AreHashesEqual(byte[] firstHash, byte[] secondHash)
    {
        int _minHashLength = firstHash.length <= secondHash.length ? firstHash.length : secondHash.length;
        var xor = firstHash.length ^ secondHash.length;
        for (int i = 0; i < _minHashLength; i++)
        {
            xor |= firstHash[i] ^ secondHash[i];
        }
        return 0 == xor;
    }


    private static final int SaltByteSize = 24;
    private static final int HashByteSize = 24;
    private static final int HasingIterationsCount = 10101;
    public static boolean VerifyHashedPassword(String hashedPassword, String password)
    {

        byte[] _passwordHashBytes;

        int _arrayLen = (SaltByteSize + HashByteSize) + 1;

        if (hashedPassword == null)
        {
            return false;
        }

        if (password == null)
        {
            throw new NullPointerException("password");
        }

        byte[] src = Convert.FromBase64String(hashedPassword);

        if ((src.length != _arrayLen) || (src[0] != 0))
        {
            return false;
        }


        byte[] _currentSaltBytes = new byte[SaltByteSize];
        Buffer.BlockCopy(src, 1, _currentSaltBytes, 0, SaltByteSize);

        byte[] _currentHashBytes = new byte[HashByteSize];
        Buffer.BlockCopy(src, SaltByteSize + 1, _currentHashBytes, 0, HashByteSize);

        try (Rfc2898DeriveBytes bytes = new Rfc2898DeriveBytes(password, _currentSaltBytes, HasingIterationsCount))
        {
            _passwordHashBytes = bytes.GetBytes(SaltByteSize);
        }

        return AreHashesEqual(_currentHashBytes, _passwordHashBytes);

    }

    private static boolean AreHashesEqual(byte[] firstHash, byte[] secondHash)
    {
        int _minHashLength = firstHash.length <= secondHash.length ? firstHash.length : secondHash.length;
        var xor = firstHash.length ^ secondHash.length;
        for (int i = 0; i < _minHashLength; i++)
        {
            xor |= firstHash[i] ^ secondHash[i];
        }
        return 0 == xor;
    }
    */
}
