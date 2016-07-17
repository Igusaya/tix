package models.securitys;

import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;

public class Secured extends Security.Authenticator{
	
	/**
	 * ユーザー名取得のルール
	 * <br>ログイン中の判定を登録されたTixUser.idで行う。
	 * @param ctx
	 * @return TixUser.id
	 */
	@Override
	public String getUsername(Context ctx){
		return ctx.session().get("username");
	}
	
	/**
	 * 非認証時のアクション
	 * <br>当初開こうとしていたUrlを取得。特にない場合はindexとする。
	 * @param ctx
	 * @return login画面へのResult
	 */
	@Override
	public Result onUnauthorized(Context ctx){
		String returnUrl = ctx.request().uri();
		if(returnUrl == null)
			returnUrl = "/";
		ctx.session().put("returnUrl", returnUrl);
		return redirect(controllers.routes.Application.login());
	}
}
