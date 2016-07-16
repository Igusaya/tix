package models.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import com.avaje.ebean.annotation.*;
import play.data.validation.*;
import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.Required;
import com.avaje.ebean.Model; 

@Entity
public class TixUser extends Model {
    @Id    // PK設定
    public Long id;
    
    @Constraints.Required    // not null制約
    public String name;
    
    @Constraints.Required
    @Email
    public String mail;
    
    @Constraints.Required
    public String pass;
    
    public String icon;
    @CreatedTimestamp
    public Date createdate;
    @UpdatedTimestamp
    public Date updatedate;
    
    // Finderクラスは検索機能を纏めたクラス。
    public final static Find<Long, TixUser> find =
            new Find<Long, TixUser>(){};
   /**
    * DBにインサートする項目を渡す
    * @param name
    * @param pass
    * @param mail
    */
   public void setData(String name, String pass, String mail){
	   this.name = name;
	   this.pass = pass;
	   this.mail = mail;
   }
// ----- getter, setter -----
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}


	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", mail=" + mail + ", pass=" + pass + ", icon=" + icon
				+ ", createdate=" + createdate + ", updatedate=" + updatedate + "]";
	}
}
