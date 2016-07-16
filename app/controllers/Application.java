
package controllers;

// Formクラスとformオブジェクト使用の為、以下2行のimport文を記述する
import play.data.*;

import models.entity.*;
import models.forms.AddUser;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {
	
	/**
	 * トップページ
	 * @return
	 */
	public Result index(){
		return ok(index.render("未ログイン"));
	}
	
	/**
	 * ユーザー登録画面遷移
	 * @return
	 */
	public Result addUser(){
        Form<AddUser> f = new Form<AddUser>(AddUser.class);
		return ok(add_user.render("DBテスト", f));			
	}
	
	/**
	 * ユーザー登録処理
	 * @return
	 */
	public Result createUser(){
        Form<AddUser> f = new Form<AddUser>(AddUser.class).bindFromRequest();
        if(!f.hasErrors()){
        	AddUser inputData = f.get();
        	TixUser insertData = new TixUser();
        	insertData.setData(inputData.getName(), inputData.getPass(), inputData.getEmail());
        	insertData.save();
    		return ok(index.render(inputData.getName()));
        }else{
        	return badRequest(add_user.render("ERROR", f));
        }		
	}


}

