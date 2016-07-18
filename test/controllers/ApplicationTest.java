package controllers;

import static org.junit.Assert.*;

import org.junit.Test;

import play.Logger;
import play.twirl.api.Content;
import static play.test.Helpers.*;

/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {

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
}
