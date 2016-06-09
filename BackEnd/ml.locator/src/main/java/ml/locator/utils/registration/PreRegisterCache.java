/**
 * 
 */
package ml.locator.utils.registration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Jun 7, 2016
 * @author Alex Iakovenko
 * @email aleks.iakovenko@gmail.com
 */
@Service
@Scope("singleton")
public class PreRegisterCache {
	private Map<String, RegisterLink> registerLinks;
	
	private PreRegisterCache(){
		registerLinks = new ConcurrentHashMap<>();
	}
	
	public void put(String login, RegisterLink link){
		if (registerLinks.containsKey(login)){
			RegisterLink oldLink = registerLinks.get(login);
			registerLinks.replace(login, oldLink, link);
		} else {
			registerLinks.put(login, link);	
		}
	}
	
	public RegisterLink getLink(String login){
		return registerLinks.get(login);
	}
	
	public String getLogin(RegisterLink link){
		String login =  registerLinks.entrySet().stream()
				.filter(entry -> entry.getValue().equals(link))
				.map(Map.Entry::getKey)
				.findFirst()
				.get();
		return login;
	}
	
	public String getLogin(String key){
		String login =  registerLinks.entrySet().stream()
				.filter(entry -> entry.getValue().getKey().equals(key))
				.map(Map.Entry::getKey)
				.findFirst()
				.get();
		return login;
	}
	
	public boolean isEmpty(){
		return registerLinks.isEmpty();
	}
	
	public void clear(){
		registerLinks.clear();
	}

}
