package domains;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(SessionStorageId.class)
public class SessionStorage {
	@Id
	@Column(length=80,nullable=false)
	private String id;
	@Column(length=40,nullable=false)
	private String sid;
	@Column(length=80,nullable=false)
	private String sessionId;
	@Id
	@Column(length=40,nullable=false)
	private String localService;
	@Id
	@Column(length=40,nullable=false)
	private Timestamp created=new Timestamp(System.currentTimeMillis());
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public String getId() {
		return id;
	}
	public void setId(String sessionId2) {
		this.id = sessionId2;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getLocalService() {
		return localService;
	}
	public void setLocalService(String localService) {
		this.localService = localService;
	}
	public String getUid() {
		return sid;
	}
	public void setUid(String st) {
		this.sid = st;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((localService == null) ? 0 : localService.hashCode());
		result = prime * result + ((sid == null) ? 0 : sid.hashCode());
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
		SessionStorage other = (SessionStorage) obj;
		if (localService == null) {
			if (other.localService != null)
				return false;
		} else if (!localService.equals(other.localService))
			return false;
		if (sid == null) {
			if (other.sid != null)
				return false;
		} else if (!sid.equals(other.sid))
			return false;
		return true;
	}
	
}
