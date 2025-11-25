package model;

import java.util.Objects;

public abstract class IdentifiableClass {
	
	private Long id;
	
	public IdentifiableClass(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof IdentifiableClass)) {
			return false;
		}
		IdentifiableClass other = (IdentifiableClass) obj;
		return Objects.equals(id, other.id);
	}
	
}
