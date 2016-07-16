
package controllers;

// Formクラスとformオブジェクト使用の為、以下2行のimport文を記述する
import play.data.*;

import java.util.ArrayList;
import java.util.List;
import models.entity.TixUser;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {
	
	/**
	 * トップページ
	 * @return
	 */
	public Result index(){
		return ok(index.render("Login"));
	}
	
	/**
	 * ユーザー登録画面遷移
	 * @return
	 */
	public Result add_user(){
		List<TixUser> datas = new ArrayList<TixUser>(); 
		datas = TixUser.find.all();
        Form<TixUser> f = new Form<TixUser>(TixUser.class);
		return ok(add_user.render("DBテスト", datas, f));			
	}
	
	/**
	 * ユーザー登録処理
	 * @return
	 */
	public Result create_user(){
		List<TixUser> datas = new ArrayList<TixUser>(); 
		datas = TixUser.find.all();
        Form<TixUser> f = new Form<TixUser>(TixUser.class).bindFromRequest();
        if(!f.hasErrors()){
        	TixUser data = f.get();
        	data.save();
    		return ok(my_page.render(datas.get(0).getName(), datas));	
        }else{
        	return badRequest(add_user.render("ERROR", datas, f));
        }		
	}


}

