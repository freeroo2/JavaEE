package domains;

import java.io.Serializable;
import java.sql.Timestamp;

public class SessionStorageId  implements Serializable{
	private static final long serialVersionUID = 3176972128965536016L;
	private String id;
	private String localService;
	private Timestamp created;
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((localService == null) ? 0 : localService.hashCode());
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
		SessionStorageId other = (SessionStorageId) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (localService == null) {
			if (other.localService != null)
				return false;
		} else if (!localService.equals(other.localService))
			return false;
		return true;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLocalService() {
		return localService;
	}
	public void setLocalService(String localService) {
		this.localService = localService;
	}
	
}
