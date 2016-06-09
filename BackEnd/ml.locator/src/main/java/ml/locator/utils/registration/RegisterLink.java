/**
 * 
 */
package ml.locator.utils.registration;

/**
 * Jun 8, 2016
 * @author Alex Iakovenko
 * @email aleks.iakovenko@gmail.com
 */
public class RegisterLink {
	private String host;
	private String email;
	private String key;
	
	public RegisterLink(String host, String email, String key){
		this.host = host;
		this.email = email;
		this.key = key;
	}

	public String getHost() {
		return host;
	}

	public String getEmail() {
		return email;
	}

	public String getKey() {
		return key;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((host == null) ? 0 : host.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegisterLink other = (RegisterLink) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (host == null) {
			if (other.host != null)
				return false;
		} else if (!host.equals(other.host))
			return false;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return host + "?email=" + email + "&key=" + key;
	}
	
	
	
	
}
