package utils;

public class UtilSecurity {
	
	/**
	 * 受けた文字列をハッシュ化する。
	 * @param message
	 * @return
	 * @throws java.security.NoSuchAlgorithmException
	 */
	public static String sha512(String message)
			throws java.security.NoSuchAlgorithmException{
		java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-512");
		StringBuilder sb = new StringBuilder();
		md.update(message.getBytes());
		byte[] mb = md.digest();
		for (byte m : mb){
			String hex = String.format("%02x", m);
			sb.append(hex);
		}
		return sb.toString();
	}
}
