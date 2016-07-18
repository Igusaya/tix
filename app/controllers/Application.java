
package controllers;

import play.data.*;
import play.filters.csrf.AddCSRFToken;
import play.filters.csrf.RequireCSRFCheck;
import models.entity.*;
import models.forms.AddUser;
import models.forms.Login;
import play.mvc.*;
import views.html.*;
import static play.data.Form.form;
import play.Logger;
import com.avaje.ebean.Model.Find;

public class Application extends Controller {
	
	/**
	 * トップページ ユーザー画面
	 * <br>要認証
	 * <br>画面にログインユーザー名の文字列を渡す
	 * @return
	 */
	@play.mvc.Security.Authenticated(models.securitys.Secured.class)
	public Result index(){
		Logger.debug("index");
		Find<Long, TixUser> find = new Find<Long, TixUser>(){};
		
		// セッションからIdを取得し、そこからユーザー名を取得する。
		String sTixUserId = ctx().session().get("username");
		Long lTixUserId = Long.valueOf(sTixUserId);
		String name = find.where().eq("id", lTixUserId).findUnique().getName();
		
		return ok(index.render(name));
	}
	
	/**
	 * ユーザー登録画面遷移
	 * <br>ユーザー登録用のフォームを持ったResultを渡す
	 * @return
	 */
	public Result addUser(){
        Form<AddUser> f = new Form<AddUser>(AddUser.class);
		return ok(add_user.render(f));			
	}
	
	/**
	 * ユーザー登録処理
	 * <br>登録後、ログイン処理を行う。
	 * @return
	 */
	@RequireCSRFCheck
	public Result createUser(){
        Form<AddUser> f = new Form<AddUser>(AddUser.class).bindFromRequest();
        if(!f.hasErrors()){
        	// 新規ユーザのインサート処理
        	AddUser inputData = f.get();
        	TixUser insertData = new TixUser();
        	insertData.setData(inputData.getName(), inputData.getPass(), inputData.getEmail());
        	insertData.save();
        	
        	// ログイン処理
        	session("username", String.valueOf(insertData.getId()));
        	
    		return ok(index.render(inputData.getName()));
        }else{
        	return badRequest(add_user.render(f));
        }
	}
	
	/**
	 * ログインフォーム
	 * 認証失敗時の遷移先
	 * @return
	 */
	@AddCSRFToken
	public Result login(){
		return ok(login.render(form(Login.class)));
	}

	/**
	 * 認証アクション
	 * @return
	 */
	@RequireCSRFCheck
	public Result authenticate(){
		Form<Login> loginForm = form(Login.class).bindFromRequest();
		if(loginForm.hasErrors()){
			return badRequest(views.html.login.render(loginForm));
		}else{
			// フォームからメールアドレスを取得。そこからUserIDを取得し、セッションへ追加する。
			Find<Long, TixUser> find = new Find<Long, TixUser>(){};
			Long TixUserId = find.where().eq("mail", loginForm.get().getMail()).findUnique().getId();
			session("username", String.valueOf(TixUserId));
			
			// Securedクラスで保存しておいた遷移先を取得
			String returnUrl = ctx().session().get("returnUrl");
			if(   returnUrl == null 
			   || returnUrl.equals("")
		       || returnUrl.equals(routes.Application.login().absoluteURL(request())))
				{returnUrl = routes.Application.index().url();
				}
				return redirect(returnUrl);
		}
	}

	/**
	 * ログアウトアクション
	 * <br>要認証
	 * @return
	 */
	@play.mvc.Security.Authenticated(models.securitys.Secured.class)
	public Result logout(){
		session().clear();
		return redirect(routes.Application.index());
	}
}