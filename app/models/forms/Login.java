package models.forms;

import java.security.NoSuchAlgorithmException;

import models.entity.TixUser;
import play.data.validation.*;
import play.mvc.Security;

import com.avaje.ebean.Model.Find;
import utils.*;

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
			hashedPass = UtilSecurity.sha512(pass);
		return find.where()
				.eq("mail", mail)
				.eq("pass", hashedPass)
				.findUnique();
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
