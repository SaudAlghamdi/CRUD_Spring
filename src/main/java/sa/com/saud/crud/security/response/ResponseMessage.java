package sa.com.saud.crud.security.response;

/**
 * @author salghamdi
 *
 */
public class ResponseMessage {
	  private String message;
	 
	  /**
	 * @param message
	 */
	public ResponseMessage(String message) {
	    this.message = message;
	  }
	 
	  /**
	 * @return
	 */
	public String getMessage() {
	    return message;
	  }
	 
	  /**
	 * @param message
	 */
	public void setMessage(String message) {
	    this.message = message;
	  }
	}