package ef.hrms.core.base;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public final class SaltedPassword {
	private final String password;
	private byte[] passwordHashed;
	private byte[] passwordSalt;
	private int saltSize = 6;
	private final Encoder encoder = Base64.getEncoder().withoutPadding();
	private final Decoder decoder = Base64.getDecoder();

	public SaltedPassword() {
		this.password = "";

	}

	public SaltedPassword(String password) {
		this.password = password;
		this.create();
	}

	public SaltedPassword(String password, final int saltSize) {
		this.password = password;
		this.saltSize = saltSize;
		this.create();
	}

	public byte[] getPasswordHashed() {
		return passwordHashed;
	}

	public byte[] getPasswordSalt() {
		return passwordSalt;
	}

	public String getPasswordHashedEncoded() {
		return encoder.encodeToString(passwordHashed);
	}

	public String getPasswordSaltEncoded() {
		return encoder.encodeToString(passwordSalt);
	}

	public boolean checkPassword(final String clearTypePassword, final String hashedPassword, final String salt)
			throws Exception {
		try {
			byte[] bSalt = decoder.decode(salt);
			byte[] bHashedPwd = Hash(clearTypePassword, bSalt);
			return encoder.encodeToString(bHashedPwd).equals(hashedPassword);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
			throw new Exception(e.toString());
		}

	}

	public static boolean checkPassword(final String clearTypePassword, final byte[] hashedPassword, final byte[] salt)
			throws Exception {
		try {
			return Arrays.equals(Hash(clearTypePassword, salt), hashedPassword);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
			throw new Exception(e.toString());
		}

	}

	private byte[] Salt() {
		SecureRandom random = new SecureRandom();
		byte salt[] = new byte[this.saltSize];
		random.nextBytes(salt);
		return salt;
	}

	private static byte[] Hash(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 256);
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
		byte[] hash = factory.generateSecret(spec).getEncoded();
		return hash;
	}

	private void create() {
		byte[] bSalt = Salt();
		this.passwordSalt = bSalt;
		try {
			this.passwordHashed = Hash(this.password, bSalt);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
	}

}