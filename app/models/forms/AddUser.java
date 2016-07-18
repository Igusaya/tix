package models.forms;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import play.data.validation.Constraints.*;
import utils.UtilSecurity;
import play.data.validation.*;
/**
 * ユーザー登録フォーム用クラス
 * @author igusaya
 *
 */
public class AddUser {
	
	@Required(message = "必須")
	private String name;
	
	@Required(message = "必須")
	private String pass1;
	
	@Required(message = "必須")
	private String pass2;
	
	@Required(message = "必須")
	@Email(message = "無効なアドレスです")
	private String email;
	
	// バリデーションの追加
	public List<ValidationError> validate() {
	        List<ValidationError> errors = new ArrayList<ValidationError>();
	        if(!(pass1.equals(pass2))){
	            errors.add(new ValidationError("pass2", "ちがくね？"));
	        }
	        return errors.isEmpty() ? null : errors;
	}
	
	/**
	 * フォームに入力されたPassを
	 * ハッシュ化した状態で渡す
	 * @return ハッシュ化したPass
	 */
	public String getPass() {
		try {
			return UtilSecurity.sha512(this.pass1);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	//--------- getter setter ----------	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass1() {
		return pass1;
	}
	public void setPass1(String pass1) {
		this.pass1 = pass1;
	}
	public String getPass2() {
		return pass2;
	}
	public void setPass2(String pass2) {
		this.pass2 = pass2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
