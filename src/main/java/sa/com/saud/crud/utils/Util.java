package sa.com.saud.crud.utils;

import java.util.Random;

public class Util {

	
	public static final String randomWords () {
		
		Random rand = new Random();
        String randomSecertKey = "";
    for(int i = 0; i < 10; i++) {
    	randomSecertKey += (char)(Math.random() * 26 + 97);
    	randomSecertKey += (char)(Math.random() * 26 + 97);
    	randomSecertKey += rand.nextInt(50);
    	randomSecertKey +=  Character.toUpperCase((char)(Math.random() * 26 + 97));
    	randomSecertKey += rand.nextInt(1000);
    }

        
    

	    
		return randomSecertKey;
	}
}
