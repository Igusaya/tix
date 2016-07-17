package models.forms;

import java.security.NoSuchAlgorithmException;

import models.entity.TixUser;
import play.data.validation.*;
import com.avaje.ebean.Model.Find;

/**
 * ログインフォーム用のメンバを持つクラス
 * EmailとPassのフォームを提供し、ユーザーの判定を行う。
 * @author igusaya
 *
 */
public class Login {
	
	@Constraints.Required
	private String mail;
	@Constraints.Required
	private String pass;
	
	/**
	 * ログインユーザーの存在判定
	 * <br>DBにこれからログインするユーザーが存在するか判定を行う
	 * @return 存在する : null 
	 * <br> 存在しない : エラーメッセージ
	 * @throws NoSuchAlgorithmException
	 */
	public String validate() throws NoSuchAlgorithmException{
		TixUser userData = authenticate(mail, pass);
		if(userData == null )
			return "Invalid mail or password";
		return null;
	}

	/**
	 * 受け取ったmailとpassからTixUserを取得する
	 * @param mail
	 * @param pass
	 * @return
	 * @throws java.security.NoSuchAlgorithmException
	 */
	private static TixUser authenticate(String mail, String pass) throws NoSuchAlgorithmException {
		Find<Long, TixUser> find = new Find<Long, TixUser>(){};
		String hashedPass = "";
		if (pass != null)
			hashedPass = sha512(pass);
		return find.where()
				.eq("mail", mail)
				.eq("pass", hashedPass)
				.findUnique();
	}
	
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

	// ----- getter setter -----
	public String getMail() {
		return mail;
	}

	public void setMail(String username) {
		this.mail = username;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
}
