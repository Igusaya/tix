package controllers;

import static org.junit.Assert.*;

import org.junit.Test;

import models.forms.AddUser;
import models.forms.Login;
import play.data.Form;
import play.twirl.api.Content;

import static play.test.Helpers.*;

class ApplicationTest {

	/**
	 * indexのレンダリングテスト
	 */
    @Test
    public void index() {
    	String msg = "テストメッセージ";
    	// レンダリングを行う
        Content index = views.html.index.render(msg);
        // レンダリング結果のコンテンツタイプを判定
        assertEquals("text/html", index.contentType());
        // レンダリング後、渡した文字列が含まれるか判定
        assertTrue(contentAsString(index).contains(msg));
    }
    
	/**
	 * loginのレンダリングテスト
	 */
    @Test
    public void login() {
    	String msg = "form";
    	// レンダリングを行う
        Content index = views.html.login.render(new Form<>(Login.class));
        // レンダリング結果のコンテンツタイプを判定
        assertEquals("text/html", index.contentType());
        // レンダリング後、渡した文字列が含まれるか判定
        assertTrue(contentAsString(index).contains(msg));
    }
        
	/**
	 * add_userのレンダリングテスト
	 */
    @Test
    public void add_user() {
    	String msg = "from";
    	// レンダリングを行う
        Content index = views.html.add_user.render(new Form<>(AddUser.class));
        // レンダリング結果のコンテンツタイプを判定
        assertEquals("text/html", index.contentType());
        // レンダリング後、渡した文字列が含まれるか判定
        assertTrue(contentAsString(index).contains(msg));
    }
}
